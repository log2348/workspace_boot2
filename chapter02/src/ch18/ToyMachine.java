package ch18;

import java.util.Random;
import java.util.Scanner;

public class ToyMachine {
	// 장난감은 3개 만들어 줍니다.
	// 사용자 랜덤 함수 사용해서 답을 맞추면
	// 장난감을 반환합니다.
	// 아니면 꽝!!!

	private static Toy[] toy = new Toy[3];

	Scanner scanner;

	public ToyMachine() {
		
		toy[0] = new Toy("토끼 인형");
		toy[1] = new Toy("강아지 인형");
		toy[2] = new Toy("로봇");
		
	}

	public void randomValue() {
		Random random = new Random();
		int ranNumber = random.nextInt(3) + 1;

		scanner = new Scanner(System.in);
		System.out.println("1 ~ 3 까지 중 숫자를 입력하세요.");
		int userInput = scanner.nextInt();

		if (ranNumber == userInput) {
			System.out.println(">>>>>>> 성공");
			System.out.println(">>>>>>> " + toy[ranNumber].name + "을(를) 반환합니다.");
		} else {
			System.out.println("아쉽지만 꽝 !!!");
			System.out.println("정답은 " + ranNumber + " 입니다.");
		}
		
		scanner.close();
	}

}
