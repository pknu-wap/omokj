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
	public int playerIdx = 0;
	
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
			
			while (true) {
				Opcode opcode = (Opcode)is.readObject();
				// operation process
				switch(opcode) {
				case joinServer: // showRoomList
					if(clientList.size()>MAX_USERS) {
						denyEntr();
						return;
					}
					else {
					this.nickname = (String)is.readObject();
					clientList.add(this);
					consoleLog(this.nickname + " Connected. [Connected : " + clientList.size() + " ]");
					showRoomList();
					}
					break;
				case joinRoom: 
					int roomNum = (int)is.readObject();
					roomNum = roomManager.joinRoom(this, roomNum);
					if (roomNum == 0) 
						this.showRoom(roomNum);
					else {
					if(roomManager.room[roomNum].player[0] != null) //  p1 exists, then showRoom
						roomManager.room[roomNum].player[0].showRoom(roomNum);
					if(roomManager.room[roomNum].player[1] != null) // p2 exists, then showRoom
						roomManager.room[roomNum].player[1].showRoom(roomNum);
					}
					if(this.roomNumber != 0) consoleLog(this.nickname + " joined Room [" + this.roomNumber + "]");
					this.roomNumber = roomNum;
					break;
				case getReady:
					roomManager.room[this.roomNumber].playerReady[this.playerIdx] = !roomManager.room[this.roomNumber].playerReady[this.playerIdx];
					
					if(roomManager.room[this.roomNumber].playerReady[0]==true && roomManager.room[this.roomNumber].playerReady[1] ==true) {
						if(roomManager.room[this.roomNumber].getPlayersNum()>=2) { // START omok
							if((roomManager.room[this.roomNumber].playerReady[0]==true && roomManager.room[this.roomNumber].playerReady[1] ==true)&&roomManager.room[this.roomNumber].getPlayersNum()>=2)
								roomManager.room[this.roomNumber].startOmok();
						}
					}
					else {
					if(roomManager.room[this.roomNumber].player[0] != null) //  p1 exists, then showRoom
						roomManager.room[this.roomNumber].player[0].showRoom(this.roomNumber);
					if(roomManager.room[this.roomNumber].player[1] != null) // p2 exists, then showRoom
						roomManager.room[this.roomNumber].player[1].showRoom(this.roomNumber);
					}
					break;
				case quitRoom:
					if(this.roomNumber == 0) {
						showRoomList();
					}
					else if(this.roomNumber >= 1 && this.roomNumber <=5) {
						consoleLog(this.nickname + " quit Room [" + this.roomNumber+ "]");
						roomManager.room[this.roomNumber].playerReady[this.playerIdx] = false;
						roomManager.room[this.roomNumber].gameStarted = false;
						roomManager.room[this.roomNumber].player[this.playerIdx] = null;
						showRoomList();
					}
					else {
						showRoomList();
					}
					if(roomManager.room[this.roomNumber].player[(this.playerIdx+1)%2]!=null)
						roomManager.room[this.roomNumber].player[(this.playerIdx+1)%2].showRoom(this.roomNumber);
					this.playerIdx = -1;
					this.roomNumber = 0;
					break;
				case sendStone:
					if(roomManager.room[this.roomNumber].player[(this.playerIdx+1)%2]==null) {
						roomManager.room[this.roomNumber].gameStarted = false;
						roomManager.room[this.roomNumber].board = null;
						roomManager.room[this.roomNumber].winner = this;
						this.playerIdx = -1;
						this.winner();
						break;
					}
					int x = (int)is.readObject();
					int y = (int)is.readObject();
					this.placeStone(x, y);
					break;
				default:
					break;
				}
			}
		}
		catch(IOException e) {
			clientList.remove(this);
			consoleLog(this.nickname + " lost connect. [Connected : " + clientList.size() + " ]");
			if(this.roomNumber >= 1 && this.roomNumber <=5) {
				consoleLog(this.nickname + " quit Room [" + this.roomNumber+ "]");
				if(roomManager.room[this.roomNumber].player[(this.playerIdx+1)%2]!=null)
					roomManager.room[this.roomNumber].player[(this.playerIdx+1)%2].showRoom(this.roomNumber);
				roomManager.room[this.roomNumber].playerReady[this.playerIdx] = false;
				roomManager.room[this.roomNumber].gameStarted = false;
				roomManager.room[this.roomNumber].player[this.playerIdx] = null;
				roomManager.room[this.roomNumber].board = null;
				roomManager.room[this.roomNumber].winner = this;
				this.playerIdx = -1;
				showRoomList();
			}
		} catch (ClassNotFoundException e) {
			clientList.remove(this);
			consoleLog(this.nickname + " lost connect. [Connected : " + clientList.size() + " ]");
			if(this.roomNumber >= 1 && this.roomNumber <=5) {
				consoleLog(this.nickname + " quit Room [" + this.roomNumber+ "]");
				if(roomManager.room[this.roomNumber].player[(this.playerIdx+1)%2]!=null)
					roomManager.room[this.roomNumber].player[(this.playerIdx+1)%2].showRoom(this.roomNumber);
				roomManager.room[this.roomNumber].playerReady[this.playerIdx] = false;
				roomManager.room[this.roomNumber].gameStarted = false;
				roomManager.room[this.roomNumber].player[this.playerIdx] = null;
				roomManager.room[this.roomNumber].board = null;
				roomManager.room[this.roomNumber].winner = this;
				this.playerIdx = -1;
				showRoomList();
			}
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
	private void denyEntr() {
		Opcode opcode = Opcode.denyEntry;
		try {
			os.writeObject(opcode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void showRoomList( ) {
		Opcode opcode = Opcode.showRoomList;
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
	
	private void showRoom(int rN) { //
		Opcode opcode = Opcode.showRoom;
		try {
			os.writeObject(opcode);
			os.writeObject(rN);
			if(rN != 0) {
				if(roomManager.room[rN].player[0] == null) os.writeObject(" ");
				else os.writeObject(roomManager.room[rN].player[0].nickname);
				if(roomManager.room[rN].player[1] == null) os.writeObject(" ");
				else os.writeObject(roomManager.room[rN].player[1].nickname);
				boolean[] rdy = roomManager.room[rN].playerReady.clone();
				os.writeObject(rdy);
			}
			else {
				this.showRoomList();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void turnOver() {
		roomManager.room[this.roomNumber].player[(this.playerIdx+1)%2].notifyBoard();
		
		Opcode opcode = Opcode.turnOver;
		try {
			os.writeObject(opcode);
			this.notifyBoard();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void notifyBoard() {
		Opcode opcode = Opcode.notifyBoard;
		
		int[][] board = new int[19][19];
		for(int i = 0; i < 19; i++) {
			board[i] = (roomManager.room[this.roomNumber].getBoard())[i].clone();
		}
		try {
			os.writeObject(opcode);
			os.writeObject(board);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void winner() {
		roomManager.room[this.roomNumber].playerReady[this.playerIdx] = false;
		roomManager.room[this.roomNumber].gameStarted = false;
		roomManager.room[this.roomNumber].player[this.playerIdx] = null;
		roomManager.room[this.roomNumber].board = null;
		roomManager.room[this.roomNumber].winner = null;
		Opcode opcode = Opcode.winner;
		try {
			os.writeObject(opcode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loser() {
		roomManager.room[this.roomNumber].playerReady[this.playerIdx] = false;
		roomManager.room[this.roomNumber].gameStarted = false;
		roomManager.room[this.roomNumber].player[this.playerIdx] = null;
		roomManager.room[this.roomNumber].board = null;
		roomManager.room[this.roomNumber].winner = null;
		Opcode opcode = Opcode.loser;
		try {
			os.writeObject(opcode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	//
	private void placeStone(int x, int y) {
		int r = roomManager.room[this.roomNumber].placeStone(this.playerIdx, x, y);
		if( r == 2) { //Game Over
			roomManager.room[this.roomNumber].winner.winner();
			roomManager.room[this.roomNumber].player[(this.playerIdx+1)%2].loser();
			
		} 
		else if (r == -1) { //Wrong Player
			
		}
		else { 
		}
	}
	
	public void startOmok() {
		consoleLog(this.roomNumber + " Started omok");
		Opcode opcode = Opcode.startOmok;
		try {
			os.writeObject(opcode);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void consoleLog(String log) {
		System.out.println("[SERVER " + Thread.currentThread().getId() + " ] " + log);
	}
}
