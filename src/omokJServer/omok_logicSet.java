package omokJServer;
import java.util.Scanner;

public class omok_logicSet {
	private int[][] omok;
	
	static int BLACK=1; 
	static int WHITE=2; 
	
	private boolean turn;
	private int state; 
	
	private omok_baseCheck bch = new omok_baseCheck();
	private ForbiddenStone fbs = new ForbiddenStone();
	
	public omok_logicSet() {
		omok = new int[19][19];
		turn = true;
		state = 1;
	}
	
	public int getXY(int y, int x) {
		return omok[x][y];
	}
	
	public boolean getTurn() {
		return turn;
	}
	
	public int getState() {
		return state;
	}
	
	public int omokCheck(int x, int y) {
		int chx = x;
		int chy = y;
		
		if(getXY(chx, chy) == BLACK || getXY(chx, chy) == WHITE) {
			state = 0;
			return state;
		}
		state = fbs.samsam(this, chx, chy);
		if(state == 0) {
			return state;
		}
		state = fbs.jangmok(this, chx, chy);
		if(state == 0) {
			return state;
		}
		state = fbs.fiveStone(this, chx, chy);
		if(state == 0) {
			return state;
		}	
		
		state = bch.omok_bCheck(this, chx, chy);
		
		if(turn == true) 
			omok[chy][chx] = BLACK;
		else
			omok[chy][chx] = WHITE;
		
		System.out.println(state);
		
		turn = !turn;
		return state;
	}
}