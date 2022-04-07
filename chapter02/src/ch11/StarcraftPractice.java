package ch11;

import java.util.Scanner;

public class StarcraftPractice {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int unitSelect = 0;
		int attackSelect = 0;
		int attackCount = 0;		
		
		Zealot zealot1 = new Zealot("질럿1");
		Marine marine1 = new Marine("마린1");
		Zergling zergling1 = new Zergling("저글링1");
		
		System.out.println("유닛을 선택하세요.");
		System.out.println("1. 질럿\t\t2. 마린\t\t3. 저글링");
		
		if(unitSelect == 1) {
			
			zealot1.showInfo();
			System.out.println("공격할 유닛을 선택하세요.");
			System.out.println("1. 마린\t\t2. 저글링");
			attackSelect = scanner.nextInt();
			
			if(attackSelect == 1) {

				System.out.println("공격 횟수 : ");
				attackCount = scanner.nextInt();
				
				for(int i = 0; i < attackCount; i++) {
					zealot1.attackMarine(marine1);
					System.out.println(">>>>>>>");
				}
			} else if(attackSelect == 2) {
				System.out.println("공격 횟수 : ");
				attackCount = scanner.nextInt();
				
				for(int i = 0; i < attackCount; i++) {
					zealot1.attackMarine(marine1);
					System.out.println(">>>>>>>");
			}
			
		} 
			
		} else if(unitSelect == 2) { // 마린선택
			
			marine1.showInfo();
			System.out.println("공격할 유닛을 선택하세요.");
			System.out.println("1. 질럿\t\t2. 저글링");
			attackSelect = scanner.nextInt();
			
		} else if(unitSelect == 3) {
			
			zergling1.showInfo();
		}
		

	} 

}
