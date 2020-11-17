package omokJServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import omokJServer.TransferObj;
import omokJServer.OmokRoomManager.OmokRoom;
import omokJServer.TransferObj.Opcode;

public class ServerClientProcessor extends Thread {
	private final static int MAX_USERS = 10;
	
	ArrayList<ServerClientProcessor> clientList = null; 
	OmokRoomManager roomManager;
	
	private Socket socket = null;
	protected ObjectInputStream is;
	protected ObjectOutputStream os;
	private String nickname = null;
	private int roomNumber = 0; // 아무 방에도 안들어가 있으면 0
	
	// main에서 각 클라이언트 연결 될 때 마다 실행 되는 생성자 ===========
	public ServerClientProcessor(Socket socket, ArrayList<ServerClientProcessor> clientList, OmokRoomManager roomManager) {
		this.socket = socket;
		this.clientList = clientList;
		this.roomManager = roomManager;
	}
	
	@Override
	public void run() { // 각 클라이언트에게 할당 되는 쓰레드 ================================================
		try {
			is = new ObjectInputStream(socket.getInputStream());
			os = new ObjectOutputStream(socket.getOutputStream());
			clientList.add(this); 
			
			TransferObj request;
			while (true) {
				request = (TransferObj)is.readObject();
				// 모든 전송을 객체 하나에 묶어서 직렬화 해서 주고받음. 객체에 operation code를 enum 클래스로 가짐. 각 operation 마다 필요 정보도 내부 클래스로 가짐.
				// operation process
				switch(request.getOpcode()) {
				case joinServer: // showRoomList
					if(clientList.size()>10) {
						// 클라이언트 서버 연결 되자마자 클라이언트에서 joinServer 호출, 이 때 처리 과정에서
						// 연결 인원수가 10명을 초과하면 이 부분에서, denyEntry 메소드 실행하며 접근거절 알리며 연결을 아예 끊어버림.
					}
					this.nickname = request.joinServer.nickname; // 날아온 닉네임을 저장
					showRoomList();
					break;
				case joinRoom: 
					int roomNum = roomManager.joinRoom(this, request.joinRoom.roomNumber-1);
					this.roomNumber = roomNum;
					if(roomNum == 0) ; // 방 꽉차서 못들어가는 경우는 클라이언트 측에서 막아줄 것이기 때문에 일단 나중에 개발
					if(roomManager.room[roomNum-1].player[0] != null)
					roomManager.room[roomNum-1].player[0].showRoom();
					if(roomManager.room[roomNum-1].player[1] != null)
					roomManager.room[roomNum-1].player[1].showRoom();
					break;
				case turnOver: // 받아서 deliverTurn으로 상대 클라이언트에게 전달
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
	
	// ===== 오퍼레이션 처리하는 메소드 =====
	
	// ===== 서버에서 킄라이언트로 보내는 메소드 =====
	private void showRoomList() {
		// 방번호 int 배열화, 각 방플레이어 String 배열화
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
		tObj.showRoomList = tObj.new ShowRoomList(rNs, p1, p2); // 방 번호, P1, P2 닉네임
		try {
			os.writeObject(tObj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void showRoom() { // 들어간 방 번호, 들어있는 사람 닉네임 날림
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
	// ===== 이 클래스를 처리하는 메소드 =====
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
