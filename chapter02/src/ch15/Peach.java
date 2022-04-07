package ch15;

public class Peach extends Fruit {

	// 상속을 절대 먼저 쓰지말고 밑의 코드부터 올라가는 개념으로 쓰기!! (bottom-up)
	
	public Peach() {
		name = "복숭아";
		price = 5000;
		fresh = 90;
	}
	
}
