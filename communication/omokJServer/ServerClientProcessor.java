package omokJServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import omokJServer.TransferObj.Opcode;

public class ServerClientProcessor extends Thread {
	private Socket socket = null;
	ArrayList<ServerClientProcessor> clientList = null; 
	
	protected ObjectInputStream is;
	protected ObjectOutputStream os;
	private String nickname = null;
	private int roomNumber = 0; // 아무 방에도 안들어가 있음
	
	public ServerClientProcessor(Socket socket, ArrayList<ServerClientProcessor> clientList) {
		this.socket = socket;
		this.clientList = clientList;
	}
	
	@Override
	public void run() {
		try {
			is = new ObjectInputStream(socket.getInputStream());
			os = new ObjectOutputStream(socket.getOutputStream());
			while (true) {
				TransferObj request = (TransferObj)is.readObject();
				// 모든 전송을 객체 하나에 묶어서 직렬화 해서 주고받음. 객체에 operation code를 enum 클래스로 가짐. 각 operation 마다 필요 정보도 내부 클래스로 가짐.
				// operation process
				switch(request.getOpcode()) {
				case joinServer: // showRoomList
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
			consoleLog(this.nickname + "님이 나갔습니다.");
		} catch (ClassNotFoundException e) {
			consoleLog(this.nickname + "님이 나갔습니다.");
		}
}
	
	private void doQuit(PrintWriter writer) {
		removeWriter(writer);
		
		String data = this.nickname = "님이 퇴장했습니다.";
		broadcast(data);
	}
	
	private void removeWriter(PrintWriter writer) {
		synchronized (listWriters) {
			listWriters.remove(writer);
		}
	}
	
	private void doMessage(String data) {
		broadcast(this.nickname + " : " + data);
	}
	
	private void doJoin(String nickname, PrintWriter writer) {
		this.nickname = nickname;
		
		String data = nickname + "님이 입장하셨습니다.";
		broadcast(data);
		
		//writer pool에 저장
		addWriter(writer);
	}
	
	private void addWriter(PrintWriter writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}
	
	private void broadcast(String data) {
		synchronized (listWriters) {
			for (PrintWriter writer : listWriters) {
				writer.println(data);
				writer.flush();
			}
		}
	}
	
	private void consoleLog(String log) {
		System.out.println(log);
	}
}
