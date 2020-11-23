package omok;

import java.awt.*;
import javax.swing.*;

import clientCommunication.*;
import omokJServer.*;

public class omok_showRoom extends JPanel{
	
	private int[] roomNumber;
	private String[] player1;
	private String[] player2;
	
	private getShowRoomList list;
	
	public omok_showRoom(ClientCommProcessor ccp) {
		setSize(500,500);
		setLayout(null);
		setBackground(Color.WHITE);
		
		ccp.run();
		
		roomNumber = list.roomNumber;
		player1 = list.player1;
		player2 = list.player2;
		
		JButton[] room = new JButton[roomNumber.length];
		
		for(int i=0; i<5; i++) { //방 갯수... 받아서 쓰는 지 아니면, 5개를 MAX로 하는 지 모르겠어요.
			room[i] = new JButton(roomNumber[i]+player1[i]+player2[i]);
			room[i].setSize(500,30);
			room[i].setLocation(0,i*30);
			add(room[i]);
		}
		
	}
	
}
