package ch11;

public class Zergling {

	private static int marinSId;

	private int power;
	private int hp;
	private int id;
	private String name;

	public Zergling(String name) {
		this.name = name;
		marinSId++;
		this.id = marinSId;
		power = 5;
		hp = 100;
	}

	public int getPower() {
		return power;
	}

	public int getHp() {
		return hp;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	// 질럿을 공격합니다.
	public void attackZealot(Zealot zealot) {
		zealot.beAttacked(power);
		System.out.println("저글링이 질럿을 공격합니다.");
	}

	// 마린을 공격합니다.
	public void attackMarine(Marine marine) {
		marine.beAttacked(power);
		System.out.println("저글링이 마린을 공격합니다.");
	}

	// 내가 공격을 당합니다.
	public void beAttacked(int power) {
		
		if (this.hp < 1) {
			System.out.println(this.name + "은 사망하였습니다.");
		} else {
			this.hp -= power;	
		}
	}

	public void upgradePower() {
		this.power += 10;
		System.out.println(this.name + "의 공격력이 10 상승하였습니다.");
	}

	public void heal(int hp) {
		this.hp += hp;
		System.out.println(this.name + "의 생명력이 " + hp + " 상승하였습니다.");
	}

	public void showInfo() {
		System.out.println("==== 정보창 ====");
		System.out.println("이름 : " + name);
		System.out.println("아이디 : " + id);
		System.out.println("공격력 : " + power);
		System.out.println("생명력 : " + hp);
		System.out.println("=============");
	}

}
