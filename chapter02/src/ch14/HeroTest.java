package ch14;

import java.util.Scanner;

public class HeroTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String characterName = null;
		boolean flag = true;
		int select = 0;
		
		Hero hero;
		
//		Warrior warrior1 = new Warrior("전사 1", 100);
//		 Archer archer1 = new Archer("궁수 1", 80);
//		 Wizard wizard1 = new Wizard("마법사 1", 120);
//		 
//		 warrior1.comboAttack(archer1);
//		 warrior1.showInfo();		 
		 
		 while(flag) {
			 
			 System.out.println("==== 캐릭터를 생성합니다. ====");			 
			 System.out.println("[ 캐릭터 선택 ]");
			 System.out.println("1. 전사");
			 System.out.println("2. 궁수");
			 System.out.println("3. 마법사");
			 System.out.println("0. 프로그램 종료");
			 
			 select = scanner.nextInt();
			 
			 if (select == 0) {
				 System.out.println("프로그램을 종료합니다.");
				 flag = false;
				 break;
				 
			 } else if(select == 1) {
				 hero = new Warrior();
				 
				 System.out.println("공격할 유닛 선택");
				 System.out.println("1. 궁수");
				 System.out.println("2. 마법사");
				 
				 
			 } else if(select == 2) {
				 hero = new Archer(characterName);
				 
			 } else if(select == 3) {
				 hero = new Wizard(characterName);
				 
			 }
		 }
		 System.out.println("프로그램이 종료되었습니다.");
		 
	}

}
