package ch21;

public class AICar extends Car {

	@Override
	void drive() {
		System.out.println("자율주행 합니다.");
		
	}

	@Override
	void stop() {
		System.out.println("스스로 멈춥니다.");
		
	}

	@Override
	public void wiper() {
		System.out.println("와이퍼 동작합니다.");
		
	}
	
	@Override
	public void washCar() {
		System.out.println("스스로 세차 합니다.");
	}

	// abstract - 추상메서드 자유롭게 만들 수 있도록 강제성 부여

}
