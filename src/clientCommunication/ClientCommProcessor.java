package clientCommunication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import omokJServer.TransferObj;
import omokJServer.TransferObj.Opcode;

public class ClientCommProcessor extends Thread {
	//ù ���� �޴����� ���� ���� ��ư ������ �� Ŭ������ �г����� �����ϸ鼭 �����带 ������
	private static final String SERVER_IP = "52.78.178.184";
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
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		// input, output ��Ʈ�� ���� �� ����Ÿ ��ü ���� ��� ����
		try {
			is = new ObjectInputStream(socket.getInputStream());
			os = new ObjectOutputStream(socket.getOutputStream());
			joinServer(); // ��Ʈ�� ���� ���� �ٷ� joinServer ���� �� ������ �Ϻ��� �Ǹ� ShowRoomList�� �޾Ƽ� ���
			
			TransferObj request;
			while (true) {
				request = (TransferObj)is.readObject();
				// ��� ������ ��ü �ϳ��� ��� ����ȭ �ؼ� �ְ����. ��ü�� operation code�� enum Ŭ������ ����. �� operation ���� �ʿ� ������ ���� Ŭ������ ����.
				// operation process
				switch(request.getOpcode()) {
				case turnOver: // ���� turnOver�� �ްų� ù ���� �������� ����
					break;
				case showRoomList:  // �� ���� ���ȣ, ����ִ� ��� �г��� (�� ĭ�� null�� ����)
					// �� ����� ���ƿ���
					// Ŭ���̾�Ʈ ������ request.showRoomList.
					//request.showRoomList.roomNumbers
					//request.showRoomList.player1
					//request.showRoomList.player2
					break;
				case showRoom: // ���µ� ���� ������ 0 �� ���ƿ��� �ƴϸ� �� ���ȣ, ����ִ� ��� �г����� ���ƿ�
					//request.showRoom.roomNumber (int)
					//request.showRoom.player1 (String)
					//request.showRoom.player2 (String)
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
	private void joinServer() {
		TransferObj tObj = new TransferObj(Opcode.joinServer);
		tObj.joinServer = tObj.new JoinServer(this.nickname);
		try {
			os.writeObject(tObj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// �� ����Ʈ���� ���ϴ� �� ����Ŭ��(?) �ϸ� �� ��ȣ ���ڷ� �ָ� ����. ���� showRoom�� ���ƿ´�
	// ���� player1, player2�� �� �� null�� �ƴϸ� �� �޼ҵ� ���� �ʰ� �׳� ����.
	public void joinRoom(int roomNum) { //showRoom�� �ް� ���� �������� �� �� ��ȣ�� Ŭ���̾�Ʈ �ڱ� �� ��ȣ�� ����!!!!!!!!!!
		TransferObj tObj = new TransferObj(Opcode.joinRoom);
		tObj.joinRoom = tObj.new JoinRoom(roomNum);
		try {
			os.writeObject(tObj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
