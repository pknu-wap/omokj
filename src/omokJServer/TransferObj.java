package omokJServer;

import java.io.*;
import java.util.ArrayList;

// This class contains everything about transfer information

public class TransferObj {
	public static enum Opcode implements Serializable { 
		joinServer, joinRoom, turnOver, gameOver, // 클라이언트 측 통신 명령
		denyEntry, showRoomList, showRoom, startOmok, deliverTurn //서버 측 통신 명령
	}
	
	// 각 통신 명령 마다 전달할 정보 
	// CLIENT
	public static class JoinServer implements Serializable {
		private static final long serialVersionUID = 10000L;
		public static String nickname = null;
	}
	public static class JoinRoom implements Serializable {
		private static final long serialVersionUID = 10000L;
		public static int roomNumber;
	}
	public static class TurnOver implements Serializable {
		private static final long serialVersionUID = 10000L;
	
	}
    // SERVER
	public static class ShowRoomList implements Serializable { // 객체 인자는 참조를 전달하기 때문에 값을 새로 복붙 해줌!!!!!!!!!
		private static final long serialVersionUID = 10000L;
		public static int[] roomNumbers = new int[5]; // 각 방의 방번호, 들어있는 사람 닉네임 (빈 칸은 null로 전달)
		public static String[] player1 = new String[5];
		public static String[] player2 = new String[5];
	}
	public static class ShowRoom implements Serializable { // 들어간 방 번호, 들어있는 사람 닉네임이 들어있음.
		private static final long serialVersionUID = 10000L;
		public static int roomNumber;
		public static String player1;
		public static String player2;
	}
	public static class StartOmok implements Serializable {
		private static final long serialVersionUID = 10000L;
		
	}
	public static class DeliverTurn implements Serializable {
		private static final long serialVersionUID = 10000L;
		
	}
	
	public static class DenyEntry implements Serializable {
		private static final long serialVersionUID = 10000L;
	}
}
