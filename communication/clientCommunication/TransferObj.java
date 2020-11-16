package clientCommunication;

import java.io.*;

// This class contains everything about transfer information

public class TransferObj implements Serializable {
	public enum Opcode { 
		joinServer, joinRoom, turnOver, gameOver, // Ŭ���̾�Ʈ �� ��� ���
		showRoomList, showRoom, startOmok, deliverTurn //���� �� ��� ���
	}
	private Opcode opcode; 
	
	public Opcode getOpcode() {
		return opcode;
	}
	// �� ��� ��� ���� ������ ���� 
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
