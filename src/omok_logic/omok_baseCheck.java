package omok_logic;

public class omok_baseCheck {
	
	private omok_logicSet ls;
	
	private int chx;
	private int chy;
	private boolean chturn;
	private int count;
	
	public int omok_bCheck(omok_logicSet LS, int x, int y) {
			ls = LS;
			chx = x;
			chy = y;
			count = 0;
			chturn = ls.getTurn();
			
			if(chturn == false) {
				while(chx > 0 && ls.getXY(chx-1,chy) == ls.WHITE) {
					chx--;
				}
				while(ls.getXY(chx++,chy) == ls.WHITE && chx <19) {
					count++;	
				}
				if(count == 4)
					return 2;
				
				
				chx = x;
				chy = y;
				count = 0;
				while(chy > 0 && ls.getXY(chx,chy-1) == ls.WHITE) {
					chy--;
				}
				while(ls.getXY(chx,chy++) == ls.WHITE && chy <19) {
					count++;
				
				}
				if(count == 4)
					return 2;
		
								
				chx = x;
				chy = y;
				count = 0;
				while(chy > 0 && chx > 0 && ls.getXY(chx-1,chy-1) == ls.WHITE) {
					chy--;
					chx--;
				}
				while(ls.getXY(chx++,chy++) == ls.WHITE && chy < 19 && chx < 19) {
					count++;
				}
				if(count == 4) {
					return 2;
				}
				
							
				chx = x;
				chy = y;
				count = 0;
				while(chy < 18 && chx > 0 && ls.getXY(chx-1,chy+1) == ls.WHITE) {
					chy++;
					chx--;
				}
				while(ls.getXY(chx++,chy--) == ls.WHITE && chy > -1 && chx < 19) {
					count++;
				}
				if(count == 4) {
					return 2;
				}
				
				
				chx = x;
				chy = y;
				count = 0;
				while(chy < 18  && chx < 18 && ls.getXY(chx+1,chy+1) == ls.WHITE) {
					chy++;
					chx++;
				}
				while(ls.getXY(chx--,chy--) == ls.WHITE && chy > 0 && chx > 0) {
					count++;
				}
				if(count == 4) {
					return 2;
				}
				
	
				chx = x;
				chy = y;
				count = 0;
				while(chy > 0  && chx < 18 && ls.getXY(chx+1,chy-1) == ls.WHITE) {
					chy--;
					chx++;
				}
				while(ls.getXY(chx--,chy++) == ls.WHITE && chy < 19 && chx > 0) {
					count++;
				}
				if(count == 4) {
					return 2;
				}
			}
	
			
			
			if(chturn == true) { 
				while(chx > 0 && ls.getXY(chx-1,chy) == ls.BLACK) {
					chx--;
				}
				while(ls.getXY(chx++,chy) == ls.BLACK && chx <19) {
					count++;	
				}
				if(count == 4)
					return 2;
				
					
				chx = x;
				chy = y;
				count = 0;
				while(chy > 0 && ls.getXY(chx,chy-1) == ls.BLACK) {
					chy--;
				}
				while(ls.getXY(chx,chy++) == ls.BLACK && chy <19) {
					count++;
				
				}
				if(count == 4)
					return 2;
				
				
				chx = x;
				chy = y;
				count = 0;
				while(chy > 0 && chx > 0 && ls.getXY(chx-1,chy-1) == ls.BLACK) {
					chy--;
					chx--;
				}
				while(ls.getXY(chx++,chy++) == ls.BLACK && chy < 19 && chx < 19) {
					count++;
				
				}
				if(count == 4) {
					return 2;
				}
				
				
				chx = x;
				chy = y;
				count = 0;
				while(chy < 18 && chx > 0 && ls.getXY(chx-1,chy+1) == ls.BLACK) {
					chy++;
					chx--;
				}
				while(ls.getXY(chx++,chy--) == ls.BLACK && chy > -1 && chx < 19) {
					count++;
				}
				if(count == 4) {
					return 2;
				}
				
				
				chx = x;
				chy = y;
				count = 0;
				while(chy < 18  && chx < 18 && ls.getXY(chx+1,chy+1) == ls.BLACK) {
					chy++;
					chx++;
				}
				while(ls.getXY(chx--,chy--) == ls.BLACK && chy > 0 && chx > 0) {
					count++;
				}
				if(count == 4) {
					return 2;
				}
				
				 
				chx = x;
				chy = y;
				count = 0;
				while(chy > 0  && chx < 18 && ls.getXY(chx+1,chy-1) == ls.BLACK) {
					chy--;
					chx++;
				}
				while(ls.getXY(chx--,chy++) == ls.BLACK && chy < 19 && chx > 0) {
					count++;
				}
				if(count == 4)
					return 2;
			}
			
			return 1;
	}
}
