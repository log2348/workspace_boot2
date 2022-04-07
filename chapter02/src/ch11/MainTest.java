package ch11;

import java.util.Scanner;

public class MainTest {

	public static void main(String[] args) {
		
		Zealot zealot1 = new Zealot("질럿1");
		Zealot zealot2 = new Zealot("질럿2");

		Marine marine1 = new Marine("마린1");
		Marine marine2 = new Marine("마린2");

		Zergling zergling1 = new Zergling("저글링1");
		Zergling zergling2 = new Zergling("저글링2");
		
		Scanner scanner = new Scanner(System.in);
		// 실행의 흐름
		// 누구를 공격하시겠습니까?		
		
		String temp = ">";
		for(int i = 0; i < 10; i++) {
			zealot1.attackMarine(marine1);
			System.out.println(temp);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
