package starcraft_game.terran;

import java.util.Scanner;

public class Barrak {
	
	private FireBat makeFireBat() {
		System.out.println("파이어뱃을 생성합니다.");
		return new FireBat();
	}
	
	private Marine makeMarine() {
		System.out.println("마린을 생성합니다.");
		return new Marine();
	}
	
	public Terran createTerranUnit() {
		Scanner sc = new Scanner(System.in);
		
		Terran terran;
		
		System.out.println("== 테란 종족 생성 ==");
		System.out.println("1. 파이어뱃");
		System.out.println("2. 마린");
		int userInput = sc.nextInt();
		
		if(userInput == 1) {
			terran = makeFireBat();
		} else {
			terran = makeMarine();
		} 
		return terran;
		
	}
	
}
