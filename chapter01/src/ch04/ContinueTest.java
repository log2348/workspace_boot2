package ch04;

import java.util.Scanner;

public class ContinueTest {

	public static void main(String[] args) {
		
		// 예약어 continue
		// 무시하고 진행하는 continue
		
		// 스캐너를 사용해서 사용자의 입력값을 받아서 배수의 개수를 출력하는 프로그램을 만들어보세요
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("배수를 입력하세요: ");
		final int MULTIPLE = scanner.nextInt();
		
		System.out.print("최대값을 입력하세요: ");
		final int MAX = scanner.nextInt();
		
		final int FIRST_VALUE = 1;
		int num;
		int count = 0;
		
		for(num = FIRST_VALUE; num <= MAX; num++) {
			
			// 만약 3의 배수이면 화면에 출력하지 마세요 !!
			if(num % MULTIPLE == 0) {
				count++;
				// count = count + 1;
				continue; 	// <-- 무시하고 진행하라
			}
			//System.out.println("num: " + num);
		}
		
		System.out.println(MULTIPLE + "의 배수는 " + count + "개 입니다.");

	}

}
