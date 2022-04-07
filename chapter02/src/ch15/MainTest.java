package ch15;

public class MainTest {

	public static void main(String[] args) {
		
		// 다양한 형태의 클래스(타입)로 바라볼 수 있다. --> 다형성
		// 배 --> 
		
		Fruit fruit1 = new Banana();
		Fruit fruit2 = new Peach();
		
		//////////////
		// 메모리 구조
		fruit1.showInfo();
		fruit2.showInfo();
		
		// instanceof
		
		Fruit fruit;
		fruit = new Peach();
		
		if(fruit instanceof Banana) {
			System.out.println("바나나 타입 확정");
			// 변수에 대한 처리
			String origin = ((Banana)(fruit)).origin;
			// 메서드에 대한 처리
			((Banana)fruit).saleBanana();
			
		} else if(fruit instanceof Peach) {
			System.out.println("복숭아 타입 확정");
		}

	}

}
