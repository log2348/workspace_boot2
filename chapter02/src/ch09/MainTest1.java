package ch09;

public class MainTest1 {

	// static 메서드 (함수)
	public static void main(String[] args) {
		
		// StringBuilder, StringBuffer 개념을 이해해보자
		
		// heap 메모리에 인스턴스로 생성
		String a = new String("a");
		
		// 상수풀 메모리에 생성 (메모리 효용 높음 new키워드로 생성하는 것보다)
		String b = "b";
		
		String str0 = "abc";
		String str1 = "abc";
		String str2 = "abc";
		
		str0 += "_0"; // 연산이 일어나면 수정되는 것이 아님
//		str1 += "_1";
		// 상수들에 생성된 녀석들은 immutable 성질을 가진다
		// 즉, 한번 생성된 문자열은 수정할 수 없다
		
		// new String("abc");
		// mutable 수정이 가능하다
		String strA = new String("A");
		System.out.println(System.identityHashCode(strA));
		
		strA += "_1";
		System.out.println(System.identityHashCode(strA)); 	// 새로운 번지 생성, 주소값 달라짐
		
		System.out.println(strA);
		
		// System.out.println(str0 == str1); 	// false 주소값 다름
		
		/*
		String str2 = "abc";
		String str3 = "abc";
		
		// 클래스 타입 (주소값 비교)
		// str1 == str2
		System.out.println(str1 == str2); 	// --> false
		System.out.println("=====================");
		System.out.println(str2 == str3); 	// 주소값 같음
		
		// str1의 주소값을 출력해보자
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		System.out.println(System.identityHashCode(str3));
		*/
		
	}

}
