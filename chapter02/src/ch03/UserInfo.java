package ch03;

import java.util.Scanner;

public class UserInfo {
	
	// 클래스 설계도면
	
	// 변수
	// 메서드
	// 함수
	Scanner scanner = new Scanner(System.in);
	
	String username;
	String password;
	String name;
	String birthYear;
	String gender;
	String phoneNumber;
	
	public void showUserInfo() {
		System.out.println("========회원 정보========");
		System.out.println("아이디: " + username);
		System.out.println("비밀번호: " + password);
		System.out.println("이름: " + name);
		System.out.println("생년월일: " + birthYear);
		System.out.println("폰 번호: " + phoneNumber);
	}
	
	public void userSignUp() {
		System.out.println("회원가입하기");
	}
	
	public String getPwd() {
		return "1234";
	}
	
}
