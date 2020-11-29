package omokJServer;

import java.io.*;
import java.util.ArrayList;

// This class contains everything about transfer information

public class TransferObj implements Serializable {
	public static enum Opcode implements Serializable { 
		joinServer, joinRoom, turnOver, gameOver, // for Client
		denyEntry, showRoomList, showRoom, startOmok, deliverTurn // for Server
	}
	
	// CLIENT
	public static class JoinServer implements Serializable {
		private final long serialVersionUID = 10000L;
		public static String nickname;
	}
	public static class JoinRoom implements Serializable {
		private static final long serialVersionUID = 10000L;
		public static int roomNumber;
	}
	public static class TurnOver implements Serializable {
		private static final long serialVersionUID = 10000L;
	
	}
    // SERVER
	public static class ShowRoomList implements Serializable { 
		private static final long serialVersionUID = 10000L;
		public static int[] roomNumbers = new int[5]; // empty slot = null
		public static String[] player1 = new String[5];
		public static String[] player2 = new String[5];
	}
	public static class ShowRoom implements Serializable { 
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
