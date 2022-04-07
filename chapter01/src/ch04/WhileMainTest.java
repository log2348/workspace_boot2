package ch04;

public class WhileMainTest {
	
	// 코드 시작점(메인 작업자)
	public static void main(String[] args) {
		
		// 1 ~ 10 까지 덧셈 연산
		int num = 1;
		int sum = 0;
		
		while(num <= 10) {
			System.out.println("현재값: " + num);
			sum = num;
			num++;	// 변수에 접근해서 1씩 값을 증가 시켜주는 녀석
			
			try {
				Thread.sleep(1000); 	// 1초동안 잠들어
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("연산에 대한 결과값은 " + sum + "입니다.");

	}

}
