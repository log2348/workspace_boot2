package ch13_1;

public class MainTest {

	public static void main(String[] args) {
		Zealot zealot1 = new Zealot("질럿1");
		Zergling zergling1 = new Zergling("저글링1");
		Marine marine1 = new Marine("마린1");
		
		zealot1.attack(marine1);
		marine1.attack(zergling1);
		zergling1.attack(zergling1);
		
		zealot1.showInfo();
		
	}

}
