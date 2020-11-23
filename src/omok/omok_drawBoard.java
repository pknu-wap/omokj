package omok;

import java.awt.*;
import javax.swing.*;

import omok_logic.*;

@SuppressWarnings("serial")
public class omok_drawBoard extends JPanel {
	
	private OmokImpl oi;
	
	public omok_drawBoard(OmokImpl oi) {
		setBackground(new Color(206,167,61));
		setSize(500,500);
		setLayout(null);
		this.oi = oi;
		addMouseListener(new omok_MouseEvent(this, this.oi));
	}
	
	public void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		arg0.setColor(Color.BLACK); //그려질 색을 지정
		board(arg0); //board 함수 이용해서 그림 보드판 그림 
		drawStone(arg0); //오목알 그림
	}
	
	public void board(Graphics arg0) {
		for(int i=1;i<=19;i++){
			arg0.drawLine(25, i*25, 25*19, i*25); //가로 줄 그리기
			arg0.drawLine(i*25, 25, i*25 , 25*19); //세로줄 그리기
			}
	}
	
	public void drawStone(Graphics arg0) {
		for(int y=0; y<18; y++) {
			for(int x=0; x<18; x++) {
				if(oi.omok[y][x]==1)
					drawBlack(arg0,x,y);
				if(oi.omok[y][x]==2){
					drawWhite(arg0,x,y);
				}
			}
		}
	}
	
	public void drawBlack(Graphics arg0,int x,int y){
		//그려질 색을 블랙으로 바꿈
		arg0.setColor(Color.BLACK);
		arg0.fillOval(x*25+18, y*25+18, 15, 15);
	}

	public void drawWhite(Graphics arg0,int x,int y){
		//그려질 색을 화이트로 바꿈
		arg0.setColor(Color.WHITE);
		arg0.fillOval(x*25+18, y*25+18, 15, 15);
	}
	
}
