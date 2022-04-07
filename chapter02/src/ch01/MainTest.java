package ch01;

public class MainTest {
	
	// 코드의 시작점
	public static void main(String[] args) {
		
		System.out.println("프로그램을 시작합니다.");
		
		int num1 = 10;	// 변수의 선언과 초기화 (기본값)
		int num2; 	// 변수의 선언
		
		Student s1;	// Student 타입으로 변수를 선언(s1) (Student 클래스 타입) --> 주소값 --> 참조변수, 레퍼런스 타입이다
		Student s2; 	// Student 타입으로 변수를 선언(s2)
		
		// s1 초기화 하기
		s1 = new Student();		// s1을 초기화 했다. (값을 넣다) (주소값) Heap 메모리 안의 실제 존재를 찾아갈 수 있는 주소 값이 담긴다.
		s2 = new Student();	// s2를 초기화 했다. (값을 넣다) (주소값)
		
		/*
		System.out.println(num1);
		System.out.println(s1);
		System.out.println(s2);
		*/
		
		// s1의 name이라는 변수에 접근해서 홍길동이라는 값을 넣겠다
		s1.name = "홍길동";
		s1.height = 170;
		s1.weight = 90.5;
		
		s2.name = "이순신";
		s2.height = 190;
		s2.weight = 80.0;
		
		// s1이라는 녀석에 height값을 화면에 출력하려면
		System.out.println(s1);
		System.out.println(s1.height);
		System.out.println(s1.weight);
		System.out.println(s1.name);
		System.out.println("======================");
		
		// s2 출력해주세요
		System.out.println(s2);
		System.out.println(s2.height);
		System.out.println(s2.weight);
		System.out.println(s2.name);	
		
		
		// 클래스, 객체, 인스턴스 이해
		
		// 1. 전사를 메모리에 올려주세요.
		// 2. 전사변수에 값을 지정해주세요.
		// 3. 각각의 값들을 화면에 출력해주세요.
		
		// 변수 --> 지역변수
		Warrior w1 = new Warrior();
		w1.name = "전사1";
		w1.height = 5;
		w1.color = "초록색";
		w1.power = 200;
		
		System.out.println("========상태창========");
		System.out.println("캐릭터의 이름: " + w1.name);
		System.out.println("캐릭터의 키: " + w1.height);
		System.out.println("캐릭터의 색깔: " + w1.color);
		System.out.println("캐릭터의 능력치: " + w1.power);
		
		Warrior w2 = new Warrior();
		w2.name = "전사2";
		w2.height = 1;
		w2.color = "빨간색";
		w2.power = 500;
		
		System.out.println("========상태창========");
		System.out.println("캐릭터의 이름: " + w2.name);
		System.out.println("캐릭터의 키: " + w2.height);
		System.out.println("캐릭터의 색깔: " + w2.color);
		System.out.println("캐릭터의 능력치: " + w2.power);
		
	}
}
