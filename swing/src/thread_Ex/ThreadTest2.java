package thread_Ex;

// 1번째 방법 : 다른 작업자를 생성하는 방법(상속) --> run() 메소드 구현
class MyCustomThread extends Thread {
	
	String name;
	
	public MyCustomThread(String name) {
		this.name = name;
	}
	
	// Thread는 약속 되어 있다.
	// run이라는 메서드는 쓰레드가 동작을 명령 받으면 수행하는 코드 영역
	@Override
	public void run() {
		int i;
		for(i = 0; i < 50; i++) {
			System.out.println(name + " : " + i);
			
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}

public class ThreadTest2 {

	public static void main(String[] args) {

		System.out.println(Thread.currentThread()); // 현재 쓰레드가 누구인가
		System.out.println("메인 쓰레드 시작");

		// 작업자 호출 및 작업 수행(run)을 순서대로 호출 하더라도 실제 작업 순서는 랜덤으로 이루어짐
		// 개발자가 쓰레드의 순서를 컨트롤 할 수 없음
		
		// 작업자 만들기
		MyCustomThread t1 = new MyCustomThread("서브 작업자1");
		
		// 쓰레드를 시작하는 방법 - start() 해야만 run() 메소드 실행
		t1.start();
		
		
		MyCustomThread t2 = new MyCustomThread("서브 작업자2");
		t2.start();
		
		MyCustomThread t3 = new MyCustomThread("서브 작업자3");
		t3.start();

		System.out.println("메인 쓰레드 종료");
	}

}
