package file_io.ch02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * 입력 스트림
 * 파일에서 한 바이트씩 데이터를 읽기
 * try-with- resource : try(...) 오토 클로즈 제공
 */
public class MainTest2 {

	public static void main(String[] args) {

		System.out.println("시작 - 파일에서 데이터를 읽어서 화면에 출력");

		// while문 사용해서 출력해주세요
		int i;
		try (FileInputStream fis = new FileInputStream("boot_a.txt")) {

			while ((i = fis.read()) != -1) {
				System.out.println((char) i);
			}
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		try {
			fis = new FileInputStream(new File("boot_a.txt"));
			
			// 변수에 담지 않으면 정상적인 결과가 안 나옴
			int i;
			while ((i = fis.read()) != -1) {
				System.out.println((char) i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 */

		System.out.println("끝");

	}

}
