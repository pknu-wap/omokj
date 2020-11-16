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
	private static final String SERVER_IP = "172.31.42.164";
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
			while (true) {
				TransferObj request = (TransferObj)is.readObject();
				// ��� ������ ��ü �ϳ��� ��� ����ȭ �ؼ� �ְ�����. ��ü�� operation code�� enum Ŭ������ ����. �� operation ���� �ʿ� ������ ���� Ŭ������ ����.
				// operation process
				switch(request.getOpcode()) {
				case turnOver: // ���� turnOver�� �ްų� ù ���� �������� ����
					break;
				case showRoomList:  // �� ���� ���ȣ, ����ִ� ��� �г��� (�� ĭ�� null�� ����)
					break;
				case showRoom:
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}