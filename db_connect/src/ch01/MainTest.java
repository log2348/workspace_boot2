package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainTest {

	// DB 서버와 연결하기 위한 준비물
	private Connection conn; // DB Connection 연결 객체
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "asd123";
	private static final String URL = "jdbc:mysql://localhost:3306/shopdb?serverTimezone=Asia/Seoul&characterEncoding=UTF-8"; // jdbc:mysql: 프로토콜, shopdb 접근할 데이터베이스
	
	private Statement stmt; // String --> 쿼리구문으로 변경해줌
	private ResultSet rs; // 결과값 담음 (Set계열)
	
	// 생성자
	public MainTest() {
		
		try {
			/*
			 * reflect 기법 : 컴파일 시점에 문자열 --> 런타임 시점에 실제 클래스가 존재하는지 확인
			 * 메모리(Heap)에 올라간다
			 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD); // db서버에 내 계정과 쿼리를 연결
			stmt = conn.createStatement();
			
			String sql1 = "select * from membertbl"; // 쿼리가 실행되면
			rs = stmt.executeQuery(sql1); // 결과가 여기 담긴다		
			
			while(rs.next()) {
				String memberId = rs.getString("memberId");
				String memberAddress = rs.getString("memberAddress");
				System.out.println("memberId : " + memberId + ", " + "memberAddress = " + memberAddress);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// 코드의 시작점
	public static void main(String[] args) {
		new MainTest();

	} // end of main

}
