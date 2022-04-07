package ch13_1;

public class Marine extends Unit {

	public Marine(String name) {
		super(name);
		// 부모생성자 호출
		//super.name = name;
	}
	
	public void beAttacked(int power) {
		super.hp -= power;
	}
	
//	public void attack(Zealot zealot) {
//		zealot.beAttacked(power);
//	}
//	
//	public void attack(Zergling zergling) {
//		zergling.beAttacked(power);
//	}
//	
//	// 강제 어택
//	public void attack(Marine marine) {
//		marine.beAttacked(power);
//	}
	
	@Override
	public void attack(Unit unit) {
		// 넘어오는 데이터 타입을 확인해서 처리를 해주세요
		//instanceof
		super.attack(unit);
		
		if(unit instanceof Zealot) {
			((Zealot) unit).beAttacked(power);
			System.out.println("마린이 질럿을 공격합니다.");
			
		} else if (unit instanceof Zergling) {
			((Zergling) unit).beAttacked(power);
			System.out.println("마린이 저글링을 공격합니다.");
			
		} else {
			((Marine) unit).beAttacked(power);
			System.out.println("마린이 마린을 공격합니다.");
			
		}

	}

}
