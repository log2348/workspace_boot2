package ch14;


public class Wizard extends Hero {
	
	public Wizard() {
		setJob("마법사");
	}

	public Wizard(String name) {
		super(name);
		setJob("마법사");
	}
	
	public void freezing(Warrior warrior) {
		System.out.println(getName() + "이(가) " + warrior.getName() + "에게 얼음마법을 씁니다.");
		warrior.beAttacked(getPower());
	}
	
	public void freezing(Archer archer) {
		System.out.println(getName() + "이(가) " + archer.getName() + "에게 얼음마법을 씁니다.");
		archer.beAttacked(getPower());
	}
	
	public void freezing(Wizard wizard) {
		System.out.println(getName() + "이(가) " + wizard.getName() + "에게 얼음마법을 씁니다.");
		wizard.beAttacked(getPower());
	}
}
