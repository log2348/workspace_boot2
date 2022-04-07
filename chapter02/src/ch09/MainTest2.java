package ch09;

public class MainTest2 {

	public static void main(String[] args) {
		
		// StringBuilder, StringBuffer 개념을 이해해보자
		// 문자열을 여러번 연결하거나 변경할 때 사용하면 유용하다
		// StringBuffer(멀티 쓰레드에서 동기화를 보장하기 때문에 유용하다)
		// StringBuilder 단일 쓰레드에서 권장 사항이다
		
		String java = new String("java");
		String android = new String("android");
		
		System.out.println(java + android); 	// 새로운 주소 생성 --> 메모리 낭비
		
		StringBuilder sb = new StringBuilder(java);
		System.out.println(System.identityHashCode(sb));
		System.out.println("------------");
		sb.append(android);
		System.out.println(System.identityHashCode(sb));
		
		System.out.println(sb);
		java = sb.toString();
		// String <- StringBuilder (toString() 사용)
 
	}

}
