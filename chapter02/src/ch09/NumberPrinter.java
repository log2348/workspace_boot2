package ch09;

public class NumberPrinter {
	
	// static - 인스턴스들이 공유할 수 있는 메모리 영역이다(new 키워드 없이도 실행 가능)
	// 먼저 메모리에 올라감
	// 프로그램이 종료되면 사라짐
	private int id;
	public static int waitNumber = 100;
	
	public NumberPrinter(int id) {
		this.id = id;
		waitNumber = 1;
	}
	
	// 번호표를 출력합니다.
	public void printWaitNumber() {
		System.out.println(id + "번 기기의 대기 순번 : " + waitNumber);
		waitNumber++;
	}
}
