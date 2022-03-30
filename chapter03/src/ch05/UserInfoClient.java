package ch05;

import java.util.Scanner;

public class UserInfoClient {
	
	public static final String MYSQL = "mysql"; // static 영역 주소
	public static final String ORACLE = "oracle";
	
	public static void main(String[] args) {
		
		// UserInfo 스캐너로 받아서 흐름 만들어 주세요.
		
		Scanner scanner = new Scanner(System.in);
		// 사용자한테 userInfo라는 정보를 받는다.
		UserInfo info = new UserInfo();
		
		System.out.print("아이디 : ");
		info.setUserId(scanner.nextLine());
		
		System.out.print("비밀번호 : ");
		info.setPassword(scanner.nextLine());
		
		System.out.print("이름 : ");
		info.setUserName(scanner.nextLine());
		
		//info.setUserId("log2348");
		//info.setPassword("#1234");
		//info.setUserName("박지현");
		
		// 인터페이스 활용
		
		// 1. A회사
		// UserInfoMysqlDao mySqlDao = new UserInfoMysqlDao();
		// mySqlDao.insertUserInfo(info);
		
		// 2. B회사
		// UserInfoOracleDao oracleDao = new UserInfoOracleDao();
		// oracleDao.insertUserInfo(info);
		
		// String str = new String("mysql"); // 힙영역 주소
		
		// equals는 문자열의 값을 비교합니다.
		// == 객체의 주소값을 비교합니다.
		// if(str == MYSQL) { 
		
		// 문자열을 비교할때는 무조건 equals라는 것을 사용한다.
		
		/*
		if("mysql".equals(MYSQL)) {
			System.out.println("문자열이 같습니다.");
		} else {
			System.out.println("문자열이 다릅니다.");
		}
		*/
		
		// 1. mysql 문자열이면 --> UserInfoMysqlDao 동작
		// 2. oracle 문자열이면 --> UserInfoOracleDao 동작
		// 단, 다형성을 사용해서 처리 해주세요.
		
		UserInfoDao userInfoDao = null;
		String str = "oracle";
		
		if(MYSQL.equals(str)) {
			userInfoDao = new UserInfoMysqlDao();
			
			
		}else if(ORACLE.equals(str)) {
			userInfoDao = new UserInfoOracleDao();
			
		} else {
			System.out.println("잘못된 입력입니다.");
		}
		
		// 방어적 코드
		if(userInfoDao != null) {
			userInfoDao.updateUserInfo(info);
			
		}
		
	}

}
