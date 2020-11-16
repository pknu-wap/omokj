package omok;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class omok_drawBoard extends JPanel {
	
	public omok_drawBoard() {
		setBackground(new Color(206,167,61));
		setSize(500,500);
		setLayout(null);
		
	}
	
	public void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		arg0.setColor(Color.BLACK); //그려질 색을 지정
		board(arg0); //board 함수 이용해서 그림 보드판 그림 
	}
	
	public void board(Graphics arg0) {
		for(int i=1;i<=19;i++){
			//가로 줄 그리기
			arg0.drawLine(25, i*25, 25*19, i*25); //시작점 x : 30, 시작점 y : i값 (줄번호)*30, 끝점 x : 600,끝점 y : i값 (줄번호)*30
			//세로줄 그리기
			arg0.drawLine(i*25, 25, i*25 , 25*19); //시작점 x : i값 (줄번호)*30, 시작점 y : 30, 끝점 x : i값 (줄번호)*30, 끝점 y : 600
		}
	}
	
	public void drawStone(Graphics arg0, int x, int y) {
		//흑 또는 백을 나타내는 인수 만들어서 주시면 그걸로 판단하고 색 결정해서 그리는걸로
		arg0.fillOval(x, y, 15, 15);
	}
	
}
