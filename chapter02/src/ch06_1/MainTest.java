package ch06_1;

public class MainTest {

	public static void main(String[] args) {
		Taxi taxi1 = new Taxi("잘간다 운수택시");
//		Person person1 = new Person();
		Person person2 = new Person("Edward", 20000);

//		person1.takeTaxi(taxi1);

		person2.takeTaxi(taxi1);	
		person2.showPersonInfo();
		taxi1.showTaxiInfo();

	}

}
