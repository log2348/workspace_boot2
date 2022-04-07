package ch06_1;

public class Person {
	String name;
	int money;
	
	public Person() {		
	}
	
	public Person(String name, int money) {
		this.name = name;
		this.money = money;
	}
	
	public void takeTaxi(Taxi taxi) {
		taxi.take();
		this.money -= 10000;
	}
	
	public void showPersonInfo() {
		System.out.println(name + "님의 남은 돈은 " + money + "원입니다.");
	}
}
