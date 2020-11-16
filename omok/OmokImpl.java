package omok;

public class OmokImpl implements OmokInterface {
	int[][] omok = new int[18][18];
	// 오목판 크기 x, y 전체 좌표
	
	
	// 오목판 출력 함수 
	public void viewOmok() {		
		int numY = 0;				
		// 오목 좌측 좌표값 초기화
		
		int numX = 0;				
		// 오목 하단 좌표값 초기화
		
		for(int[] i : omok) {
		
			
			System.out.print(numY < 10 ? numY++ + " " : numY++);
			// 좌측에 Y 좌표 표시
			
			
			for(int j : i) {
				if(j == 1) {
					System.out.print(" ●");	
					// 흑돌 표시
				} else if(j == 2) {
					System.out.print(" ○"); 
					// 백돌 표시
				} else {
					System.out.print(" ㆍ"); 
					// 공백 표시
				}
			}
			System.out.print("\n");
		}
		
		System.out.print("  ");
		
		for(int[] i : omok) {

			System.out.print(" " + numX++);
			// 하단에 X축 좌표 표시

		}
		
		System.out.print("\n");
	}
	
	

	
	
	
	// 오목 
	public int OmokAction(int x, int y, int turn) {
		int chX = x;

		
		int chY = y;

		

		int count = 0;
		
		int _x,_y = 0;
		
		String name = new String();
		name = (turn == 1) ? "흑돌" : "백돌"; 
		
		if( omok[chY][chX] != 0 ) {
			System.out.println("이미 오목돌이 설치되어 있습니다.");
			System.out.println("다시 놓아주십시오.");
			return 2;
		} else {
			omok[chY][chX] = turn;
		}
		
		
		
		
		
		
		// 가로 체크 코드
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
			System.out.println(name + "승리");
			return 1;
		}
		
		// 세로 체크 코드
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
			System.out.println(name + "승리");
			return 1;
		}
		
		// 대각선 ↘
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
			System.out.println(name + "승리");
			return 1;
		}
		
		// 대각선 ↗
		_x = chX;
		_y = chY;
		count = 0;
		while(omok[_y][_x] == turn && _y > 0 && _x > 0) {
			_y++;
			_x--;
		}
		while(omok[_y-- < 0 ? 0 : _y][_x++] == turn && _y <= 17 && _x <= 17) {
			// omok[][] 에서 y 값에 조건문을 넣은 이유는 a, 1 을 입력시 
			// y 값이 -1 로 음수값으로 넘어가면 오류를 출력해서 조정
			count++;
		}
		if(count == 5) {
			System.out.println(name + "승리");
			return 1;
		}
		
		return 0;
	}
}