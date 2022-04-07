package ch01;

public class StringMainTest {

	public static void main(String[] args) {
		
		//String --> 자바에서 조금 특별한 녀석
		String str1 = new String("Hello, Java!"); // 'new' 키워드 -> Heap 메모리에 할당
		System.out.println(str1);
		
		String str2 = "안녕하세요."; // --> 상수 풀
		int number = 10; // --> 상수 풀
		float a = 10.0f;
		System.out.println(str2);

	}

}
