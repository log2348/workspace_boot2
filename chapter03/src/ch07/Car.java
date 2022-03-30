package ch07;

public class Car {
	
	private int carNumber;
	private String company;
	private String name;
	
	public Car(String name, int carNumber) {
		this.name = name;
		this.carNumber = carNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Car) {	
			// Car 클래스 타입이 들어오면 안에 접근해서 이름 확인
			Car tempCar = (Car)obj;
			String name = tempCar.name;
			
			if(this.name == name) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
