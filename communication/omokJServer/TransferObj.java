package omokJServer;

import java.io.*;

// This class contains everything about transfer information

public class TransferObj implements Serializable {
	public enum Opcode { 
		joinServer, joinRoom, turnOver, gameOver, // 클라이언트 측 통신 명령
		denyEntry, showRoomList, showRoom, startOmok, deliverTurn //서버 측 통신 명령
	}
	private Opcode opcode; 
	JoinServer joinServer = null;
	JoinRoom joinRoom = null;
	TurnOver turnOver = null;
	GameOver gameOver = null;
	
	ShowRoomList showRoomList = null;
	ShowRoom showRoom = null;
	StartOmok startOmok = null;
	DeliverTurn deliverTurn = null;
	DenyEntry denyEntry = null;
	
	/* 각 명령에 대한 전송 객체 생성자 */
	public TransferObj(JoinServer  jS) {
		this.opcode = Opcode.joinServer;
		this.joinServer = jS;
	}
	public TransferObj(JoinRoom jR) {
		this.opcode = Opcode.joinRoom;
		this.joinRoom = jR;
	}
	public TransferObj(TurnOver tO) {
		this.opcode = Opcode.turnOver;
		this.turnOver = tO;
	}
	public TransferObj(GameOver gO) {
		this.opcode = Opcode.gameOver;
		this.gameOver = gO;
	}
	public TransferObj(ShowRoomList sRL) {
		this.opcode = Opcode.showRoomList;
		this.showRoomList = sRL;
	}
	public TransferObj(ShowRoom sR) {
		this.opcode = Opcode.showRoom;
		this.showRoom = sR;
	}
	public TransferObj(StartOmok sO) {
		this.opcode = Opcode.startOmok;
		this.startOmok = sO;
	}
	public TransferObj(DeliverTurn dT) {
		this.opcode = Opcode.deliverTurn;
		this.deliverTurn = dT;
	}
	public TransferObj(DenyEntry dE) {
		this.opcode = Opcode.denyEntry;
		this.denyEntry = dE;
	}
	
	public Opcode getOpcode() {
		return opcode;
	}
	
	// 각 통신 명령 마다 전달할 정보 
	// CLIENT
	public class JoinServer {
		
	}
	public class JoinRoom {
		
	}
	public class TurnOver {
	
	}
	public class GameOver {
		
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
	public class DenyEntry {
		
	}
}
