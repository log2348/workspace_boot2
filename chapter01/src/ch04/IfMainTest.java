package ch04;

import java.util.Scanner;

public class IfMainTest {

	public static void main(String[] args) {
		// 제어문 if
		// 주어진 조건에 따라서 실행이 이루어지도록 구현
		// 만약.. 이러면
		
		boolean flag = false;
		
		// if문 단독
		if(false) {
			System.out.println("조건식이 true이면 여기 구문이 실행 됩니다.");
		} // end of if
		
		// if else 문
		if(false) {
			System.out.println("true가 실행됩니다.");
		}else {
			System.out.println("false가 실행됩니다.");
		}
		
		System.out.println("성적을 입력하세요");
		Scanner sc = new Scanner(System.in);
		int point = sc.nextInt();
		
		// if else if else 문법
		if(point >= 90) {
			System.out.println("A학점 입니다.");
		} else if(point >= 80) {
			System.out.println("B학점 입니다.");
		} else if(point >= 70) {
			System.out.println("C학점 입니다.");
		} else if(point >= 60) {
			System.out.println("D학점 입니다.");
		} else {
			System.out.println("F학점 입니다.");
		}

	}

}
