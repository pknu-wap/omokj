package omokJServer;

import java.io.*;
import java.util.ArrayList;

// This class contains everything about transfer information

public class TransferObj implements Serializable {
	public enum Opcode { 
		joinServer, joinRoom, turnOver, gameOver, // Ŭ���̾�Ʈ �� ��� ���
		denyEntry, showRoomList, showRoom, startOmok, deliverTurn //���� �� ��� ���
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
	
	/* �� ��ɿ� ���� ���� ��ü ������ */
	public TransferObj(Opcode opcode) {
		this.opcode = opcode;
	}
	
	// getter setter
	public Opcode getOpcode() {
		return opcode;
	}
	
	// �� ��� ��� ���� ������ ���� 
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
	public class ShowRoomList { // ��ü ���ڴ� ������ �����ϱ� ������ ���� ���� ���� ����!!!!!!!!!
		int[] roomNumbers; // �� ���� ���ȣ, ����ִ� ��� �г��� (�� ĭ�� null�� ����)
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
