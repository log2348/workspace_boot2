package ch03;

import java.util.Scanner;

public class MainTest7 {

	public static void main(String[] args) {
		
		// 삼항 연산자
		// 조건식? 결과1 : 결과2
		
		int num1 = (5 > 3) ? 10 : 20;
		System.out.println(num1);
		
		int num2 = (5 < 3) ? 10 : 20;
		System.out.println(num2);
		
		// JDK가 만들어준 도구 JRE
		int max = 0;
		System.out.println("입력받은 두 수 중 큰 수를 출력 하세요");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("입력1: ");
		int x = sc.nextInt();
		System.out.print("입력2: ");
		int y = sc.nextInt();
		
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		
		// 두 값(정수)을 입력 받아서
		// 화면에 큰 수를 출력해주세요.
		// 단, 삼항 연산자를 사용하세요!!!
		
		max = (x > y) ? x: y;
		System.out.println("큰 숫자는: " + max + "입니다.");
		
	}

}
