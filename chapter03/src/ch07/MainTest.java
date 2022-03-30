package ch07;

public class MainTest {

	public static void main(String[] args) {
		Car car1 = new Car("제네시스", 1234);
		Car car2 = new Car("k5", 5678);
		
		if(car1.equals(car2)) {
			System.out.println("같은 기종입니다.");
		} else {
			System.out.println("다른 기종입니다.");
		}

	}

}
