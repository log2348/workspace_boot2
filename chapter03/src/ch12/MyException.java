package ch12;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyException {
	
	String fileName;
	
	public MyException(String fileName) {
		this.fileName = fileName;
		
	}
	
	// throws - 던지기 (사용하는 사람이 직접 예외처리를 하라고 지시함)
	public String readFile() throws IOException {
		
		FileInputStream fis = new FileInputStream(this.fileName);
		Properties properties = new Properties();
		properties.load(fis);
		String dbType = properties.getProperty("DBTYPE");
		
		return dbType;
	}
	
	public static void main(String[] args) {
		
		String dbType = null;
		MyException my = new MyException("b.txt");
		
		try {
			dbType = my.readFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("예외가 발생해서 로그 저장");
		}
		
		System.out.println("결과값 : " + dbType);
		
	} // end of main
	
}
