package omok;

import clientCommunication.*;
import omokJServer.*; 

public class getShowRoomList {
	int[] roomNumber;
	String[] player1;
	String[] player2;
	
	
	public getShowRoomList(int[] roomNumber, String[] player1, String[] player2) {
		this.roomNumber = roomNumber;
		this.player1 = player1;
		this.player2 = player2;
	}
}
