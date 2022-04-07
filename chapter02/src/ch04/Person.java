package ch04;

public class Person {
	String name;
	int weight;
	int height;
	int age;
	
	public Person() {
		
	}
	
	public Person(String name, int age, int weight, int height) {
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.height = height;
		
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("몸무게 : " + weight);
		System.out.println("키 : " + height);
	}
	
}
