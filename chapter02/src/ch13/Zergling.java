package ch13;

public class Zergling extends Unit {

	public Zergling(String name) {
		super(name);
		// super.name = name;

	}

	public void beAttacked(int power) {
		super.hp -= power;
	}
		
		@Override
		public void attack(Unit unit) {
			super.attack(unit);
			
			if(unit instanceof Zealot) {
				((Zealot) unit).beAttacked(power);
				System.out.println("저글링이 질럿을 공격합니다.");
				
			} else if (unit instanceof Zergling) {
				((Zergling) unit).beAttacked(power);
				System.out.println("저글링이 저글링을 공격합니다.");
				
			} else {
				((Marine) unit).beAttacked(power);
				System.out.println("저글링이 마린을 공격합니다.");
				
			}
			
		}
}
