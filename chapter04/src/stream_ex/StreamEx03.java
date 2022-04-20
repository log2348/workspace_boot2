package stream_ex;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamEx03 {
	
	public static void main(String[] args) {
		
		InputStream in = System.in; // 키보드에 연결		
		InputStreamReader ir = new InputStreamReader(in); // 65 --> A (부호화), 추가적 기능...
		
		// 보조 스트림 -> 직접 읽고 쓰는 기능이 없기 때문에 생성자에 기반 스트림을 넣어 사용
		BufferedReader br = new BufferedReader(ir); // String으로 받을 수 있다.
		// 통신에는 항상 인코딩, 디코딩이 들어간다 (버퍼가 달려있다)
		
		try {
			// "HEL" + "LO";
			String data = br.readLine();
			System.out.println(data);

		} catch (Exception e) {
			System.out.println("예외 : " + e.getMessage());
		}
	}

}
