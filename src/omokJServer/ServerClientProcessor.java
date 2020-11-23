package omokJServer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import omokJServer.TransferObj.Opcode;

public class ServerClientProcessor extends Thread {
	private final static int MAX_USERS = 10;
	
	ArrayList<ServerClientProcessor> clientList = null; 
	OmokRoomManager roomManager;
	
	private Socket socket = null;
	protected ObjectInputStream is;
	protected ObjectOutputStream os;
	private String nickname = null;
	private int roomNumber = 0; // �ƹ� �濡�� �ȵ� ������ 0
	
	// main���� �� Ŭ���̾�Ʈ ���� �� �� ���� ���� �Ǵ� ������ ===========
	public ServerClientProcessor(Socket socket, ArrayList<ServerClientProcessor> clientList, OmokRoomManager roomManager) {
		this.socket = socket;
		this.clientList = clientList;
		this.roomManager = roomManager;
	}
	
	@Override
	public void run() { // �� Ŭ���̾�Ʈ���� �Ҵ� �Ǵ� ������ ================================================
		try {
			is = new ObjectInputStream(socket.getInputStream());
			os = new ObjectOutputStream(socket.getOutputStream());
			clientList.add(this); 
			
			TransferObj request;
			while (true) {
				request = (TransferObj)is.readObject();
				// ��� ������ ��ü �ϳ��� ��� ����ȭ �ؼ� �ְ����. ��ü�� operation code�� enum Ŭ������ ����. �� operation ���� �ʿ� ������ ���� Ŭ������ ����.
				// operation process
				switch(request.getOpcode()) {
				case joinServer: // showRoomList
					if(clientList.size()>10) {
						// Ŭ���̾�Ʈ ���� ���� ���ڸ��� Ŭ���̾�Ʈ���� joinServer ȣ��, �� �� ó�� ��������
						// ���� �ο����� 10���� �ʰ��ϸ� �� �κп���, denyEntry �޼ҵ� �����ϸ� ���ٰ��� �˸��� ������ �ƿ� �������.
					}
					this.nickname = request.joinServer.nickname; // ���ƿ� �г����� ����
					showRoomList();
					break;
				case joinRoom: 
					int roomNum = roomManager.joinRoom(this, request.joinRoom.roomNumber-1);
					this.roomNumber = roomNum;
					if(roomNum == 0) ; // �� ������ ������ ���� Ŭ���̾�Ʈ ������ ������ ���̱� ������ �ϴ� ���߿� ����
					if(roomManager.room[roomNum-1].player[0] != null)
					roomManager.room[roomNum-1].player[0].showRoom();
					if(roomManager.room[roomNum-1].player[1] != null)
					roomManager.room[roomNum-1].player[1].showRoom();
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
		finally {
			try {
				is.close();
				os.close();
				socket.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
}
	
	// ===== ���۷��̼� ó���ϴ� �޼ҵ� =====
	
	// ===== �������� �����̾�Ʈ�� ������ �޼ҵ� =====
	private void showRoomList() {
		// ���ȣ int �迭ȭ, �� ���÷��̾� String �迭ȭ
		int rm_len = roomManager.room.length;
		int[] rNs = new int[rm_len];
		String[] p1 = new String[rm_len];
		String[] p2 = new String[rm_len];
		for(int i = 0; i < rm_len; i++) {
			rNs[i] = roomManager.room[i].roomNumber;
			p1[i] = roomManager.room[i].player[0].nickname;
			p2[i] = roomManager.room[i].player[1].nickname;
		}
		TransferObj tObj = new TransferObj(Opcode.showRoomList);
		tObj.showRoomList = tObj.new ShowRoomList(rNs, p1, p2); // �� ��ȣ, P1, P2 �г���
		try {
			os.writeObject(tObj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void showRoom() { // �� �� ��ȣ, ����ִ� ��� �г��� ����
		TransferObj tObj = new TransferObj(Opcode.showRoom);
		tObj.showRoom = tObj.new ShowRoom(this.roomNumber, this.roomManager.room[this.roomNumber-1].player[0].nickname,
				this.roomManager.room[this.roomNumber-1].player[1].nickname);
		try {
			os.writeObject(tObj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ===== �� Ŭ������ ó���ϴ� �޼ҵ� =====
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
