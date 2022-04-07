package ch14;

public class Warrior extends Hero {
	
	public Warrior() {
		setJob("전사");
	}
	
	public Warrior(String name) {
		super(name);
		setJob("전사");
	}
	
	public void comboAttack(Archer archer) {
		System.out.println(getName() + "이(가) " + archer.getName() + "에게 2단 공격을 합니다.");
		archer.beAttacked(getPower());
	}
	
	public void comboAttack(Wizard wizard) {
		System.out.println(getName() + "이(가) " + wizard.getName() + "에게 2단 공격을 합니다.");
		wizard.beAttacked(getPower());
	}
	
	public void comboAttack(Warrior warrior) {
		System.out.println(getName() + "이(가) " + warrior.getName() + "에게 2단 공격을 합니다.");
		warrior.beAttacked(getPower());
	}
}
