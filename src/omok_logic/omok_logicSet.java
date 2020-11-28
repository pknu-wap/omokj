package omok_logic;

public class omok_logicSet {
	private int[][] omok;
	
	static private int BLACK; //흑,백을 나타내는 인수 설정 -->민준님께 여쭤보기
	static private int WHITE;
	
	private boolean turn;	//true = 흑, false = 백
	private boolean state; //true = 놓을 수 있음, false = 못놓음
	
	public omok_logicSet() {
		omok = new int[18][18];
		BLACK = 1;
		WHITE = -1;
		turn = true;
		state = true;
		
	}
	
	public int getXY(int y, int x) {
		return omok[y][x];
	}
	
	public boolean getTurn() {
		return turn;
	}
	
	public boolean getstate() {
		return state;
	}
	
	public void omokCheck(int x, int y, boolean turn) {
		int chx = x;
		int chy = y;
		boolean chturn = turn;
		
	}
	
}