package ch04;

public interface RemoteController {
	
	// 인터페이스
	// 구현된 것이 아무것도 없는 밑그림만 있는 기본 설계도
	//멤버변수, 일반메서드를 가질 수 없고 오직 추상 메서드와 상수만을 멤버로 가질 수 있다.
	
	// 추상클래스보다 높은 추상화
	// 강제성이 있는 약속
	// class - public, default 사용
	
	public static final int SERIAL_NUMBER = 1000;
	int SERIAL_NUMBER2 = 100; // 측약형 사용 가능
	
	public abstract void turnOn();
	void turnOff();
	
}
