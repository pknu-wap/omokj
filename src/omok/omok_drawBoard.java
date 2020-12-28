package omok;

import java.awt.*;
import javax.swing.*;

import clientCommunication.ClientCommProcessor;
import omokJServer.omok_logicSet;

@SuppressWarnings("serial")
public class omok_drawBoard extends JPanel {
	
	//private omok_logicSet ls;
	int[][] board;
	
	public omok_drawBoard(ClientCommProcessor ccomp, int[][] board) {
		setBackground(new Color(206,167,61));
		setSize(500,500);
		setLayout(null);
		this.board = board;
		addMouseListener(new omok_MouseEvent(ccomp, this));
	}
	
	public void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		arg0.setColor(Color.BLACK); 
		board(arg0); 
		drawStone(arg0);
	}
	
	public void board(Graphics arg0) {
		for(int i=1;i<=19;i++){
			arg0.drawLine(25, i*25, 25*19, i*25); 
			arg0.drawLine(i*25, 25, i*25 , 25*19); 
			}
	}
	
	public void drawStone(Graphics arg0) { // 여기서 ls (logicSet에서 배열 각 요소를 가져와서 돌들을 그림)
		for(int y=0; y<19; y++) {
			for(int x=0; x<19; x++) {
				if(board[x][y]==1)
					drawBlack(arg0,x,y);
				if(board[x][y]==2){
					drawWhite(arg0,x,y);
				}
			}
		}
	}
	
	public void drawBlack(Graphics arg0,int x,int y){
		arg0.setColor(Color.BLACK);
		arg0.fillOval(x*25+18, y*25+18, 15, 15);
	}

	public void drawWhite(Graphics arg0,int x,int y){
		arg0.setColor(Color.WHITE);
		arg0.fillOval(x*25+18, y*25+18, 15, 15);
	}
	
}
