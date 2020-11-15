package omok;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GUI_play extends JPanel {
	
	public GUI_play() {
		setSize(700,700);
		setLayout(null);
		
		JLabel info = new JLabel("게임 정보 및 턴, 승패 표시"); //상단의 게임 정보 표시 위치
		info.setLocation(300,15);
		info.setSize(100,20);
		
		omok_drawBoard board = new omok_drawBoard(); //오목판 그리는 패널
		board.setLocation(100,50);
		board.setSize(500,500);
		
		JLabel player1 = new JLabel("Player1"); //플레이어 정보 표시 -->기입한 닉네임으로 할 지 말 지 결정필요
		player1.setLocation(130,560);
		player1.setSize(100,30);
		
		JLabel player2 = new JLabel("Player2"); //플레이어 정보 표시 -->기입한 닉네임으로 할 지 말 지 결정필요
		player2.setLocation(550,560);
		player2.setSize(100,30);
		
		
		JButton exit = new JButton("종료");// 서버 종료 버튼 
		exit.addActionListener(e->{System.exit(0);}); //종료 누르면 그냥 창 종료
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