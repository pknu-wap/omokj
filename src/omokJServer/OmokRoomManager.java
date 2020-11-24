package omokJServer;

import omokJServer.OmokRoomManager.OmokRoom;

public class OmokRoomManager {
	private final static int MAX_ROOMS = 5;
	OmokRoom[] room = new OmokRoom[MAX_ROOMS]; // roomNumber : 1 ~
	public OmokRoomManager() {
		for(int i = 0; i < room.length; i++) {
			this.room[i] = new OmokRoom(i+1);
		}
	}
	
	public int joinRoom (ServerCommProcessor player, int rN){
		if(this.room[rN-1].curPlayers >= 2) { // deny if the nuber of players >= 2 
			return 0;
		}
		player.setRoomNumber(rN);
		if(this.room[rN-1].player[0] == null) this.room[rN-1].player[0] = player;
		else this.room[rN-1].player[1] = player;
		this.room[rN-1].curPlayers++;
		return rN; // if got in room then, save roomNum return value to player's roomNum 
	}
	
	public void quitRoom (ServerCommProcessor player,int rN) {
		if(player.getRoomNumber() == 0) return; // �̹� �ƹ� �濡�� �ȵ� �ִ� ��� �׳� ����
		if(this.room[rN-1].player[0] == player) this.room[rN-1].player[0] = null;
		else if(this.room[rN-1].player[1] == player) this.room[rN-1].player[1] = null;
		return;
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
		boolean p1Ready = false; // �� �� true�� startOmok
		boolean p2Ready = false;
		int curPlayers = 0;
		ServerCommProcessor[] player = {null, null};
		int nextPlayerIndex = 0; // ���� ���� �÷��̾��� �ε��� +1 �ϰ� %2 �ذ��� �� ��ȯ
 
		boolean gameOver = false;
		ServerCommProcessor winner = null;
		
		private OmokRoom (int rN) {
			this.roomNumber = rN;
		}
	}
}
