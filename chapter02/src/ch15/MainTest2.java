package ch15;

import java.util.Scanner;

public class MainTest2 {

	public static void main(String[] args) {
		
		final int BANANA = 1;
		final int PEACH = 2;
		
		System.out.println("상품 정보 확인");
		System.out.println("1. 바나나\n2. 복숭아");
		Scanner scanner = new Scanner(System.in);
		int userSelected = scanner.nextInt();
		
		// 단, 다형성을 활용해서 코드를 완성해주세요
		
		Fruit fruit;
		
		//float tempF = (float)10.0;
		
		if(userSelected == 1) {
			// 바나나 객체를 생성해서
			// 상품에 대한 정보를 출력해 주세요
			fruit = new Banana();		// 업캐스팅
			// 바나나의 멤버 변수인 origin 값을 화면에 출력 하시오.
			// 찾을 수 없는 이유 ( 컴파일 시점에는 컴파일러가 데이터의 타입만을 확인 한다)
			String origin = ((Banana)fruit).origin;	// 다운캐스팅
			System.out.println(origin);
		} else {
			// 복숭아 객체를 생성해서
			// 상품에 대한 정보를 출력해주세요		
			fruit = new Peach();			
		}
		fruit.showInfo();
		// 사용자가 입력한 값에 따라서 인스턴스 변수를 쉽게 바꿔줄 수 있다.
		

	}

}
