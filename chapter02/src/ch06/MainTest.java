package ch06;

public class MainTest {

	public static void main(String[] args) {
		
		Bus bus100 = new Bus(100);
		Bus bus200 = new Bus(200);
		
		Subway subway1 = new Subway(1);
		Subway subway2 = new Subway(2);
		Subway subway3 = new Subway(3);
		
		subway3.take(1300);
		subway3.take(1300);
		
		subway1.showInfo();
		subway2.showInfo();
		subway3.showInfo();	
		
		bus100.take(Bus.charge);	// 1 사람
		bus100.take(Bus.charge); 	// 2 사람
		/*
		bus100.take(1200); 	// 3 사람
		bus100.take(1200); 	// 4 사람
		bus100.showInfo();
		
		System.out.println();
		
		bus200.take(1200);
		bus200.take(1200);
		bus200.take(1200);
		bus200.showInfo();
		*/

	}

}
