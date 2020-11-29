package omok_logic;

public class omok_logicSet {
	private int[][] omok;
	
	static int BLACK; // 흑은 1
	static int WHITE; // 백은 2
	
	private boolean turn;	//true = 흑, false = 백
	private int state; // 0 = 못놓음, 1=가능, 2=승리
	
	private omok_baseCheck bch = new omok_baseCheck();
	
	public omok_logicSet() {
		omok = new int[18][18];
		BLACK = 1;
		WHITE = 2;
		turn = true;
		state = 1;
		
	}
	
	public int getXY(int y, int x) { 
		return omok[y][x];
	}
	
	public boolean getTurn() {
		return turn;
	}
	
	public int getstate() {
		return state;
	}
	
	public int omokCheck(int x, int y) {
		int chx = x;
		int chy = y;
		
		state = bch.omok_bCheck(this, chx, chy);
		
		if(state ==1) {
			if(turn==true)
				omok[y][x]=BLACK;
			else
				omok[y][x]=WHITE;
			
			turn = !turn;
		}

		return state;
	}
	
}