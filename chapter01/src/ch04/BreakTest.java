package ch04;

public class BreakTest {

	public static void main(String[] args) {
		
		// 예약어 break
		// 중간에 멈추는 break
		
		for(int i = 1; i < 11; i++) {
			System.out.println("i: " + i);
			// i의 값이 소수 7이면 멈춰라
			if(i % 7 == 0) {
				break;
			} // end of if
		} // end of for

	}

}
