package thread_Ex;

import javax.swing.JFrame;

/*
 * Runnable 인터페이스를 구현해서 만들기 - 2번째 방법
 * 자바는 다중 상속이 허용되지 않으므로 다른 클래스에서 상속한 경우
 * thread를 만들기 위해 Runnable interface를 구현하도록 합니다.
 */

class MyRunnable extends JFrame implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.print("↗ ");
		}

	}

}

public class RunnableTest1 {

	public static void main(String[] args) {

		// 사용하는 방법
		MyRunnable myRunnable = new MyRunnable();
		// Runnable 구현한 객체는 Thread를 생성해서 매개 변수에 담고 Thread를 시작하면 된다.
		Thread thread1 = new Thread(myRunnable);
		thread1.start();

		Thread thread2 = new Thread(myRunnable);
		thread2.start();

	}

}
