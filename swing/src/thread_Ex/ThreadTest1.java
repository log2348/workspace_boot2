package thread_Ex;

public class ThreadTest1 {

	// 코드의 시작점
	// 메인 쓰레드가 동작하는 부분
	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			System.out.println(i);
			
			try {
				Thread.sleep(200); // 0.2초 쉬었다가 동작
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
