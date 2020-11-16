package omokJServer;

import java.io.*;
import java.util.ArrayList;

// This class contains everything about transfer information

public class TransferObj implements Serializable {
	public enum Opcode { 
		joinServer, joinRoom, turnOver, gameOver, // 클라이언트 측 통신 명령
		denyEntry, showRoomList, showRoom, startOmok, deliverTurn //서버 측 통신 명령
	}
	private Opcode opcode; 
	public JoinServer joinServer = null;
	public JoinRoom joinRoom = null;
	public TurnOver turnOver = null;
	
	public ShowRoomList showRoomList = null;
	public ShowRoom showRoom = null;
	public StartOmok startOmok = null;
	public DeliverTurn deliverTurn = null;
	public DenyEntry denyEntry = null;
	
	/* 각 명령에 대한 전송 객체 생성자 */
	public TransferObj(Opcode opcode) {
		this.opcode = opcode;
	}
	
	// getter setter
	public Opcode getOpcode() {
		return opcode;
	}
	
	// 각 통신 명령 마다 전달할 정보 
	// CLIENT
	public class JoinServer {
		String nickname = null;
		public JoinServer(String nickname) {
			this.nickname = nickname;
		}
		public String getNickname() {
			return nickname;
		}
	}
	public class JoinRoom {
		
	}
	public class TurnOver {
	
	}
    // SERVER
	public class ShowRoomList { // 객체 인자는 참조를 전달하기 때문에 값을 새로 복붙 해줌!!!!!!!!!
		int[] roomNumbers; // 각 방의 방번호, 들어있는 사람 닉네임 (빈 칸은 null로 전달)
		String[] player1;
		String[] player2;
		public ShowRoomList(int[] rNs, String[] p1, String[] p2) {
			roomNumbers = new int[rNs.length];
			player1 = new String[rNs.length];
			player2 = new String[rNs.length];
			for(int i = 0; rNs.length < 10; i++) {
				this.roomNumbers[i] = rNs[i];
				this.player1[i] = p1[i];
				this.player2[i] = p2[i];
			}
		}
	}
	public class ShowRoom {
		
	}
	public class StartOmok {
		
	}
	public class DeliverTurn {
		
	}
	public class DenyEntry {
		
	}
}
