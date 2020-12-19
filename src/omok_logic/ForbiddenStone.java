package omok_logic;

public class ForbiddenStone {
	
	private omok_logicSet ls;

	private int chx;
	private int chy;
	
	
	
	public omok_logicSet() {
		this.ls = ls;
		chx = 0;
		chy = 0;
	}


	public boolean samsam() {
		int open_sam_count = 0;
		open_sam_count += find1(); 
		open_sam_count += find2();
		open_sam_count += find3();
		open_sam_count += find4();

		if(open_sam_count >= 2)
			return true;
		else
			return false;
	}

	// ← → 탐색
	// ─ 탐색 : 열린3이 되면 1을 리턴 아니면 0 리턴
	public int find1(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;

		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		//열린 3인지 체크하기위한것..
		int blink1 = 1;
		
		//blink2 는 blink1 과 같음 중간에서넣어줄거임.
		//int blink2 = blink1;
		
		
		// ←
		chx = x-1; //달라지는 좌표
		boolean check = false;
		left :
		while(true) {
			
			//좌표끝도달
			if(chx == -1)
				break left;
			
			//check를 false로 바꿈으로 두번연속으로 만나는지 확인할수있게.
			if(ls.getXY(chy,chx) == ls.BLACK) 
			{
				check = false;
				stone1++;
			}
			
			//상대돌을 만나면 탐색중지
			if(ls.getXY(chy,chx) == ls.WHITE) 
				break left;
			
			if(ls.getXY(chy,chx) == 0) {
				//처음 빈공간을만나 check가 true가 됬는데
				//연달아 빈공간을만나면 탐색중지
				//두번연속으로 빈공간만날시 blink카운트를 되돌림.
				if(check == false) {
					check = true;
				}else {
					blink1++;
					break left;
				}
				
				if(blink1 == 1) {
					blink1--;
				}else {
					break left; //빈공간을만났으나 빈공간을 두번만나면 끝임
				}
			}
			//계속탐색
			chx--;
		}
		
		
		// →
		chx = x+1; //달라지는 좌표
		int blink2 = blink1; //blink1남은거만큼 blink2,
		if(blink1 == 1) //빈공간을 만나지않은경우 없었음을기록
			blink1 = 0;
		check = false;
		right :
		while(true) {
			//좌표끝도달
			if(chx == 18)
				break right;
			
			if(ls.getXY(chy,chx) == ls.BLACK) 
			{
				check = false;
				stone2++;
			}
			
			//상대돌을 만나면 탐색중지
			if(ls.getXY(chy,chx) == ls.WHITE) 
				break right;
			
			if(ls.getXY(chy,chx) == 0) {
				//두번연속으로 빈공간만날시 blink카운트를 되돌림.
				if(check == false) {
					check = true;
				}else {
					blink2++;
					break right;
				}
				
				if(blink2 == 1) {
					blink2--;
				}else {
					break right; //빈공간을만났으나 빈공간을 두번만나면 끝임
				}
			}
			chx++;
		}
		
		allStone = stone1 + stone2;
		//삼삼이므로 돌갯수가 2 + 1(현재돌)이아니면 0리턴
		//이부분이 43을 허용하게해줌. 33만 찾게됨
		if(allStone != 2) {
			return 0;
			}
		//돌갯수가 3이면 열린 3인지 파악.
		
		int left = (stone1 + blink1);
		int right = (stone2 + blink2);
		
		//벽으로 막힌경우 - 열린3이 아님
		if(x - left == 0 || x + right == 17) {
			return 0;
		}else //상대돌로 막힌경우 - 열린3이 아님
			if(ls.getXY(y,x-left-1) == ls.WHITE || ls.getXY(y,x+right+1) == ls.WHITE) {
				return 0;
			}else {
				return 1; //열린3 일때 1 리턴
			}

	}
	// ↖ ↘ 탐색
	public int find2(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;
		
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		
		
		// ↖
		chx = x-1; 
		chy = y-1;
		boolean check = false;
		leftUp :
		while(true) {
			if(chx == -1 || chy == -1)
				break leftUp;
			
			if(ls.getXY(chy,chx) == ls.BLACK)
			{
				check = false;
				stone1++;
			}
			
			if(ls.getXY(chy,chx) == ls.WHITE)
				break leftUp;
			
			if(ls.getXY(chy,chx) == 0) {
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
		
		
		// ↘
		int blink2 = blink1;
		if(blink1 == 1) 
			blink1 = 0;
		chx = x+1;
		chy = y+1;
		check = false;
		rightDown:
		while(true) {
			if(chx == 18 || chy == 18)
				break rightDown;
			
			if(ls.getXY(chy,chx) == ls.BLACK)
			{
				check = false;
				stone2++;
			}
			
			if(ls.getXY(chy,chx) == ls.WHITE)
				break rightDown;
			
			if(ls.getXY(chy,chx) == 0) {
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
			if(ls.getXY(y - leftUp -1,x - leftUp - 1) == ls.WHITE || ls.getXY(y + rightDown + 1,x + rightDown + 1) == ls.WHITE) {
				return 0;
			}else {
				return 1;
			}
		
		
	}
	// ↑ ↓ 탐색
	public int find3(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;
		
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		
		// ↑ 
		int chy = y-1; 
		boolean check = false;
		up :
		while(true) {
			if(chy == -1)
				break up;

			if(ls.getXY(chy,x) == ls.BLACK)
			{
				check = false;
				stone1++;
			}

			if(ls.getXY(chy,x) == ls.WHITE)
				break up;
			
			if(ls.getXY(chy,x) == 0) {
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
		
		// ↓
		int blink2 = blink1; 
		if(blink1 == 1) 
			blink1 = 0;
		chy = y + 1;
		check = false;
		down :
		while(true) {
			if(chy == 18)
				break down;
			
			if(ls.getXY(chy,x) == ls.BLACK)
			{
				check = false;
				stone2++;
			}
			
			if(ls.getXY(chy,x) == ls.WHITE)
				break down;
			
			if(ls.getXY(chy,x) == 0) {
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
		if(allStone != 2) {
			return 0;
			}

		int up = (stone1 + blink1);
		int down = (stone2 + blink2);

		if(y - up == 0 || y + down == 17) {
			return 0;
		}else 
			if(ls.getXY(y - up - 1,x) == ls.WHITE || ls.getXY(y + down + 1,x) == ls.WHITE) {
				return 0;
			}else {
				return 1;
			}
	}
	// ／ 탐색
	// ↙ ↗ 탐색
	public int find4(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;
		
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		
		// ↙
		int chx = x-1; 
		int chy = y+1;
		boolean check = false;
		leftDown : 
		while(true) {
			if(chx == -1 || chy == 18)
				break leftDown;
			
			if(ls.getXY(chy,chx) == ls.BLACK)
			{
				check = false;
				stone1++;
			}
			
			if(ls.getXY(chy,chx) == ls.WHITE)
				break leftDown;
			
			if(ls.getXY(chy,chx) == 0) {
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
	
		// ↗
		int blink2 = blink1; 
		if(blink1 == 1) 
			blink1 = 0;
		chx = x + 1;
		chy = y - 1;
		check = false;
		rightUp : 
		while(true) {
			if(chx == 18 || chy == -1)
				break rightUp;

			if(ls.getXY(chy,chx) == ls.BLACK)
			{
				check = false;
				stone2++;
			}

			if(ls.getXY(chy,chx) == ls.WHITE)
				break rightUp;
			
			if(ls.getXY(chy,chx) == 0) {
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
		}else 
			if(ls.getXY(y + leftDown + 1,x - leftDown - 1) == ls.WHITE || ls.getXY(y-rightUp-1,x + rightUp +1) == ls.WHITE) {
				return 0;
			}else {
				return 1;
			}

	}
	
	
	
	//44
	//열리는건 문제 x 그냥 4의 갯수를 담는변수가 2개이상이면 44
	//똑같이 빈공간은 하나만 허용
	// 4가지 부분으로 로나누어 풀수있음
	public boolean sasa() {
		int fourStone = 0;
		
		fourStone += fourORjang1(1);
		fourStone += fourORjang2(1);
		fourStone += fourORjang3(1);
		fourStone += fourORjang4(1);
		
		
		if(fourStone >= 2)
			return true;
		else
			return false;
	}
	
	
	// ← → 탐색
	public int fourORjang1(int trigger) {
		int b = BLACK;
		int w = WHITE;
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		//열린4인지는 상관은없음. 다만 코드상 빈공간만을 의미.
		int blink1 = 1;
		if(trigger == 3) // 5목달성조건은 빈공간없이 5개가 이어져야함.
			blink1 = 0;
		
		// ←  탐색
		int chy = y;
		int chx = x - 1;
		boolean check = false;
		left :
		while(true) {
			if(chx == -1)
				break left;
			
			if(ls.getXY(chy,chx) == ls.BLACK) {
				check = false;
				stone1++;
			}
			
			if(ls.getXY(chy,chx) == ls.WHITE)
				break left;
			
			if(ls.getXY(chy,chx) == 0) {
				//두번연속으로 빈공간만날시 blink카운트를 되돌림.
				if(check == false) {
					check = true;
				}else {
					blink1++;
					break left;
				}
				
				if(blink1 == 1) {
					blink1--;
				}else {
					break left; //빈공간을만났으나 빈공간을 두번만나면 끝임
				}
				
				
			}
			
			chx--;
		}
		
		// → 탐색
		chx = x + 1;
		chy = y;
		int blink2 = blink1;
		check = false;
		right :
		while(true) {
			if(chx == 18)
				break right;
			
			if(ls.getXY(chy,chx) == ls.BLACK) {
				check = false;
				stone2++;
			}
			
			if(ls.getXY(chy,chx) == ls.WHITE)
				break right;
			
			if(ls.getXY(chy,chx) == 0) {
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
		
		//사사찾는 트리거
		if (trigger == 1) {
			if (allStone != 3)
				return 0; //놓은돌제외 3개아니면 4가아니니까.
			else
				return 1; //놓은돌제외 3개면 4임. 닫히고 열린지는 상관없음.
		}

		//장목찾는 트리거
		if (trigger == 2) {
			//현재놓은돌 +1 +5 => 6목이상은 장목. 여기서 놓은돌기준 두방향모두 돌이있어야 장목
			if(allStone >= 5 && stone1 != 0 && stone2 != 0)
				return 1;
			else
				return 0;
		}
		
		if(trigger == 3) {
			//놓은돌포함 5개의돌이완성되면.
			if(allStone == 4)
				return 1;
			else
				return 0;
		}
		
		//그럴일을없지만 1 도 2도아니면 0리턴
		return 0;
	}
	// ↖ ↘ 탐색
	public  int fourORjang2(int trigger) {
		int b = BLACK;
		int w = WHITE;
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		if(trigger == 3)
			blink1 = 0;
		
		// ↖  탐색
		int chy = y - 1;
		int chx = x - 1;
		boolean check = false;
		leftUp :
		while(true) {
			if(chx == -1 || chy == -1)
				break leftUp;
			
			if(ls.getXY(chy,chx) == ls.BLACK) {
				check = false;
				stone1++;
			}
			
			if(ls.getXY(chy,chx) == ls.WHITE)
				break leftUp;
			
			if(ls.getXY(chy,chx) == 0) {
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
		
		// ↘  탐색
		chy = y + 1;
		chx = x + 1;
		check = false;
		int blink2 = blink1;
		leftDown :
		while(true) {
			if(chx == 18 || chy == 18)
				break leftDown;
			
			if(ls.getXY(chy,chx) == ls.BLACK) {
				check = false;
				stone2++;
			}
			
			if(ls.getXY(chy,chx) == ls.WHITE)
				break leftDown;
			
			if(ls.getXY(chy,chx) == 0) {
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
	// ↑ ↓ 탐색
	public  int fourORjang3(int trigger) {
		int b = BLACK;
		int w = WHITE;
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		if(trigger == 3)
			blink1 = 0;
		
		// ↑  탐색
		int chy = y - 1;
		int chx = x;
		boolean check = false;
		up :
		while(true) {
			if(chy == -1)
				break up;
			
			if(ls.getXY(chy,chx) == ls.BLACK) {
				check = false;
				stone1++;
			}
			
			if(ls.getXY(chy,chx) == ls.WHITE)
				break up;
			
			if(ls.getXY(chy,chx) == 0) {
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
		
		// ↓  탐색
		chy = y + 1;
		chx = x;
		check = false;
		int blink2 = blink1;
		down :
		while(true) {
			if(chy == 18)
				break down;
			
			if(ls.getXY(chy,chx) == ls.BLACK) {
				check = false;
				stone2++;
			}
			
			if(ls.getXY(chy,chx) == ls.WHITE)
				break down;
			
			if(ls.getXY(chy,chx) == 0) {
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
	// ↗ ↙ 탐색
	public  int fourORjang4(int trigger) {
		int b = BLACK;
		int w = WHITE;
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		if(trigger == 3)
			blink1 = 0;
		
		// ↗ 탐색
		int chy = y - 1;
		int chx = x + 1;
		boolean check = false;
		rightup :
		while(true) {
			if(chx == 18 || chy == -1)
				break rightup;
			
			if(ls.getXY(chy,chx) == ls.BLACK) {
				check = false;
				stone1++;
			}
			
			if(ls.getXY(chy,chx) == ls.WHITE)
				break rightup;
			
			if(ls.getXY(chy,chx) == 0) {
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
		
		// ↙ 탐색
		chy = y + 1;
		chx = x - 1;
		check = false;
		int blink2 = blink1;
		leftdown :
		while(true) {
			if(chx == -1 || chy == 18)
				break leftdown;
			
			if(ls.getXY(chy,chx) == ls.BLACK) {
				check = false;
				stone2++;
			}
			
			if(ls.getXY(chy,chx) == ls.WHITE)
				break leftdown;
			
			if(ls.getXY(chy,chx) == 0) {
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
	
	
	//장목
	public  boolean jangmok() {
		int result = 0;
		
		result += fourORjang1(2);
		result += fourORjang2(2);
		result += fourORjang3(2);
		result += fourORjang4(2);
		
		if(result >= 1)  //하나라도 장목수가있으면
			return true;
		
		return false;
	}
	
	public  boolean fiveStone() {
		int result = 0;
		
		result += fourORjang1(3);
		result += fourORjang2(3);
		result += fourORjang3(3);
		result += fourORjang4(3);
		
		if(result >= 1) //하나라도 오목이 달성되면.
			return true;
		
		
		return false;
	}
}