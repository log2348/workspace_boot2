package ch07;

public class MainTest {

	public static void main(String[] args) {
		Bank bank = new Bank();
		
		// getter : read-only
		bank.setBalance(-10_000);
		int bankBalance = bank.getBalance();
		
		System.out.println(bankBalance);
		
		//bank.deposit(10_000);
		//bank.showInfo();
		
		//bank.withdraw(5_000);
		//bank.showInfo();
		
		// 신입이 실수로 멤버 변수에 접근해서 잔액을 수정함 !!! ㅜㅜ --> 접근제어자로 막아놓기
		// bank.balance = 100_000;
		// bank.showInfo();
		
		// 만약 멤버 변수에 접근해야할 경우가 있다면 어떻게 하나요?
		// get/ set 개념 !!!

	}

}
