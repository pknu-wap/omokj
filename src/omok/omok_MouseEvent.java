package omok;

import java.awt.event.*;

import clientCommunication.ClientCommProcessor;
import omokJServer.omok_logicSet;

import java.awt.*;


public class omok_MouseEvent extends MouseAdapter {
	
	private omok_drawBoard b;
	ClientCommProcessor ccp;
	
	public omok_MouseEvent(ClientCommProcessor ccomp, omok_drawBoard b) {
		this.b = b;
		this.ccp = ccomp;
	}
	

	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
		int x = (int)Math.round(arg0.getX()/(double)25)-1;
		int y = (int)Math.round(arg0.getY()/(double)25)-1;
		if(x<0 || x>18 || y<0 || y>18) {
			return;
		} // 클릭 되는 경우 해당 위치 x,y 값
		
		//int check = ls.omokCheck(x,y);
		if(ccp.placeAuth == true) { // x,y 값을 서버로 전달
			ccp.sendStone(y,x);
			ccp.placeAuth = false;
		}
		
		b.repaint();
		/*
		if(check==1) {
			b.repaint();
		}
		else if(check==2) {
			b.repaint();
			System.out.println("GUI�뿉�꽌 �듅由� �솗�씤");
			//�듅由ы몴�떆 硫붿냼�뱶
		}
		*/
	}
	
}
