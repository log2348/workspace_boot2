package file_io.ch03;

import java.io.FileOutputStream;

/*
 * 바이트 단위 출력
 * 파일에 한 바이트씩 쓰기
 */
public class MainTest1 {

	public static void main(String[] args) {
		
		// FileOutputStream은 파일이 없다면 자동으로 파일을 생성
		try(FileOutputStream fos = new FileOutputStream("output_a.txt", true)) { // 파일 생성, 생성자에 true지정하면 덮어서 써줌
			/*
			 * 1.
			 * 2.
			 * 3.
			 * 4.
			 * 5. 
			 */
			fos.write(65);
			fos.write(66);
			fos.write('C');
			fos.write('D');
			fos.write('E');

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(">>>> 출력 처리가 끝 <<<<");

	}

}
