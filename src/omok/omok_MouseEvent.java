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
		
		int check = ls.omokCheck(x,y);
		
		if(check==1) {
			b.repaint();
		}
		else if(check==2) {
			b.repaint();
			System.out.println("GUI에서 승리 확인");
			//승리표시 메소드
		}
			
	}
	
}
