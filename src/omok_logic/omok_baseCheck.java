package omok_logic;

public class omok_baseCheck {
	
	private omok_logicSet ls;
	
	private int chx;
	private int chy;
	private boolean chturn;
	private int count;
	
	public omok_baseCheck() {
		chx = 0;
		chy = 0;
		chturn = true;
		count =0;
	}
	
	public int omok_bCheck(omok_logicSet LS, int x, int y) {
			ls = LS;
			chx = x;
			chy = y;
			chturn = ls.getTurn();
			
			if(chturn == false) {
				while(ls.getXY(chx,chy) == ls.WHITE && chx > 0) {
					chx--;
				}
				while(ls.getXY(chx,chy++) == ls.WHITE && chx <18) {
					count++;	
				}
				if(count == 5)
					return 2;
	
				
				// 세로 체크 코드
				chx = x;
				chy = y;
				count = 0;
				while(ls.getXY(chx,chy) == ls.WHITE && chy > 0) {
					chy--;
				}
				while(ls.getXY(chx++,chy) == ls.WHITE && chy <18) {
					count++;
				
				}
				if(count == 5)
					return 1;
		
				
				// 대각선 ↘
				chx = x;
				chy = y;
				count = 0;
				while(ls.getXY(chx,chy) == ls.WHITE && chy > 0 && chx > 0) {
					chy--;
					chx--;
				}
				while(ls.getXY(chx++,chy++) == ls.WHITE && chy < 18 && chx < 18) {
					count++;
				
				}
				if(count == 5) {
					return 2;
				}
				
				
				// 대각선 ↗
				chx = x;
				chy = y;
				count = 0;
				while(ls.getXY(chx,chy) == ls.WHITE && chy > 0 && chx > 0) {
					chy++;
					chx--;
				}
				while(ls.getXY(chx--<=0?0:chx,chy++) == ls.WHITE && chy < 18 && chx < 18) {
					// 오목[][] 에서 y 값에 조건문을 넣은 이유는 a, 1 을 입력시 
					// y 값이 -1 로 음수값으로 넘어가면 오류를 출력해서 조정
					count++;
				}
				if(count == 5) {
					return 2;
				}
				
			}
			
			
			if(chturn == true) {
				while(ls.getXY(chx,chy) == ls.BLACK && chx > 0) {
					chx--;
				}
				while(ls.getXY(chx,chy++) == ls.BLACK && chx <18) {
					count++;	
				}
				if(count == 5)
					return 2;
	
				
				// 세로 체크 코드
				chx = x;
				chy = y;
				count = 0;
				while(ls.getXY(chx,chy) == ls.BLACK && chy > 0) {
					chy--;
				}
				while(ls.getXY(chx++,chy) == ls.BLACK && chy <18) {
					count++;
				
				}
				if(count == 5)
					return 1;
		
				
				// 대각선 ↘
				chx = x;
				chy = y;
				count = 0;
				while(ls.getXY(chx,chy) == ls.BLACK && chy > 0 && chx > 0) {
					chy--;
					chx--;
				}
				while(ls.getXY(chx++,chy++) == ls.BLACK && chy < 18 && chx < 18) {
					count++;
				
				}
				if(count == 5) {
					return 2;
				}
				
				
				// 대각선 ↗
				chx = x;
				chy = y;
				count = 0;
				while(ls.getXY(chx,chy) == ls.BLACK && chy > 0 && chx > 0) {
					chy++;
					chx--;
				}
				while(ls.getXY(chx--<=0?0:chx,chy++) == ls.BLACK && chy < 18 && chx < 18) {
					// 오목[][] 에서 y 값에 조건문을 넣은 이유는 a, 1 을 입력시 
					// y 값이 -1 로 음수값으로 넘어가면 오류를 출력해서 조정
					count++;
				}
				if(count == 5) {
					return 2;
				}
				
			}
			
			return 1;
			
	}
}
