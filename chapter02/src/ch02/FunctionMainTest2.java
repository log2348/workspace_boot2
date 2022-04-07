package ch02;

public class FunctionMainTest2 {
	
	// 코드의 시작
	public static void main(String[] args) {
		
		int num1 = 10;
		int num2 = 20;
		
		int total =  addNum(num1, num2);
		System.out.println("total: " + total);
		sayHello("안녕하세요 홍길동입니다.");
		int sum = calcSum();
		System.out.println(sum);
		
		int total2 = intAdd(10, 20, 30);
		double total3 = doubleAdd(10, 20, 30);
		System.out.println(total2); 
		System.out.println(total3);
		printArticle("Hello");

	} // end of main
	
	public static int addNum(int n1, int n2) {
		int result;
		
		result = n1 + n2;
		
		return result;
	}
	
	public static void sayHello(String greeting) {
		System.out.println(greeting);
	}
	
	public static int calcSum() {
		int sum = 0;
		int i;
		
		for(i = 0; i < 100; i++) {
			sum += i + 1;
		}
		return sum;		
	}
	
	// 함수를 만들어 봅시다.
	// 1. 리턴값이 int, 매개변수 n1, n2, n3 이름은 intAdd
	// 2. 리턴값이 double, 매개변수 n1, n2, n3 이름은 doubleAdd
	// 3. 리턴값이 void, 매개변수는 String article 이름은 printArticle
	// 4. 직접 사용해봅시다. 머리속에 메모리 구조와 동작 방식을 꼭 생각하면서 만들어 주세요.
	
	public static int intAdd(int n1, int n2, int n3) {
		int result;
		result = n1 + n2 + n3;
		return result;
	}
	
	public static double doubleAdd(double n1, double n2, double n3) {
		double result;
		result = n1 + n2 + n3;
		return result;
	}
	
	public static void printArticle(String article) {
		System.out.println(article);
	}
	
}
