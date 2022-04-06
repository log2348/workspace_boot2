package thread_Ex;

class PriorityThread extends Thread {
	@Override
	public void run() {

		int sum = 0;
		
		Thread t = Thread.currentThread();
		System.out.println(t + "start");
		
		for (int i = 0; i < 1000000; i++) {
			sum += i;
		}
		
		System.out.println(t.getPriority() + "end");
	}
}

public class ThreadTest4 {

	public static void main(String[] args) {
		int i;
		
		// 우선순위를 부여했지만 마음대로 되지 않는다
		for (i = Thread.MIN_PRIORITY; i < Thread.MAX_PRIORITY + 1; i++) {
			PriorityThread pt = new PriorityThread();
			pt.setPriority(i);
			pt.start();
			
		}

	}

}
