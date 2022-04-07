package ch14;

import java.util.Scanner;

public class Hero {
	protected String name;
	private String job;
	private int hp;
	private int power;
	private Scanner scanner;

	public Hero(String name) {
		scanner = new Scanner(System.in);
		System.out.print("캐릭터 이름 입력 : ");
		this.name = scanner.nextLine();
		System.out.println();
		System.out.println("==== 캐릭터 생성 완료 ====");
	}
	
	public Hero() {
		
	}

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public int getPower() {
		return power;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setPower(int power) {
		this.power = power;
	}

	// 기본공격
	public void attack(int power) {
		
	}

	public void beAttacked(int power) {		
		if(hp < 1) {
			System.out.println(name + "이(가) 사망하였습니다.");
			this.hp = 0;
		} else {
			this.hp -= power;
		}		
	}
	
	public void showInfo() {
		System.out.println("이름 : " + name);
		System.out.println("체력 : " + hp);
		System.out.println("공격력 : " + power);
		System.out.println("직업 : " + job);

	}

}
