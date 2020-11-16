package omokJServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import omokJServer.TransferObj.Opcode;

public class ServerClientProcessor extends Thread {
	private final static int MAX_USERS = 10;
	
	ArrayList<ServerClientProcessor> clientList = null; 
	
	private Socket socket = null;
	protected ObjectInputStream is;
	protected ObjectOutputStream os;
	private String nickname = null;
	private int roomNumber = 0; // �ƹ� �濡�� �ȵ� ������ 0
	
	public ServerClientProcessor(Socket socket, ArrayList<ServerClientProcessor> clientList) {
		this.socket = socket;
		this.clientList = clientList;
	}
	
	@Override
	public void run() {
		try {
			is = new ObjectInputStream(socket.getInputStream());
			os = new ObjectOutputStream(socket.getOutputStream());
			clientList.add(this); 
			while (true) {
				TransferObj request = (TransferObj)is.readObject();
				// ��� ������ ��ü �ϳ��� ��� ����ȭ �ؼ� �ְ����. ��ü�� operation code�� enum Ŭ������ ����. �� operation ���� �ʿ� ������ ���� Ŭ������ ����.
				// operation process
				switch(request.getOpcode()) {
				case joinServer: // showRoomList
					if(clientList.size()>10) {
						// Ŭ���̾�Ʈ ���� ���� ���ڸ��� Ŭ���̾�Ʈ���� joinServer ȣ��, �� �� ó�� ��������
						// ���� �ο����� 10���� �ʰ��ϸ� �� �κп���, denyEntry �޼ҵ� �����ϸ� ���ٰ��� �˸��� ������ �ƿ� �������.
					}
					break;
				case joinRoom: // showRoom 2���� �濡 ������ startOmok
					break;
				case turnOver: // �޾Ƽ� deliverTurn���� ��� Ŭ���̾�Ʈ���� ����
					break;
				case gameOver:
						break;
				case showRoomList:
					break;
				default:
					break;
				}
			}
		}
		catch(IOException e) {
			consoleLog(this.nickname + "lost connect.");
		} catch (ClassNotFoundException e) {
			consoleLog(this.nickname + "lost connect.");
		}
}
	
	// ===== ���۷��̼� ó���ϴ� �޼ҵ�
	
	// ===== �� Ŭ������ ó���ϴ� �޼ҵ�
	public void setRoomNumber(int rN) {
		this.roomNumber = rN;
	}
	public int getRoomNumber( ) {
		return this.roomNumber;
	}
	//
	private void consoleLog(String log) {
		System.out.println(log);
	}
}
