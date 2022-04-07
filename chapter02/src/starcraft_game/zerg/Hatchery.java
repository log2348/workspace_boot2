package starcraft_game.zerg;

import java.util.Scanner;

public class Hatchery {
	
	private Hydra makeHydra() {
		System.out.println("히드라를 생성합니다.");
		return new Hydra();
	}
	
	private Ultra makeUltra() {
		System.out.println("울트라를 생성합니다.");
		return new Ultra();
	}
	
	private Zergling makeZergling() {
		System.out.println("저글링을 생성합니다.");
		return new Zergling();
	}
	
	public Zerg createZergUnit() {
		Scanner sc = new Scanner(System.in);
		
		Zerg zerg;
		
		System.out.println("== 저그 종족 생성 ==");
		System.out.println("1. 히드라");
		System.out.println("2. 울트라");
		System.out.println("3. 저글링");
		int userInput = sc.nextInt();
		
		if (userInput == 1) {
			zerg = makeHydra();
		} else if (userInput == 2) {
			zerg = makeUltra();
		} else {
			zerg = makeZergling();
		}
		
		return zerg;
	}

}
