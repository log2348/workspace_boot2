package stream_ex;

import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamEx02 {
	
	public static void main(String[] args) {
		// 기반 스트림
		InputStream in = System.in; // 키보드에 연결
		
		// 데코레이터 패턴
		// 보조 스트림
		InputStreamReader ir = new InputStreamReader(in); // 65 --> A (부호화), 추가적 기능...
		/*
		 *  ir 단점 !!
		 *  new char[1000];
		 *  A -> 999개 공간 낭비
		 *  크기를 미리 정해야하기 때문에 잘 사용하지 않음 (특히 통신) --> 해결 방안 BufferedReader
		 */
		try {
			//int data = in.read(); // 1byte(8bit)
			char[] data = new char[3];
			ir.read(data);
			
			System.out.println(data);

		} catch (Exception e) {
			System.out.println("예외 : " + e.getMessage());
		}
	}

}
