package omokJServer;


public class OmokRoomManager {
	OmokRoom[] room; // ���ȣ�� 1�� ���� �����ؼ� �ʱ�ȭ
	public OmokRoomManager(OmokRoom[] room) {
		this.room = room;
		for(int i = 0; i < room.length; i++) {
			room[i].roomNumber = i+1;
		}
	}
	
	// sCP���� �濡 ����� ��� ������ �ű⼭ �� �޼ҵ� ����
	public int joinRoom (ServerClientProcessor player, int rN){
		if(this.room[rN-1].curPlayers >= 2) { // rN�� ���� ���� �ο����� 2���� ũ�ų� ������ ���� 
			return 0;
		}
		player.setRoomNumber(rN);
		if(this.room[rN-1].player[0] == null) this.room[rN-1].player[0] = player;
		else this.room[rN-1].player[1] = player;
		return rN; // �濡 ������ �� ��ȣ ���� �޾Ƽ� player�� roomNumber�� ����
	}
	// �÷��̾ �濡�� ���� ��� 
	public void quitRoom (ServerClientProcessor player,int rN) {
		if(player.getRoomNumber() == 0) return; // �̹� �ƹ� �濡�� �ȵ� �ִ� ��� �׳� ����
		if(this.room[rN-1].player[0] == player) this.room[rN-1].player[0] = null;
		else if(this.room[rN-1].player[1] == player) this.room[rN-1].player[1] = null;
		return;
	}
	
	// ���� getter setter ��
	public int getRoomsNum ( ) {
		return room.length;
	}
	public OmokRoom[] getRoomRef ( ) {
		return room;
	}
	
	// ���� �� ������ ���� Room ��ü
	public class OmokRoom {
		int roomNumber; // 1 ~ 5
		
		boolean gameStarted = false;
		
		int curPlayers = 0;
		ServerClientProcessor[] player = {null, null};
		
		int nextPlayerIndex = 0; // ���� ���� �÷��̾��� �ε��� +1 �ϰ� %2 �ذ��� �� ��ȯ
 
		boolean gameOver = false;
		ServerClientProcessor winner = null;
		
		private OmokRoom (int rN) {
			this.roomNumber = rN;
		}
	}
}
