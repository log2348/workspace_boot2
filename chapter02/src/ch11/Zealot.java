package ch11;

public class Zealot {

	private static int zealotSId;

	private int power;
	private int hp;
	private int id;
	private String name;

	public Zealot(String name) {
		this.name = name;
		zealotSId++;
		this.id = zealotSId;
		power = 10;
		hp = 100;

	}

	// getter
	public int getPower() {
		return power;
	}

	public int getHp() {
		return hp;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public static int getsId() {
		return zealotSId;
	}

	// 객체와 객체를 상호작용하게 코딩한다.
	// 마린을 공격합니다.
	public void attackMarine(Marine marine) {
		// 질럿 공격력 10
		marine.beAttacked(power);
		System.out.println("질럿이 마린을 공격합니다.");
	}

	// 저글링을 공격합니다.
	public void attackZergling(Zergling zergling) {
		zergling.beAttacked(power);
		System.out.println("질럿이 저글링을 공격합니다.");
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
		System.out.println(this.name + "의 생명력이 " +hp + " 상승하였습니다.");
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
