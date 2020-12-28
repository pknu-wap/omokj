package omokJServer;

public class ForbiddenStone {
	
	private omok_logicSet ls;

	private int chx;
	private int chy;
	
	public ForbiddenStone() {
		chx = 0;
		chy = 0;
	}


	public int samsam(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;

		int open_sam_count = 0;
		open_sam_count += find1(ls, chx, chy);
		open_sam_count += find2(ls, chx, chy);
		open_sam_count += find3(ls, chx, chy);
		open_sam_count += find4(ls, chx, chy);

		if(open_sam_count >= 2)
			return 0;
		else
			return 1;
	}
	

	// �뜝�룞�삕 �뜝�룞�삕 �깘�뜝�룞�삕
	// �뜝�룞�삕 �깘�뜝�룞�삕 : �뜝�룞�삕�뜝�룞�삕3�뜝�룞�삕 �뜝�떎紐뚯삕 1�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�떍�땲紐뚯삕 0 �뜝�룞�삕�뜝�룞�삕
	public int find1(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;

		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		//�뜝�룞�삕�뜝�룞�삕 3�뜝�룞�삕�뜝�룞�삕 泥댄겕�뜝�떦源띿삕�뜝�룞�삕�뜝�떬怨ㅼ삕..
		int blink1 = 1;
		
		//blink2 �뜝�룞�삕 blink1 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�뙥怨ㅼ삕�뜝�룞�삕�뜝�룞�삕�뜝�뙇�뼲�삕�뜝�뙐怨ㅼ삕�뜝�룞�삕.
		//int blink2 = blink1;
		
		
		// �뜝�룞�삕
		chx = x-1; //�뜝�뙣�씛�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�몴
		boolean check = false;
		left :
		while(true) {
			
			//�뜝�룞�삕�몴�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
			if(chx == -1)
				break left;
			
			//check�뜝�룞�삕 false�뜝�룞�삕 �뜝�뙐�먯삕�뜝�룞�삕�뜝�룞�삕 �뜝�떥諭꾩삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �솗�뜝�룞�삕�뜝���눦�삕�뜝�뙇怨ㅼ삕.
			if(ls.getXY(chx,chy) == ls.BLACK) 
			{
				check = false;
				stone1++;
			}
			
			//�뜝�룞�삕�얜콈�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �깘�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
			if(ls.getXY(chx,chy) == ls.WHITE) 
				break left;
			
			if(ls.getXY(chx,chy) == 0) {
				//泥섇뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� check�뜝�룞�삕 true�뜝�룞�삕 �뜝�룞�삕夷됧뜝占�
				//�뜝�룞�삕�뜝�뙣�뼲�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �깘�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
				//�뜝�떥諭꾩삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� blink移닷뜝�룞�삕�듃�뜝�룞�삕 �뜝�떎�벝�삕�뜝�룞�삕.
				if(check == false) {
					check = true;
				}else {
					blink1++;
					break left;
				}
				
				if(blink1 == 1) {
					blink1--;
				}else {
					break left; //�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�떥諭꾩삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
				}
			}
			//�뜝�룞�삕�뜝�떊�룞�삕�뜝占�
			chx--;
		}
		
		
		// �뜝�룞�삕
		chx = x+1; //�뜝�뙣�씛�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�몴
		int blink2 = blink1; //blink1�뜝�룞�삕�뜝�룞�삕�뜝�떊紐뚯삕�겮 blink2,
		if(blink1 == 1) //�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占�
			blink1 = 0;
		check = false;
		right :
		while(chx < 19 && chy < 19) {
			//占쏙옙표占쏙옙占쏙옙占쏙옙
			if(chx == 18)
				break right;
			
			if(ls.getXY(chx,chy) == ls.BLACK) 
			{
				check = false;
				stone2++;
			}
			
			//�뜝�룞�삕�얜콈�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �깘�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
			if(ls.getXY(chx,chy) == ls.WHITE) 
				break right;
			
			if(ls.getXY(chx,chy) == 0) {
				//�뜝�떥諭꾩삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� blink移닷뜝�룞�삕�듃�뜝�룞�삕 �뜝�떎�벝�삕�뜝�룞�삕.
				if(check == false) {
					check = true;
				}else {
					blink2++;
					break right;
				}
				
				if(blink2 == 1) {
					blink2--;
				}else {
					break right; //�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�떥諭꾩삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
				}
			}
			chx++;
		}
		
		allStone = stone1 + stone2;
		//�뜝�룞�삕�뜝�룞�삕鈺댄뱪�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 2 + 1(�뜝�룞�삕�뜝�뜾�룎)�뜝�떛�븘�땲紐뚯삕 0�뜝�룞�삕�뜝�룞�삕
		//�뜝�떛遺�釉앹삕�뜝�룞�삕 43�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕礪뗥뜝�룞�삕�뜝�룞�삕�뜝占�. 33�뜝�룞�삕 李얍뜝�뙃�벝�삕
		if(allStone != 2) {
			return 0;
			}
		//�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 3�뜝�떛紐뚯삕 �뜝�룞�삕�뜝�룞�삕 3�뜝�룞�삕�뜝�룞�삕 �뜝�떇�뼲�삕.
		
		int left = (stone1 + blink1);
		int right = (stone2 + blink2);
		
		//�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� - �뜝�룞�삕�뜝�룞�삕3�뜝�룞�삕 �뜝�떍�뙋�삕
		if(x - left == 0 || x + right == 17) {
			return 0;
		}else //�뜝�룞�삕�얜콈�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� - �뜝�룞�삕�뜝�룞�삕3�뜝�룞�삕 �뜝�떍�뙋�삕
			if(ls.getXY(x-left-1,y) == ls.WHITE || ls.getXY(x+right+1,y) == ls.WHITE) {
				return 0;
			}else {
				return 1; //�뜝�룞�삕�뜝�룞�삕3 �뜝�떦�씛�삕 1 �뜝�룞�삕�뜝�룞�삕
			}

	}
	// �뜝�룞�삕 �뜝�룞�삕 �깘�뜝�룞�삕
	public int find2(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;
		
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		
		
		// �뜝�룞�삕
		chx = x-1; 
		chy = y-1;
		boolean check = false;
		leftUp :
		while(true) {
			if(chx == -1 || chy == -1)
				break leftUp;
			
			if(ls.getXY(chx,chy) == ls.BLACK)
			{
				check = false;
				stone1++;
			}
			
			if(ls.getXY(chx,chy) == ls.WHITE)
				break leftUp;
			
			if(ls.getXY(chx,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink1++;
					break leftUp;
				}
				
				if(blink1 == 1) {
					blink1--;
				}else {
					break leftUp;
				}
			}
			chx--;
			chy--;
		}
		
		
		// �뜝�룞�삕
		int blink2 = blink1;
		if(blink1 == 1) 
			blink1 = 0;
		chx = x+1;
		chy = y+1;
		check = false;
		rightDown:
		while(chx < 19 && chy < 19) {
			if(chx == 18 || chy == 18)
				break rightDown;
			
			if(ls.getXY(chx,chy) == ls.BLACK)
			{
				check = false;
				stone2++;
			}
			
			if(ls.getXY(chx,chy) == ls.WHITE)
				break rightDown;
			
			if(ls.getXY(chx,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink2++;
					break rightDown;
				}
				
				if(blink2 == 1) {
					blink2--;
				}else {
					break rightDown; 
				}
			}
				
			chx++;
			chy++;
		}
		
		allStone = stone1 + stone2;
		if(allStone != 2) {
			return 0;
			}
		
		int leftUp = (stone1 + blink1);
		int rightDown = (stone2 + blink2);
		
		if(y - leftUp == 0 || x - leftUp == 0 || y + rightDown == 17 || x + rightDown == 17) {
			return 0;
		}else 
			if(ls.getXY(x - leftUp - 1,y - leftUp -1) == ls.WHITE ||
					x + rightDown + 1 < 19 && y + rightDown + 1 < 19 &&
							ls.getXY(x + rightDown + 1,y + rightDown + 1) == ls.WHITE) {
				return 0;
			}else {
				return 1;
			}
		
		
	}
	// �뜝�룞�삕 �뜝�룞�삕 �깘�뜝�룞�삕
	public int find3(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;
		
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		
		// �뜝�룞�삕 
		int chy = y-1; 
		boolean check = false;
		up :
		while(chx < 19 && chy < 19) {
			if(chy == -1)
				break up;

			if(ls.getXY(x,chy) == ls.BLACK)
			{
				check = false;
				stone1++;
			}

			if(ls.getXY(x,chy) == ls.WHITE)
				break up;
			
			if(ls.getXY(x,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink1++;
					break up;
				}
				
				if(blink1 == 1) {
					blink1--;
				}else {
					break up; 
				}
			}
			chy--;
		}
		
		// �뜝�룞�삕
		int blink2 = blink1; 
		if(blink1 == 1) 
			blink1 = 0;
		chy = y + 1;
		check = false;

		while(chx < 19 && chy < 19) {
			if(chy == 18)
				break;
			
			if(ls.getXY(x,chy) == ls.BLACK)
			{
				check = false;
				stone2++;
			}
			
			if(ls.getXY(x,chy) == ls.WHITE)
				break;
			
			if(ls.getXY(x,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink2++;
					break;
				}
				
				if(blink2 == 1) {
					blink2--;
				}else {
					break;
				}
			}
			
			chy++;
		}

		allStone = stone1 + stone2;
		if(allStone != 2) {
			return 0;
			}

		int up = (stone1 + blink1);
		int down = (stone2 + blink2);

		if(y - up == 0 || y + down == 17) {
			return 0;
		}else 
			if(ls.getXY(x,y - up - 1) == ls.WHITE || ls.getXY(x,y + down + 1) == ls.WHITE) {
				return 0;
			}else {
				return 1;
			}
	}
	// �뜝�룞�삕 �깘�뜝�룞�삕
	// �뜝�룞�삕 �뜝�룞�삕 �깘�뜝�룞�삕
	public int find4(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;
		
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		
		// �뜝�룞�삕
		int chx = x-1; 
		int chy = y+1;
		boolean check = false;
		leftDown :
		while(chx < 19 && chy < 19) {
			if(chx == -1 || chy == 18)
				break leftDown;
			
			if(ls.getXY(chx,chy) == ls.BLACK)
			{
				check = false;
				stone1++;
			}
			
			if(ls.getXY(chx,chy) == ls.WHITE)
				break leftDown;
			
			if(ls.getXY(chx,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink1++;
					break leftDown;
				}
				
				if(blink1 == 1) {
					blink1--;
				}else {
					break leftDown; 
				}
			}
			chx--;
			chy++;
		}
	
		// �뜝�룞�삕
		int blink2 = blink1; 
		if(blink1 == 1) 
			blink1 = 0;
		chx = x + 1;
		chy = y - 1;
		check = false;
		rightUp : 
		while(chx < 19 && chy < 19) {
			if(chx == 18 || chy == -1)
				break rightUp;

			if(ls.getXY(chx,chy) == ls.BLACK)
			{
				check = false;
				stone2++;
			}

			if(ls.getXY(chx,chy) == ls.WHITE)
				break rightUp;
			
			if(ls.getXY(chx,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink2++;
					break rightUp;
				}
				
				if(blink2 == 1) {
					blink2--;
				}else {
					break rightUp; 
				}
			}
			chx++;
			chy--;
		}
		
		allStone = stone1 + stone2;
		if (allStone != 2) {

			return 0;
		}
		
		
		int leftDown = (stone1 + blink1);
		int rightUp = (stone2 + blink2);

		if(x - leftDown == 0 || y - rightUp == 0|| y + leftDown == 17 || x + rightUp == 17) {
			return 0;
		}else {
			if (x - leftDown - 1 >= 0 && ls.getXY(x - leftDown - 1, y + leftDown + 1) == ls.WHITE ||
					y - rightUp - 1 >= 0 && x + rightUp + 1 <= 18 &&
							ls.getXY(x + rightUp + 1, y - rightUp - 1) == ls.WHITE) {
				return 0;
			} else {
				return 1;
			}
		}
	}
	
	
	
