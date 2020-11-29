package clientCommunication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import omokJServer.TransferObj.*;
import omokJServer.TransferObj;

public class ClientCommProcessor extends Thread {
	//ù ���� �޴����� ���� ���� ��ư ������ �� Ŭ������ �г����� �����ϸ鼭 �����带 ������
	private static final String SERVER_IP = "52.78.178.184"; // 172.31.42.164       52.78.178.184
	private static final int SERVER_PORT = 50505;
	
	private Socket socket = null;
	
	protected ObjectInputStream is;
	protected ObjectOutputStream os;
	
	private String nickname = null;
	private int roomNumber = 0; // �ƹ� �濡�� �ȵ� ������ 0
	
	public ClientCommProcessor (String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public void run() {
		//������ ����
		try {
			//InetAddress address= InetAddress.getByName(SERVER_IP);
			//socket=new Socket(address.getHostAddress(),SERVER_PORT);
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		// input, output ��Ʈ�� ���� �� ����Ÿ ��ü ���� ��� ����
		try {
			System.out.println("JOIN===============");
			os = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("JOIN===============");
			is = new ObjectInputStream(socket.getInputStream());
			System.out.println("JOIN===============");
			joinServer(nickname); // ��Ʈ�� ���� ���� �ٷ� joinServer ���� �� ������ �Ϻ��� �Ǹ� ShowRoomList�� �޾Ƽ� ���
			System.out.println("JOIN===============");
			
			while (true) {
				Opcode opcode = (Opcode)is.readObject();
				// ��� ������ ��ü �ϳ��� ��� ����ȭ �ؼ� �ְ����. ��ü�� operation code�� enum Ŭ������ ����. �� operation ���� �ʿ� ������ ���� Ŭ������ ����.
				// operation process
				switch(opcode) {
				case turnOver: // ���� turnOver�� �ްų� ù ���� �������� ����
					break;
				case showRoomList:  // �� ���� ���ȣ, ����ִ� ��� �г��� (�� ĭ�� null�� ����)
					ShowRoomList sRL = new ShowRoomList();
					sRL = (ShowRoomList)is.readObject();
					
					ShowRoomList.roomNumbers = null;
					ShowRoomList.player1= null;
					ShowRoomList.player2= null;
					
					break;
				case showRoom: // ���µ� ���� ������ 0 �� ���ƿ��� �ƴϸ� �� ���ȣ, ����ִ� ��� �г����� ���ƿ�
					ShowRoom sR = new ShowRoom();
					sR = (ShowRoom)is.readObject();
		
					ShowRoom.roomNumber = 0;
					ShowRoom.player1 = null;
					ShowRoom.player2 = null;
					
					break;
				default:
					break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if( socket != null && !socket.isClosed()) {
					is.close();
					os.close();
					socket.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
}
	//JoinServer ����, ��Ʈ�� ����� ���Ŀ� �ٷ� ����
	private void joinServer(String nickname) {
		Opcode opcode = Opcode.joinServer;
		JoinServer.nickname = nickname;
		try {
			os.writeObject(opcode);
			os.writeObject(new JoinServer());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// �� ����Ʈ���� ���ϴ� �� ����Ŭ��(?) �ϸ� �� ��ȣ ���ڷ� �ָ� ����. ���� showRoom�� ���ƿ´�
	// ���� player1, player2�� �� �� null�� �ƴϸ� �� �޼ҵ� ���� �ʰ� �׳� ����.
	public void joinRoom(int roomNum) { //showRoom�� �ް� ���� �������� �� �� ��ȣ�� Ŭ���̾�Ʈ �ڱ� �� ��ȣ�� ����!!!!!!!!!!
		this.roomNumber = roomNum;
		Opcode opcode = Opcode.joinRoom;
		JoinRoom jR = new JoinRoom();
		JoinRoom.roomNumber = roomNum;
		try {
			os.writeObject(opcode);
			os.writeObject(jR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
