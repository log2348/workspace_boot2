package ch13_1;

public class Zealot extends Unit {
	
	// 부모에 있는 생성자를 먼저 호출 해야 한다.
	public Zealot(String name) {
		super(name);
		//this();
		//super.name = name;
		
	}
	
	// 공격 메서드
	
	public void beAttacked(int power) {
		super.hp -= power;
	}
	
//	// 메서드 오버로딩
//	// (메서드 이름이 같은 녀석이라도 매개변수, 리턴타입 다를 경우 컴파일러는 구분이 가능하다.)
//	
//	public void attack(Marine marine) {
//		marine.beAttacked(power);
//	}
//	
//	public void attack(Zergling zergling) {
//		zergling.beAttacked(power);
//	}
//	
//	// 강제 어택
//	public void attack(Zealot zealot) {
//		zealot.beAttacked(power);
//	}
	
	@Override
	public void attack(Unit unit) {
		super.attack(unit);
		
		if(unit instanceof Zealot) {
			((Zealot) unit).beAttacked(power);
			System.out.println("질럿이 질럿을 공격합니다.");
			
		} else if (unit instanceof Zergling) {
			((Zergling) unit).beAttacked(power);
			System.out.println("질럿이 저글링을 공격합니다.");
			
		} else {
			((Marine) unit).beAttacked(power);
			System.out.println("질럿이 마린을 공격합니다.");
			
		}
	}
	
}
