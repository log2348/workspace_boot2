package ch05;

import java.util.Scanner;

public class UserInfoScanner {
	
	// 상태 --- 멤버변수
	Scanner scanner;
	
	// 생성자 --> 기본생성자(메모리에 올라갈 때 처음 실행 됩니다)
	public UserInfoScanner() {
		scanner = new Scanner(System.in);
	}
	
	// 행위
	// 메서드를 만들어 주세요
	public UserInfo inputData() {
		
		System.out.println("==== 사용자 정보 입력창 ====");
		System.out.print("이름 : ");
		String name = scanner.nextLine();
		
		System.out.print("이메일 : ");
		String email = scanner.nextLine();
		
		System.out.print("주소 : ");
		String address = scanner.nextLine();
		
		System.out.print("전화번호 : ");
		String phoneNumber = scanner.nextLine();
		
		scanner.close();
		
		// printData();
		
		UserInfo user = new UserInfo();
		user.name = name;
		user.email = email;
		user.address = address;
		user.phoneNumber = phoneNumber;
		
		return user;
		
	}
	
	/*
	public void printData() {
		
		System.out.println("====== 사용자 정보 ======");
		System.out.println("이름 : " + name);
		System.out.println("이메일 : " + email);
		System.out.println("주소 : " + address);
		System.out.println("전화번호 : " + phoneNumber);
	}
	*/
	
}
