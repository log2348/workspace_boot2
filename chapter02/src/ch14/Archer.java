package ch14;

public class Archer extends Hero {
	
	public Archer() {
	}

	public Archer(String name) {
		super(name);
		setJob("궁수");
	}

	
	public void fireArrow(Warrior warrior) {
		System.out.println(getName() + "이(가) " + warrior.getName() + "에게 불화살을 쏩니다.");
		warrior.beAttacked(getPower());
	}
	
	public void fireArrow(Wizard wizard) {
		System.out.println(getName() + "이(가) " + wizard.getName() + "에게 불화살을 쏩니다.");
		wizard.beAttacked(getPower());
	}
	
	public void fireArrow(Archer archer) {
		System.out.println(getName() + "이(가) " + archer.getName() + "에게 불화살을 쏩니다.");
		archer.beAttacked(getPower());
	}

}
