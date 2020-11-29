package omokJServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import omokJServer.TransferObj.*;
import omokJServer.OmokRoomManager.OmokRoom;

public class ServerCommProcessor extends Thread {
	private final static int MAX_USERS = 10;
	
	ArrayList<ServerCommProcessor> clientList = null; 
	OmokRoomManager roomManager;
	
	private Socket socket = null;
	protected ObjectInputStream is;
	protected ObjectOutputStream os;
	private String nickname = null;
	private int roomNumber = 0; // 0 if never entered any room
	
	// Constructor for each connection
	public ServerCommProcessor(Socket socket, ArrayList<ServerCommProcessor> clientList, OmokRoomManager roomManager) {
		this.socket = socket;
		this.clientList = clientList;
		this.roomManager = roomManager;
	}
	
	@Override
	public void run() { // Thread for each client ================================================
		try {
			is = new ObjectInputStream(socket.getInputStream());
			os = new ObjectOutputStream(socket.getOutputStream());
			clientList.add(this); 
			
			while (true) {
				Opcode opcode = (Opcode)is.readObject();
				// operation process
				switch(opcode) {
				case joinServer: // showRoomList
					if(clientList.size()>10) {
						// n of cl num > 10 -> deny
					}
					this.nickname = (String)is.readObject();
					showRoomList();
					break;
				case joinRoom: 
					JoinRoom jR = (JoinRoom)is.readObject();
					int roomNum = JoinRoom.roomNumber - 1;
					this.roomNumber = roomNum;
					if(roomNum == 0) ; // full ??
					if(roomManager.room[roomNum-1].player[0] != null)
					roomManager.room[roomNum-1].player[0].showRoom();
					if(roomManager.room[roomNum-1].player[1] != null)
					roomManager.room[roomNum-1].player[1].showRoom();
					break;
				case turnOver:
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
			consoleLog(this.nickname + " lost connect.");
		} catch (ClassNotFoundException e) {
			consoleLog(this.nickname + " lost connect.");
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
	
	// ===== Comm Operations =====
	
	// ===== Server to Client =====
	private void showRoomList() {
		Opcode opcode = Opcode.showRoomList;
		ShowRoomList sRL = new ShowRoomList();
		consoleLog(this.nickname + " Connected.");
		// rn => int[], players => String[]
		int rm_len = roomManager.room.length;
		int[] rNs = new int[rm_len];
		String[] p1 = new String[rm_len];
		String[] p2 = new String[rm_len];
		for(int i = 0; i < rm_len; i++) {
			rNs[i] = roomManager.room[i].roomNumber;
			if(roomManager.room[i].player[0] == null) p1[i] = " ";
			else p1[i] = roomManager.room[i].player[0].nickname;
			if(roomManager.room[i].player[1] == null) p2[i] = " ";
			else p2[i] = roomManager.room[i].player[1].nickname;
		}
		// Room Numbers, Players in each rooms
		try {
			os.writeObject(opcode);
			os.writeObject(rNs);
			os.writeObject(p1);
			os.writeObject(p2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void showRoom() { //
		Opcode opcode = Opcode.showRoom;
		ShowRoom sRL = new ShowRoom();
		
		ShowRoom.roomNumber = this.roomNumber;
		ShowRoom.player1 = roomManager.room[this.roomNumber-1].player[0].nickname;
		ShowRoom.player2 = roomManager.room[this.roomNumber-1].player[1].nickname;
		try {
			os.writeObject(opcode);
			os.writeObject(sRL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ===== Methods about this Class =====
	public void setRoomNumber(int rN) {
		this.roomNumber = rN;
	}
	public int getRoomNumber( ) {
		return this.roomNumber;
	}
	//
	private void consoleLog(String log) {
		System.out.println("[SERVER " + Thread.currentThread().getId() + " ] " + log);
	}
}
