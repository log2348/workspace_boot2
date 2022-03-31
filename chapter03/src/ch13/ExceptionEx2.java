package ch13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExceptionEx2 {

	public static void main(String[] args) {
		
		FileInputStream fis;
		
		try {
			// 예외가 발생할 수 있는 코드
			fis = new FileInputStream("a.txt");
		} catch(FileNotFoundException e) {
			// 예외를 어떻게 처리해야 하는지 작성하는 부분
			e.printStackTrace();
		} finally {
			System.out.println("반드시 실행되는 영역");
		}
		
		System.out.println("코드가 실행이 되나요?");

	}

}
