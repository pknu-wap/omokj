package omok;

public class OmokImpl implements OmokInterface {
	int[][] omok = new int[18][18];
	// ������ ũ�� x, y ��ü ��ǥ
	
	
	// ������ ��� �Լ� 
	public void viewOmok() {		
		int numY = 0;				
		// ���� ���� ��ǥ�� �ʱ�ȭ
		
		int numX = 0;				
		// ���� �ϴ� ��ǥ�� �ʱ�ȭ
		
		for(int[] i : omok) {
		
			
			System.out.print(numY < 10 ? numY++ + " " : numY++);
			// ������ Y ��ǥ ǥ��
			
			
			for(int j : i) {
				if(j == 1) {
					System.out.print(" ��");	
					// �浹 ǥ��
				} else if(j == 2) {
					System.out.print(" ��"); 
					// �鵹 ǥ��
				} else {
					System.out.print(" ��"); 
					// ���� ǥ��
				}
			}
			System.out.print("\n");
		}
		
		System.out.print("  ");
		
		for(int[] i : omok) {

			System.out.print(" " + numX++);
			// �ϴܿ� X�� ��ǥ ǥ��

		}
		
		System.out.print("\n");
	}
	
	

	
	
	
	// ���� 
	public int OmokAction(int x, int y, int turn) {
		int chX = x;

		
		int chY = y;

		

		int count = 0;
		
		int _x,_y = 0;
		
		String name = new String();
		name = (turn == 1) ? "�浹" : "�鵹"; 
		
		if( omok[chY][chX] != 0 ) {
			System.out.println("�̹� ������ ��ġ�Ǿ� �ֽ��ϴ�.");
			System.out.println("�ٽ� �����ֽʽÿ�.");
			return 2;
		} else {
			omok[chY][chX] = turn;
		}
		
		
		
		
		
		
		// ���� üũ �ڵ�
		_x = chX;
		_y = chY;
		count = 0;
		while(omok[_y][_x] == turn && _x > 0) {
			_x--;
		}
		while(omok[_y][_x++] == turn && _x <= 17) {
			count++;
			
		}
		if(count == 5) {
			System.out.println(name + "�¸�");
			return 1;
		}
		
		// ���� üũ �ڵ�
		_x = chX;
		_y = chY;
		count = 0;
		while(omok[_y][_x] == turn && _y > 0) {
			_y--;
		}
		while(omok[_y++][_x] == turn && _y <= 17) {
			count++;
		
		}
		if(count == 5) {
			System.out.println(name + "�¸�");
			return 1;
		}
		
		// �밢�� ��
		_x = chX;
		_y = chY;
		count = 0;
		while(omok[_y][_x] == turn && _y > 0 && _x > 0) {
			_y--;
			_x--;
		}
		while(omok[_y++][_x++] == turn && _y <= 17 && _x <= 17) {
			count++;
			
		}
		if(count == 5) {
			System.out.println(name + "�¸�");
			return 1;
		}
		
		// �밢�� ��
		_x = chX;
		_y = chY;
		count = 0;
		while(omok[_y][_x] == turn && _y > 0 && _x > 0) {
			_y++;
			_x--;
		}
		while(omok[_y-- < 0 ? 0 : _y][_x++] == turn && _y <= 17 && _x <= 17) {
			// omok[][] ���� y ���� ���ǹ��� ���� ������ a, 1 �� �Է½� 
			// y ���� -1 �� ���������� �Ѿ�� ������ ����ؼ� ����
			count++;
		}
		if(count == 5) {
			System.out.println(name + "�¸�");
			return 1;
		}
		
		return 0;
	}
}