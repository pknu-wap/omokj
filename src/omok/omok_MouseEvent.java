package omok;

import java.awt.event.*;
import java.awt.*;
import omok_logic.*;

public class omok_MouseEvent extends MouseAdapter {
	
	private omok_drawBoard b;
	private omok_logicSet ls;
	
	public omok_MouseEvent(omok_drawBoard b, omok_logicSet ls) {
		this.b = b;
		this.ls = ls;
	}
	

	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
		int x = (int)Math.round(arg0.getX()/(double)25)-1;
		int y = (int)Math.round(arg0.getY()/(double)25)-1;
		if(x<0 || x>19 || y<0 || y>19) {
			return;
		}
		//
		//
		if(ls.omokCheck(x,y)==1)
			b.repaint();
		else if(ls.omokCheck(x,y)==2) {
			b.repaint();
			//승리표시 메소드
		}
			
		//필요: x,y좌표 넘겨주는 방법, 그리고 이미 놓여져 있는 곳이면 안놓이게 하는 거
	}
	
}
