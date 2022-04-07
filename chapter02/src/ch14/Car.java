package ch14;

public class Car {

	// 포함 관계(멤버변수가 똑같다 하더라도 자동차와 엔진의 관계를 생각했을 때 포함관계로 만드는 게 적절하다.)
	String name;
	int price;
	Engine engine;
	
	public Car() {
		engine = new Engine("gdr엔진", 6000);
	}

	// 도전 과제
	// 각각의 클래스를 메모리에 올려서 값을 넣고
	// 화면에 결과를 출력하시오 !!


	public void showInfo() {
		System.out.println("====== 차량 정보 ======");
		System.out.println("이름 : " + name);
		System.out.println("가격 : " + price);
		System.out.println("====== 엔진 정보 ======");
		System.out.println("이름 : " + engine.name);
		System.out.println("가격 : " + engine.price);
	}
	
	// private으로 막아놨을 때 getter 사용

}
