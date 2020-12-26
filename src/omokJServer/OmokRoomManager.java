package omokJServer;


public class OmokRoomManager {
	private final static int MAX_ROOMS = 5+1;
	public OmokRoom[] room = new OmokRoom[MAX_ROOMS]; // roomNumber : 1 ~
	
	public OmokRoomManager() {
		for(int i = 0; i < room.length; i++) {
			this.room[i] = new OmokRoom(i);
		}
	}
	
	public int joinRoom (ServerCommProcessor player, int rN){
		if(rN<1 || rN> 5|| room[rN].getPlayersNum() >= 2) { // Strange room or exceed
			rN = 0;
			return rN;
		}
		else if(room[rN].player[0] == null) { // p1 empty
			room[rN].player[0] = player;		 // p1 regist
			player.playerIdx = 0;
		}
		else if(room[rN].player[1] == null) { // p2 empty
			room[rN].player[1] = player;		 // p2 regist
			player.playerIdx = 1;
		}
		else {
			rN = 0;
			return rN;
		}
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
		ServerCommProcessor[] player = {null, null};
		
		omok_logicSet board; // board.omokCheck(x, y)
		
		boolean gameStarted = false;
		boolean[] playerReady = {false, false};
		int nextPlayerIndex = 0; // Next Player + 1 % 2
		
		boolean gameOver = false;
		ServerCommProcessor winner = null;
		
		public OmokRoom (int rN) {
			this.roomNumber = rN;
			this.player[0] = null;
			this.player[1] = null;
			
			this.board = new omok_logicSet();
		}
		public int getPlayersNum () {
			if (player[0] == null && player[1] == null) {
				return 0;
			}
			else if((player[0] == null && player[1] != null)||(player[0] != null && player[1] == null)) {
				return 1;
			}
			else {
				return 2;
			}
		}
	}
}
