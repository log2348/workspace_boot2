package thread_Ex;

class BankAccount {

	private int money = 100_000;

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	// 입금 기능
	// synchronized 키워드 사용
	public synchronized void saveMoney(int money) {

		int currentMoney = getMoney();

		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMoney(currentMoney + money);
		System.out.println("입금 후 계좌 잔액 : " + getMoney());
	}

	// 출금 기능
	public void withdraw(int money) {

		// synchronized 블럭
		synchronized (this) {
			int currentMoney = getMoney();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			setMoney(currentMoney - money);
			System.out.println("출금 후 계좌 잔액 : " + getMoney());

		}

	}

}

// 아버지 입금함 (네트워크가 느려서 시간이 조금 걸림)
class Father extends Thread {

	BankAccount account;

	public Father(BankAccount account) {
		this.account = account;
	}

	@Override
	public void run() {
		account.saveMoney(10_000);
	}
}

class Mother extends Thread {

	BankAccount account;

	public Mother(BankAccount account) {
		this.account = account;
	}

	@Override
	public void run() {
		account.withdraw(5000);
	}
}

public class SharedResource {

	public static void main(String[] args) {
		// 현재 10만원 저금되어있음
		BankAccount account = new BankAccount();

		Father father = new Father(account);
		Mother mother = new Mother(account);

		// 아버지가 입금합니다. start() -> run() 메소드 실행
		father.start();
		// 어머니가 출금합니다.
		mother.start();

		// 정상처리 금액 : 10 + 1 - 0.5 = 10만5천원

		/*
		 * 공유되는 자원을 활용 할 때 의도치 않은 결과를 생성할 수 있다. 해결 방법 --> 동기화(synchronization)
		 */
	}

}
