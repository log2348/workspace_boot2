package file_io.ch04;

import java.io.FileWriter;

/*
 * 문자 단위 출력 스트림
 * 파일에 문자 쓰기 - 1
 */
public class MainTest1 {

	public static void main(String[] args) {
		
		try (FileWriter fw = new FileWriter("writer_1.txt")) {

			fw.write('A'); // 문자 하나 출력
			
			char buf[] = {'B', 'C', 'D', 'E', 'F', 'G'}; // 배열 사용하여 한꺼번에 넣기
			fw.write(buf);
			
			fw.write("\t안녕하세요 ~\n가나다라마"); // String
			
			fw.write(buf, 1, 2); // 1번째 인덱스부터 2 길이만큼
			
			fw.write("65");
			fw.write("/");
			fw.write(65); // 정수값은 부호화 시켜줌
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
