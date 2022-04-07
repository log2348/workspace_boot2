package starcraft_game.protoss;

import java.util.Scanner;

public class GateWay {
	
	private final int ZEALOT = 1;
	private final int DRAGOON = 2;
	
	// 1. 질럿을 생산하는 메서드 만들기
	private Zealot makeZealot() {
		System.out.println("질럿을 생성합니다.");
		return new Zealot();
	}
	
	// 2. 드라곤을 생산하는 메서드 만들기
	private Dragoon makeDragoon() {
		System.out.println("드라군을 생성합니다.");
		return new Dragoon();
	}
	
	// 3. 다크 템블러를 생산하는 메서드 만들기
	private DarkTempler makeDarkTempler() {
		System.out.println("다크 템플러를 생성합니다.");
		return new DarkTempler();
	}
	
	// 단, 1, 2, 3 캡슐화(private)
	// 4. 외부에서 접근할 수 있는 메서드 만들기 -- 1 2 3 활용해서
	// (메서드 이름 고민)
	// 단, 스캐너를 사용해서 1. 질럿 2. 드라곤 3. 다크템플러 생산
	// 그리고 생산한 유닛을 반드시 리턴 시키기 !!!
	
	public Protoss createProtossUnit() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("== 프로토스 종족 생성 ==");
		System.out.println("1. 질럿");
		System.out.println("2. 드라군");
		System.out.println("3. 다크 템플러");
		int userInput = sc.nextInt();
		
		Protoss protoss;
		
		if (userInput == ZEALOT) {
			protoss = makeZealot();
		} else if (userInput == DRAGOON) {
			protoss = makeDragoon();
		} else {
			protoss = makeDarkTempler();
		}
		
		sc.close();
		return protoss;
		
	}
	
}
