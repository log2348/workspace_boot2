package ch15;

public class Banana extends Fruit {
	
	// 1. 바나나에 origin 멤버 변수 추가
	String origin;
	
	public Banana() {
		name = "바나나";
		price = 2000;
		fresh = 100;
		origin = "필리핀";

	}
	
	public void saleBanana() {
		System.out.println("바나나 가격을 할인 합니다.");
		price -= 1000;
	}
	
	@Override
	public void showInfo() {
		super.showInfo();
		System.out.println("원산지 : " + origin);
	}
	
}
