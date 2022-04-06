package thread_Ex;

import javax.swing.JFrame;

class MyRunnable2 extends JFrame {
	
	int grade;
	// 익명 구현 객체 (자바문법) -> 내부 익명 구현 객체를 변수에 담기, 멤버변수와 같은 개념
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			
			for (int i = 0; i < 10; i++) {
				System.out.println("-");
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	};
	
	// 생성자
	public MyRunnable2() {
		//runnable.run();
	}
}

public class RunnableTest2 {

	public static void main(String[] args) {
		MyRunnable2 myRunnable2 = new MyRunnable2();
		// 여기서 실행 --->
		myRunnable2.runnable.run();

	}

}
