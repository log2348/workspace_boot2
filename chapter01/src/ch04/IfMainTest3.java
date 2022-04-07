package ch04;

public class IfMainTest3 {

	public static void main(String[] args) {
		
		// 이중 for문을 사용해서 구구단을 출력해주세요.
		
		for(int i = 2; i < 10; i++) {
			for(int j = 1; j < 10; j++) {
				System.out.println(i + " * " + j + " = " + i * j);
			}	// end of inner for
			System.out.println("------------------------------------------");
		}	// end of outer for
		
		// 화면에 2단부터 9단까지 출력 합니다.
		// num10이라는 변수가 2부터 9까지 반복하는 for문을 작성해 봅시다.
		
		/*
		for(int num10 = 2; num10 < 10; num10++) {
			for(int i = 1; i < 10; i++) {
				System.out.println(num + " * " + i + " = " + (num * i));
			}
		}
		*/		
	}

}
