package omokJServer;

import java.io.*;
import java.util.ArrayList;

// This class contains everything about transfer information

public class TransferObj implements Serializable {
	public static enum Opcode implements Serializable { 
		joinServer, joinRoom, turnOver, gameOver, // for Client
		denyEntry, showRoomList, showRoom, startOmok, deliverTurn // for Server
	}
	
	public static class TurnOver implements Serializable {
		private static final long serialVersionUID = 10000L;
	
	}
	public static class StartOmok implements Serializable {
		private static final long serialVersionUID = 10000L;
		
	}
	public static class DeliverTurn implements Serializable {
		private static final long serialVersionUID = 10000L;
		
	}
}
