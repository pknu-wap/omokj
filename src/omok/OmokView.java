package omok;

import java.util.Scanner;

public class OmokView {
	public static void main(String[] args) {
		
		int gameState = 0; 		
    		// 게임의 상태를 알려주는 변수
    
		int turn = 0; 			
    		// 누구턴인지 알려주는 변수
    
		int x = 0; 		
    		// x축 입력받는 변수
    
		int y = 0; 				
    		// y축 입력받는 변수
		
		OmokImpl o = new OmokImpl(); 
		
		Scanner sc = new Scanner(System.in);
		
		// 사용자의 좌표 입력 받는 메소드
		while(gameState == 0) {
			o.viewOmok();		
      			// 오폭판을 출력합니다.
			
			System.out.println("x 좌표를 입력하세요.");
			x = sc.nextInt();
			System.out.println("y 좌표를 입력하세요.");
			y = sc.nextInt();
			
			turn = (turn == 1) ? 2 : 1;
			
			gameState = o.OmokAction(x, y, turn);
					
	
			
		while(gameState == 2) {
			o.viewOmok();		
				
			turn = (turn == 1) ? 2 : 1;
				
			System.out.println("x 좌표를 입력하세요.");
			x = sc.nextInt();
			System.out.println("y 좌표를 입력하세요.");
			y = sc.nextInt();
				
			turn = (turn == 1) ? 2 : 1;
				
			gameState = o.OmokAction(x, y, turn);
			}
			// 잘못 놓았을 때
			
		}
		
		o.viewOmok();			
    		// 최종 오목판을 출력
		
		System.out.println("게임이 끝났습니다.");
		sc.close();
	}
}