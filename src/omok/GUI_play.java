package omok;

import java.awt.*;
import javax.swing.*;

import clientCommunication.ClientCommProcessor;
import omokJServer.omok_logicSet;

@SuppressWarnings("serial")
public class GUI_play extends JPanel {
	
	//private omok_logicSet ls = new omok_logicSet();
	
	public GUI_play(ClientCommProcessor ccomp) {
		setSize(700,700);
		setLayout(null);
		
		JLabel info = new JLabel("INFO"); 
		info.setLocation(300,15);
		info.setSize(100,20);
		
		omok_drawBoard board = new omok_drawBoard(ccomp); //
		
		ccomp.setDrawBoard(board);
		
		board.setLocation(100,50);
		board.setSize(500,500);
		
		JLabel player1 = new JLabel(ccomp.player[0]);
		player1.setLocation(130,560);
		player1.setSize(100,30);
		
		JLabel player2 = new JLabel(ccomp.player[1]); 
		player2.setLocation(550,560);
		player2.setSize(100,30);
		
		
		JButton exit = new JButton("종료");
		exit.addActionListener(e->{System.exit(0);}); 
		exit.setLocation(300,600);
		exit.setSize(100,30);
		
		
		add(info);
		add(board);
		add(player1);
		add(player2);
		add(exit);
		setVisible(true);
	}
	
	public void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		arg0.setColor(Color.BLACK);
		drawStone(arg0, 107, 567);
		arg0.setColor(Color.WHITE);
		drawStone(arg0, 523, 567);
	}
	
	public void drawStone(Graphics arg0, int x, int y) {
		arg0.fillOval(x, y, 15, 15);
	}
}