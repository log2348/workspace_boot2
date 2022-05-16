package ch03;

public class StringConcatImplTest {
	
	public static void main(String[] args) {
		
		String s1 = "Hello";
		String s2 = "java";
		
		// 우리가 알고 있던 OOP 방식
		StringConcatImpl impl = new StringConcatImpl();
		impl.makeString(s1, s2);
		
		// 익명 클래스 활용(익명 구현 클래스)
		IStringConcat iStringConcat = new IStringConcat() {
			
			@Override
			public void makeString(String s1, String s2) {
				System.out.println("[[[" + s1 + " : " + s2 + "]]]");
				
			}
		};
		// 클래스 설계 없이 바로 사용 가능
		
		// 람다 표현식으로 설계해서 사용하기
		
		IStringConcat iStringConcatLamda = (a, b) -> System.out.println("[[[" + a + " : " + b + "]]]");
		iStringConcatLamda.makeString("안녕", "람다");
		
		// 람다 표현식으로 Thread 구현
		new Thread(() -> {});
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
