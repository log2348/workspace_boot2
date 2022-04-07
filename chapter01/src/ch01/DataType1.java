package ch01;

public class DataType1 {

	public static void main(String[] args) {
		
		/*
		 * 데이터 타입의 종류
		 * 1. 기본 자료형(primitive data type) --> 기본 값
		 * 2. 참조타입(Reference Type) --> 주소 값
		 * 
		 * 정수형(1, 10, 500..)
		 * 크기가 다르다(담을 수 있는 숫자의 크기가 제한 되어있다.)
		 */
		
		byte b;	// 1바이트 (8bit)
		short s;	// 2바이트 (16bit)
		int i;	// 4바이트 (32bit)
		long l;	// 8바이트 (64bit)
		
		b = 127;
//		b = 128;
		b = -128; // -128 ~ - 127
		
		// 문제 : short 크기는 얼마부터 얼마까지의 숫자가 들어가는가?
		s = 128;
		
		i = 2100000000;		// int는 약 21억까지의 정수를 담을 수 있다.
//		i = 2200000000;
		
		l = 2200000000L;		// 대문자 L은 접미사(대소문자 가능)
		
		// 변수에 접근해서 값을 변경해서 화면wjd에 출력하기
		b = 122;
		s = 200;
		i = 2000000000;
		l = 2100000000L;
		
		System.out.println(b);
		System.out.println(s);
		System.out.println(i);
		System.out.println(l);

	}

}
