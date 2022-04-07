package ch03;

public class ShortCircuitTest {

	public static void main(String[] args) {
		int num1 = 5;
		int i = 0;
		// 15 < 10 --> false
		boolean value1 = ((num1 = num1 + 10) < 10) && ((i = i + 2) < 10);
		System.out.println("value1: " + value1);
		System.out.println("num1: " + num1);
		System.out.println("i: " + i); // 앞 연산에서 false라는 값이 나왔기 때문에 뒤의 식은 실행되지 않았다.
		
		System.out.println("==================");
		// 15 < 10 ---> false 	|| 	2 < 10 ---> true
		boolean value2 = ((num1 = num1 + 10) < 10) || ((i = i + 2) < 10);
		System.out.println("value2: " + value2);
		System.out.println("num1: " + num1);
		System.out.println("i: " + i);
	}

}
