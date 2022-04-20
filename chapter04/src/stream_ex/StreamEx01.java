package stream_ex;

import java.io.InputStream;

public class StreamEx01 {
	
	public static void main(String[] args) {
		InputStream in = System.in; // 키보드에 연결
		/*
		 *  1. 키보드에 A를 인코딩해서 01000001로 컴퓨터에게 전송
		 *  2. Byte Stream으로 흘러 들어감(Input)
		 *  3. read() 메서드로 01000001 --> 65로 디코딩 한다.
		 *  4. 부호화 65를 문자 A로 변환
		 *  단점 : 1byte만 받음(ABC 입력해도 A만 받음)
		 */
		try {
			int data = in.read(); // 1byte(8bit)
			System.out.println(data);
			System.out.println((char)data); // 부호화
		} catch (Exception e) {
			System.out.println("예외 : " + e.getMessage());
		}
	}

}
