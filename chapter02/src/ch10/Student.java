package ch10;

public class Student {
	
	private static Student instance;
	
	// 외부에서 생성하지 못하도록 하기
	private Student() {
	}
	
	public static Student getInstance() {
		if(instance == null) {
			instance = new Student();
		}
		return instance;
	}
	
}
