package omok;

import java.awt.event.*;
import java.awt.*;
import omok_logic.*;

public class omok_MouseEvent extends MouseAdapter {
	
	private omok_drawBoard b;
	private OmokImpl oi;
	private int turn = 1;
	
	public omok_MouseEvent(omok_drawBoard b, OmokImpl oi) {
		this.b = b;
		this.oi = oi;
	}
	

	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
		int x = (int)Math.round(arg0.getX()/(double)25)-1;
		int y = (int)Math.round(arg0.getY()/(double)25)-1;
		if(x<0 || x>19 || y<0 || y>19) {
			return;
		}
		oi.OmokAction(x,y,turn);
		b.repaint();
		if(turn == 1)
			turn = 2;
		else
			turn = 1;
		//필요: x,y좌표 넘겨주는 방법, 그리고 이미 놓여져 있는 곳이면 안놓이게 하는 거
	}
	
}
