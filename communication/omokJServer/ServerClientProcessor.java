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
	private int roomNumber = 0; // 아무 방에도 안들어가 있으면 0
	
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
				// 모든 전송을 객체 하나에 묶어서 직렬화 해서 주고받음. 객체에 operation code를 enum 클래스로 가짐. 각 operation 마다 필요 정보도 내부 클래스로 가짐.
				// operation process
				switch(request.getOpcode()) {
				case joinServer: // showRoomList
					if(clientList.size()>10) {
						// 클라이언트 서버 연결 되자마자 클라이언트에서 joinServer 호출, 이 때 처리 과정에서
						// 연결 인원수가 10명을 초과하면 이 부분에서, denyEntry 메소드 실행하며 접근거절 알리며 연결을 아예 끊어버림.
					}
					break;
				case joinRoom: // showRoom 2명이 방에 들어오면 startOmok
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
}
	
	// ===== 오퍼레이션 처리하는 메소드
	
	// ===== 이 클래스를 처리하는 메소드
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
