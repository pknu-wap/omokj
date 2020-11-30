package omokJServer;

import omokJServer.OmokRoomManager.OmokRoom;

public class OmokRoomManager {
	private final static int MAX_ROOMS = 5+1;
	public OmokRoom[] room = new OmokRoom[MAX_ROOMS]; // roomNumber : 1 ~
	public OmokRoomManager() {
		for(int i = 0; i < room.length; i++) {
			this.room[i] = new OmokRoom(i);
		}
	}
	
	public int joinRoom (ServerCommProcessor player, int rN){
		if(rN<1 || rN> 5|| room[rN].curPlayers >= 2) { // Strange room or exceed
			rN = 0;
			return rN;
		}
		else if(room[rN].player[0] == null) { // p1 empty
			room[rN].player[0] = player;		 // p1 regist
		}
		else if(room[rN].player[1] == null) { // p2 empty
			room[rN].player[1] = player;		 // p2 regist
		}
		else {
			rN = 0;
			return rN;
		}
		room[rN].curPlayers++;
		return rN;
	}
	
	public void quitRoom (ServerCommProcessor player,int rN) {
		
	}
	
	// getter setter 
	public int getRoomsNum ( ) {
		return room.length;
	}
	public OmokRoom[] getRoomRef ( ) {
		return room;
	}
	
	// Omok Room
	public class OmokRoom {
		int roomNumber; // 1 ~ 5
		boolean gameStarted = false;
		boolean p1Ready = false; // both are true, then startOmok
		boolean p2Ready = false;
		int curPlayers = 0;
		ServerCommProcessor[] player = {null, null};
		int nextPlayerIndex = 0; // Next Player + 1 % 2
 
		boolean gameOver = false;
		ServerCommProcessor winner = null;
		
		private OmokRoom (int rN) {
			this.roomNumber = rN;
			this.player[0] = null;
			this.player[1] = null;
		}
	}
}
