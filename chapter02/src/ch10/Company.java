package ch10;

public class Company {
	
	// 싱글톤 패턴	
	private static Company instance = new Company();
	
	// 생성자를 통해서 객체를 생성하지 못하게 막는다.
	private Company() {
		
	}
	
	// 여기서 문제점 - nullPointerException
	// 멤버변수 사용 x
	public static Company getInstance() {
		if(instance == null) {
			instance = new Company();
		} 
		return instance;

	}
	
	
}
