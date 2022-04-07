package ch10;

public class StudentTest {

	public static void main(String[] args) {
		
		Student student1 = Student.getInstance();
		Student student2 = Student.getInstance();
		Student student3 = Student.getInstance();
		
		System.out.println(student1);
		System.out.println(student2);
		System.out.println(student3);
		
		// 3개의 주소가 같음 --> 싱글톤

	}

}