	//44
	//�뜝�룞�삕�뜝�룞�삕�뜝�듅怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 x �뜝�뙎�냲�삕 4�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕夷섇뜝�룞�삕�뜝�룞�삕�뜝占� 2�뜝�룞�삕�뜝�떛�궪�삕�뜝�떛紐뚯삕 44
	//�뜝�떕怨ㅼ삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�떦�냲�삕�뜝�룞�삕 �뜝�룞�삕�뜝占�
	// 4�뜝�룞�삕�뜝�룞�삕 �뜝�떥釉앹삕�뜝�룞�삕�뜝�룞�삕 �뜝�떥�냲�삕�뜝�룞�삕�뜝�룞�삕 ���뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
	public int sasa(omok_logicSet LS, int x, int y) {
		ls = LS;
		
		int fourStone = 0;
		
		fourStone += fourORjang1(1, ls, x, y);
		fourStone += fourORjang2(1, ls, x, y);
		fourStone += fourORjang3(1, ls, x, y);
		fourStone += fourORjang4(1, ls, x, y);
		
		
		if(fourStone >= 2)
			return 0;
		else
			return 1;
	}
	
	
	// �뜝�룞�삕 �뜝�룞�삕 �깘�뜝�룞�삕
	public int fourORjang1(int trigger, omok_logicSet LS, int x, int y) {
		int b = ls.BLACK;
		int w = ls.WHITE;
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		//�뜝�룞�삕�뜝�룞�삕4�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占�. �뜝�뙐紐뚯삕 �뜝�뙓�벝�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�떎諭꾩삕.
		int blink1 = 1;
		if(trigger == 3) // 5�뜝�룞�삕麗룟뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� 5�뜝�룞�삕�뜝�룞�삕 �뜝�떛�뼲�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕.
			blink1 = 0;
		
		// �뜝�룞�삕  �깘�뜝�룞�삕
		int chy = y;
		int chx = x - 1;
		boolean check = false;
		left :
		while(true) {
			if(chx == -1)
				break left;
			
			if(ls.getXY(chx,chy) == ls.BLACK) {
				check = false;
				stone1++;
			}
			
			if(ls.getXY(chx,chy) == ls.WHITE)
				break left;
			
			if(ls.getXY(chx,chy) == 0) {
				//�뜝�떥諭꾩삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� blink移닷뜝�룞�삕�듃�뜝�룞�삕 �뜝�떎�벝�삕�뜝�룞�삕.
				if(check == false) {
					check = true;
				}else {
					blink1++;
					break left;
				}
				
				if(blink1 == 1) {
					blink1--;
				}else {
					break left; //�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�떥諭꾩삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
				}
				
				
			}
			
			chx--;
		}
		
		// �뜝�룞�삕 �깘�뜝�룞�삕
		chx = x + 1;
		chy = y;
		int blink2 = blink1;
		check = false;
		right :
		while(chx < 19 && chy < 19) {
			if(chx == 18)
				break right;
			
			if(ls.getXY(chx,chy) == ls.BLACK) {
				check = false;
				stone2++;
			}
			
			if(ls.getXY(chx,chy) == ls.WHITE)
				break right;
			
			if(ls.getXY(chx,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink2++;
					break right;
				}
				
				if(blink2 == 1) {
					blink2--;
				}else {
					break right; 
				}
				
				
			}
			
			chx++;
		}
		
		
		allStone = stone1 + stone2;
		
		//�뜝�룞�삕�뜝�떆節륁삕�뜝占� �듃�뜝�룞�삕�뜝�룞�삕
		if (trigger == 1) {
			if (allStone != 3)
				return 0; //�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 3�뜝�룞�삕�뜝�떍�땲紐뚯삕 4�뜝�룞�삕�뜝�떍�땲�땲源띿삕.
			else
				return 1; //�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 3�뜝�룞�삕�뜝�룞�삕 4�뜝�룞�삕. �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占�.
		}

		//�뜝�룞�삕�뜝�떆節륁삕�뜝占� �듃�뜝�룞�삕�뜝�룞�삕
		if (trigger == 2) {
			//�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� +1 +5 => 6�뜝�룞�삕�뜝�떛�궪�삕�뜝�룞�삕 �뜝�룞�삕�뜝占�. �뜝�룞�삕�뜝�뜦�꽌 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떥諭꾩삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�뙇�뼲�삕�뜝占� �뜝�룞�삕�뜝占�
			if(allStone >= 5 && stone1 != 0 && stone2 != 0)
				return 1;
			else
				return 0;
		}
		
		if(trigger == 3) {
			//�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 5�뜝�룞�삕�뜝�떎�벝�삕�뜝�떛�셿�눦�삕�뜝�떎紐뚯삕.
			if(allStone == 4)
				return 1;
			else
				return 0;
		}
		
		//�뜝�뙎琉꾩삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 1 �뜝�룞�삕 2�뜝�룞�삕�뜝�떍�땲紐뚯삕 0�뜝�룞�삕�뜝�룞�삕
		return 0;
	}
	// �뜝�룞�삕 �뜝�룞�삕 �깘�뜝�룞�삕
	public  int fourORjang2(int trigger, omok_logicSet LS, int x, int y) {
		int b = LS.BLACK;
		int w = LS.WHITE;
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		if(trigger == 3)
			blink1 = 0;
		
		// �뜝�룞�삕  �깘�뜝�룞�삕
		int chy = y - 1;
		int chx = x - 1;
		boolean check = false;
		leftUp :
		while(true) {
			if(chx == -1 || chy == -1)
				break leftUp;
			
			if(ls.getXY(chx,chy) == ls.BLACK) {
				check = false;
				stone1++;
			}
			
			if(ls.getXY(chx,chy) == ls.WHITE)
				break leftUp;
			
			if(ls.getXY(chx,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink1++;
					break leftUp;
				}
				
				if(blink1 == 1) {
					blink1--;
				}else {
					break leftUp; 
				}
				
				
			}
			
			chx--;
			chy--;
		}
		
		// �뜝�룞�삕  �깘�뜝�룞�삕
		chy = y + 1;
		chx = x + 1;
		check = false;
		int blink2 = blink1;
		leftDown :
		while(chx < 19 && chy < 19) {
			if(chx == 18 || chy == 18)
				break leftDown;
			
			if(ls.getXY(chx,chy) == ls.BLACK) {
				check = false;
				stone2++;
			}
			
			if(ls.getXY(chx,chy) == ls.WHITE)
				break leftDown;
			
			if(ls.getXY(chx,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink2++;
					break leftDown;
				}
				
				if(blink2 == 1) {
					blink2--;
				}else {
					break leftDown; 
				}
				
				
			}
			
			chx++;
			chy++;
		}
		
		
		allStone = stone1 + stone2;
		
		if (trigger == 1) {
			if (allStone != 3)
				return 0;
			else
				return 1;
		} 
		
		if (trigger == 2) {
			if(allStone >= 5 && stone1 != 0 && stone2 != 0)
				return 1;
			else
				return 0;
		}
		
		if(trigger == 3) {
			if(allStone == 4)
				return 1;
			else
				return 0;
		}

		return 0;
	}
	// �뜝�룞�삕 �뜝�룞�삕 �깘�뜝�룞�삕
	public  int fourORjang3(int trigger, omok_logicSet LS, int x, int y) {
		int b =LS.BLACK;
		int w = LS.WHITE;
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		if(trigger == 3)
			blink1 = 0;
		
		// �뜝�룞�삕  �깘�뜝�룞�삕
		int chy = y - 1;
		int chx = x;
		boolean check = false;
		up :
		while(true) {
			if(chy == -1)
				break up;
			
			if(ls.getXY(chx,chy) == ls.BLACK) {
				check = false;
				stone1++;
			}
			
			if(ls.getXY(chx,chy) == ls.WHITE)
				break up;
			
			if(ls.getXY(chx,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink1++;
					break up;
				}
				
				if(blink1 == 1) {
					blink1--;
				}else {
					break up; 
				}
				
				
			}
			
			chy--;
		}
		
		// �뜝�룞�삕  �깘�뜝�룞�삕
		chy = y + 1;
		chx = x;
		check = false;
		int blink2 = blink1;
		down :
		while(chx < 19 && chy < 19) {
			if(chy == 18)
				break down;
			
			if(ls.getXY(chx,chy) == ls.BLACK) {
				check = false;
				stone2++;
			}
			
			if(ls.getXY(chx,chy) == ls.WHITE)
				break down;
			
			if(ls.getXY(chx,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink2++;
					break down;
				}
				
				if(blink2 == 1) {
					blink2--;
				}else {
					break down; 
				}
				
				
			}
			
			chy++;
		}
		
		
		
		allStone = stone1 + stone2;
		
		if (trigger == 1) {
			if (allStone != 3)
				return 0;
			else
				return 1;
		}  
		
		if (trigger == 2) {
			if(allStone >= 5 && stone1 != 0 && stone2 != 0)
				return 1;
			else
				return 0;
		}
		
		if(trigger == 3) {
			if(allStone == 4)
				return 1;
			else
				return 0;
		}
		
		return 0;
	}
	// �뜝�룞�삕 �뜝�룞�삕 �깘�뜝�룞�삕
	public  int fourORjang4(int trigger, omok_logicSet LS, int x, int y) {
		int b = LS.BLACK;
		int w = LS.WHITE;
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		if(trigger == 3)
			blink1 = 0;
		
		// �뜝�룞�삕 �깘�뜝�룞�삕
		int chy = y - 1;
		int chx = x + 1;
		boolean check = false;
		rightup :
		while(chx < 19 && chy < 19) {
			if(chx == 18 || chy == -1)
				break rightup;
			
			if(ls.getXY(chx,chy) == ls.BLACK) {
				check = false;
				stone1++;
			}
			
			if(ls.getXY(chx,chy) == ls.WHITE)
				break rightup;
			
			if(ls.getXY(chx,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink1++;
					break rightup;
				}
				
				if(blink1 == 1) {
					blink1--;
				}else {
					break rightup; 
				}
				
				
			}
			
			chx++;
			chy--;
		}
		
		// �뜝�룞�삕 �깘�뜝�룞�삕
		chy = y + 1;
		chx = x - 1;
		check = false;
		int blink2 = blink1;
		leftdown :
		while(chx < 19 && chy < 19) {
			if(chx == -1 || chy == 18)
				break leftdown;
			
			if(ls.getXY(chx,chy) == ls.BLACK) {
				check = false;
				stone2++;
			}
			
			if(ls.getXY(chx,chy) == ls.WHITE)
				break leftdown;
			
			if(ls.getXY(chx,chy) == 0) {
				if(check == false) {
					check = true;
				}else {
					blink2++;
					break leftdown;
				}
				
				if(blink2 == 1) {
					blink2--;
				}else {
					break leftdown;
				}
				
				
			}
			
			chx--;
			chy++;
		}
		
		
		
		allStone = stone1 + stone2;
		
		if (trigger == 1) {
			if (allStone != 3)
				return 0;
			else
				return 1;
		}  
		
		if (trigger == 2) {
			if(allStone >= 5 && stone1 != 0 && stone2 != 0)
				return 1;
			else
				return 0;
		}
		
		if(trigger == 3) {
			if(allStone == 4)
				return 1;
			else
				return 0;
		}
		
		return 0;
	}
	
	
	//�뜝�룞�삕�뜝占�
	public  int jangmok(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;
		
		int result = 0;
		
		result += fourORjang1(2, ls, chx, chy);
		result += fourORjang2(2, ls, chx, chy);
		result += fourORjang3(2, ls, chx, chy);
		result += fourORjang4(2, ls, chx, chy);
		
		if(result >= 1)  //�뜝�떦�냲�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
			return 0;
		
		return 1;
	}
	
	public  int fiveStone(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;
		int result = 0;
		
		result += fourORjang1(3, ls, chx, chy);
		result += fourORjang2(3, ls, chx, chy);
		result += fourORjang3(3, ls, chx, chy);
		result += fourORjang4(3, ls, chx, chy);
		
		if(result >= 1) //�뜝�떦�냲�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�뙣�눦�삕�뜝�떎紐뚯삕.
			return 0;
		
		return 1;
	}
}