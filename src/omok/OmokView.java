package omok;

import java.util.Scanner;

public class OmokView {
	public static void main(String[] args) {
		
		int gameState = 0; 		
    		// ������ ���¸� �˷��ִ� ����
    
		int turn = 0; 			
    		// ���������� �˷��ִ� ����
    
		int x = 0; 		
    		// x�� �Է¹޴� ����
    
		int y = 0; 				
    		// y�� �Է¹޴� ����
		
		OmokImpl o = new OmokImpl(); 
		
		Scanner sc = new Scanner(System.in);
		
		// ������� ��ǥ �Է� �޴� �޼ҵ�
		while(gameState == 0) {
			o.viewOmok();		
      			// �������� ����մϴ�.
			
			System.out.println("x ��ǥ�� �Է��ϼ���.");
			x = sc.nextInt();
			System.out.println("y ��ǥ�� �Է��ϼ���.");
			y = sc.nextInt();
			
			turn = (turn == 1) ? 2 : 1;
			
			gameState = o.OmokAction(x, y, turn);
					
	
			
		while(gameState == 2) {
			o.viewOmok();		
				
			turn = (turn == 1) ? 2 : 1;
				
			System.out.println("x ��ǥ�� �Է��ϼ���.");
			x = sc.nextInt();
			System.out.println("y ��ǥ�� �Է��ϼ���.");
			y = sc.nextInt();
				
			turn = (turn == 1) ? 2 : 1;
				
			gameState = o.OmokAction(x, y, turn);
			}
			// �߸� ������ ��
			
		}
		
		o.viewOmok();			
    		// ���� �������� ���
		
		System.out.println("������ �������ϴ�.");
		sc.close();
	}
}