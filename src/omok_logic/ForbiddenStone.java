package omok_logic;

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
	

	// �� �� Ž��
	// �� Ž�� : ����3�� �Ǹ� 1�� ���� �ƴϸ� 0 ����
	public int find1(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;

		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		//���� 3���� üũ�ϱ����Ѱ�..
		int blink1 = 1;
		
		//blink2 �� blink1 �� ���� �߰������־��ٰ���.
		//int blink2 = blink1;
		
		
		// ��
		chx = x-1; //�޶����� ��ǥ
		boolean check = false;
		left :
		while(true) {
			
			//��ǥ������
			if(chx == -1)
				break left;
			
			//check�� false�� �ٲ����� �ι��������� �������� Ȯ���Ҽ��ְ�.
			if(ls.getXY(chx,chy) == ls.BLACK) 
			{
				check = false;
				stone1++;
			}
			
			//��뵹�� ������ Ž������
			if(ls.getXY(chx,chy) == ls.WHITE) 
				break left;
			
			if(ls.getXY(chx,chy) == 0) {
				//ó�� ����������� check�� true�� ��µ�
				//���޾� ������������� Ž������
				//�ι��������� ����������� blinkī��Ʈ�� �ǵ���.
				if(check == false) {
					check = true;
				}else {
					blink1++;
					break left;
				}
				
				if(blink1 == 1) {
					blink1--;
				}else {
					break left; //��������������� ������� �ι������� ����
				}
			}
			//���Ž��
			chx--;
		}
		
		
		// ��
		chx = x+1; //�޶����� ��ǥ
		int blink2 = blink1; //blink1�����Ÿ�ŭ blink2,
		if(blink1 == 1) //������� ������������� �����������
			blink1 = 0;
		check = false;
		right :
		while(true) {
			//��ǥ������
			if(chx == 18)
				break right;
			
			if(ls.getXY(chx,chy) == ls.BLACK) 
			{
				check = false;
				stone2++;
			}
			
			//��뵹�� ������ Ž������
			if(ls.getXY(chx,chy) == ls.WHITE) 
				break right;
			
			if(ls.getXY(chx,chy) == 0) {
				//�ι��������� ����������� blinkī��Ʈ�� �ǵ���.
				if(check == false) {
					check = true;
				}else {
					blink2++;
					break right;
				}
				
				if(blink2 == 1) {
					blink2--;
				}else {
					break right; //��������������� ������� �ι������� ����
				}
			}
			chx++;
		}
		
		allStone = stone1 + stone2;
		//����̹Ƿ� �������� 2 + 1(���絹)�̾ƴϸ� 0����
		//�̺κ��� 43�� ����ϰ�����. 33�� ã�Ե�
		if(allStone != 2) {
			return 0;
			}
		//�������� 3�̸� ���� 3���� �ľ�.
		
		int left = (stone1 + blink1);
		int right = (stone2 + blink2);
		
		//������ ������� - ����3�� �ƴ�
		if(x - left == 0 || x + right == 17) {
			return 0;
		}else //��뵹�� ������� - ����3�� �ƴ�
			if(ls.getXY(x-left-1,y) == ls.WHITE || ls.getXY(x+right+1,y) == ls.WHITE) {
				return 0;
			}else {
				return 1; //����3 �϶� 1 ����
			}

	}
	// �� �� Ž��
	public int find2(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;
		
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		
		
		// ��
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
		
		
		// ��
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
			if(ls.getXY(x - leftUp - 1,y - leftUp -1) == ls.WHITE || ls.getXY(x + rightDown + 1,y + rightDown + 1) == ls.WHITE) {
				return 0;
			}else {
				return 1;
			}
		
		
	}
	// �� �� Ž��
	public int find3(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;
		
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		
		// �� 
		int chy = y-1; 
		boolean check = false;
		up :
		while(true) {
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
		
		// ��
		int blink2 = blink1; 
		if(blink1 == 1) 
			blink1 = 0;
		chy = y + 1;
		check = false;
		down :
		while(true) {
			if(chy == 18)
				break down;
			
			if(ls.getXY(x,chy) == ls.BLACK)
			{
				check = false;
				stone2++;
			}
			
			if(ls.getXY(x,chy) == ls.WHITE)
				break down;
			
			if(ls.getXY(x,chy) == 0) {
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
			if(ls.getXY(x,y - up - 1) == ls.WHITE || ls.getXY(x,y + down + 1) == ls.WHITE) {
				return 0;
			}else {
				return 1;
			}
	}
	// �� Ž��
	// �� �� Ž��
	public int find4(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;
		
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		
		// ��
		int chx = x-1; 
		int chy = y+1;
		boolean check = false;
		leftDown : 
		while(true) {
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
	
		// ��
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
		}else 
			if(ls.getXY(x - leftDown - 1,y + leftDown + 1) == ls.WHITE || ls.getXY(x + rightUp +1,y-rightUp-1) == ls.WHITE) {
				return 0;
			}else {
				return 1;
			}

	}
	
	
	
	//44
	//�����°� ���� x �׳� 4�� ������ ��º����� 2���̻��̸� 44
	//�Ȱ��� ������� �ϳ��� ���
	// 4���� �κ����� �γ����� Ǯ������
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
	
	
	// �� �� Ž��
	public int fourORjang1(int trigger, omok_logicSet LS, int x, int y) {
		int b = ls.BLACK;
		int w = ls.WHITE;
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		//����4������ ���������. �ٸ� �ڵ�� ��������� �ǹ�.
		int blink1 = 1;
		if(trigger == 3) // 5��޼������� ��������� 5���� �̾�������.
			blink1 = 0;
		
		// ��  Ž��
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
				//�ι��������� ����������� blinkī��Ʈ�� �ǵ���.
				if(check == false) {
					check = true;
				}else {
					blink1++;
					break left;
				}
				
				if(blink1 == 1) {
					blink1--;
				}else {
					break left; //��������������� ������� �ι������� ����
				}
				
				
			}
			
			chx--;
		}
		
		// �� Ž��
		chx = x + 1;
		chy = y;
		int blink2 = blink1;
		check = false;
		right :
		while(true) {
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
		
		//���ã�� Ʈ����
		if (trigger == 1) {
			if (allStone != 3)
				return 0; //���������� 3���ƴϸ� 4���ƴϴϱ�.
			else
				return 1; //���������� 3���� 4��. ������ �������� �������.
		}

		//���ã�� Ʈ����
		if (trigger == 2) {
			//��������� +1 +5 => 6���̻��� ���. ���⼭ ���������� �ι����� �����־�� ���
			if(allStone >= 5 && stone1 != 0 && stone2 != 0)
				return 1;
			else
				return 0;
		}
		
		if(trigger == 3) {
			//���������� 5���ǵ��̿ϼ��Ǹ�.
			if(allStone == 4)
				return 1;
			else
				return 0;
		}
		
		//�׷����������� 1 �� 2���ƴϸ� 0����
		return 0;
	}
	// �� �� Ž��
	public  int fourORjang2(int trigger, omok_logicSet LS, int x, int y) {
		int b = LS.BLACK;
		int w = LS.WHITE;
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		if(trigger == 3)
			blink1 = 0;
		
		// ��  Ž��
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
		
		// ��  Ž��
		chy = y + 1;
		chx = x + 1;
		check = false;
		int blink2 = blink1;
		leftDown :
		while(true) {
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
	// �� �� Ž��
	public  int fourORjang3(int trigger, omok_logicSet LS, int x, int y) {
		int b =LS.BLACK;
		int w = LS.WHITE;
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		if(trigger == 3)
			blink1 = 0;
		
		// ��  Ž��
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
		
		// ��  Ž��
		chy = y + 1;
		chx = x;
		check = false;
		int blink2 = blink1;
		down :
		while(true) {
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
	// �� �� Ž��
	public  int fourORjang4(int trigger, omok_logicSet LS, int x, int y) {
		int b = LS.BLACK;
		int w = LS.WHITE;
		int stone1 = 0;
		int stone2 = 0;
		int allStone = 0;
		int blink1 = 1;
		if(trigger == 3)
			blink1 = 0;
		
		// �� Ž��
		int chy = y - 1;
		int chx = x + 1;
		boolean check = false;
		rightup :
		while(true) {
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
		
		// �� Ž��
		chy = y + 1;
		chx = x - 1;
		check = false;
		int blink2 = blink1;
		leftdown :
		while(true) {
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
	
	
	//���
	public  int jangmok(omok_logicSet LS, int x, int y) {
		ls = LS;
		chx = x;
		chy = y;
		
		int result = 0;
		
		result += fourORjang1(2, ls, chx, chy);
		result += fourORjang2(2, ls, chx, chy);
		result += fourORjang3(2, ls, chx, chy);
		result += fourORjang4(2, ls, chx, chy);
		
		if(result >= 1)  //�ϳ��� ������������
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
		
		if(result >= 1) //�ϳ��� ������ �޼��Ǹ�.
			return 0;
		
		return 1;
	}
}