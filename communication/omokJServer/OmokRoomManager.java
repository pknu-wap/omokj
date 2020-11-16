package omokJServer;


public class OmokRoomManager {
	OmokRoom[] room; // 방번호를 1번 부터 시작해서 초기화
	public OmokRoomManager(OmokRoom[] room) {
		this.room = room;
		for(int i = 0; i < room.length; i++) {
			room[i].roomNumber = i+1;
		}
	}
	
	// sCP에서 방에 들어가라는 명령 받으면 거기서 이 메소드 실행
	public int joinRoom (ServerClientProcessor player, int rN){
		if(this.room[rN-1].curPlayers >= 2) { // rN번 방의 현재 인원수가 2보다 크거나 같으면 거절 
			return 0;
		}
		player.setRoomNumber(rN);
		if(this.room[rN-1].player[0] == null) this.room[rN-1].player[0] = player;
		else this.room[rN-1].player[1] = player;
		return rN; // 방에 들어갔으면 방 번호 리턴 받아서 player의 roomNumber에 저장
	}
	// 플레이어가 방에서 나갈 경우 
	public void quitRoom (ServerClientProcessor player,int rN) {
		if(player.getRoomNumber() == 0) return; // 이미 아무 방에도 안들어가 있는 경우 그냥 리턴
		if(this.room[rN-1].player[0] == player) this.room[rN-1].player[0] = null;
		else if(this.room[rN-1].player[1] == player) this.room[rN-1].player[1] = null;
		return;
	}
	
	// 정보 getter setter 등
	public int getRoomsNum ( ) {
		return room.length;
	}
	public OmokRoom[] getRoomRef ( ) {
		return room;
	}
	
	// 오목 방 정보를 가진 Room 객체
	public class OmokRoom {
		int roomNumber; // 1 ~ 5
		
		boolean gameStarted = false;
		
		int curPlayers = 0;
		ServerClientProcessor[] player = {null, null};
		
		int nextPlayerIndex = 0; // 다음 턴인 플레이어의 인덱스 +1 하고 %2 해가며 턴 교환
 
		boolean gameOver = false;
		ServerClientProcessor winner = null;
		
		private OmokRoom (int rN) {
			this.roomNumber = rN;
		}
	}
}
