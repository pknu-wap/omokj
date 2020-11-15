package omok;

import java.awt.event.*;

public class omok_MouseEvent extends MouseAdapter {
	
	private omok_drawBoard b;
	
	public omok_MouseEvent() {
		
	}
	
	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
		int x = (int)Math.round(arg0.getX()/(double)25)-1;
		int y = (int)Math.round(arg0.getY()/(double)25)-1;
		if(x<0 || x>19 || y<0 || y>19) {
			return;
		}
		
		//지금 여기 필요한 건 x,y좌표 넘겨주는 방법, 그리고 이미 놓여져 있는 곳이면 안놓이게 하는 거
	}
	
}
