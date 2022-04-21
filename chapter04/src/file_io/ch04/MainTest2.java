package file_io.ch04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * BufferedWriter 이용한 파일에 문자 쓰기
 */
public class MainTest2 {

	public static void main(String[] args) {
		

		String text = "File Writer Test";
		String fileName = "result.txt";
		// 버퍼는 자기 공간이 다 채워지면 자동으로 전달 --> flush 사용
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true)); // 보조스트림 - 기능 확장
			bw.write(text);
			bw.flush(); // 강제로 보낼 때
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
