package clientCommunication;

import java.io.*;

// This class contains everything about transfer information

public class TransferObj implements Serializable {
	public enum Opcode { 
		joinServer, joinRoom, turnOver, gameOver, // 클라이언트 측 통신 명령
		showRoomList, showRoom, startOmok, deliverTurn //서버 측 통신 명령
	}
	private Opcode opcode; 
	
	public Opcode getOpcode() {
		return opcode;
	}
	// 각 통신 명령 마다 전달할 정보 
	// CLIENT
	public class JoinServer {
		String nickname;
	}
	public class JoinRoom {
		
	}
	public class Turnover {
	
	}
	public class Gameover {
		
	}
    // SERVER
	public class ShowRoomList {
		
	}
	public class ShowRoom {
		
	}
	public class StartOmok {
		
	}
	public class DeliverTurn {
		
	}
}
