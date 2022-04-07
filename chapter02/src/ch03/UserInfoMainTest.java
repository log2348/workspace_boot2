package ch03;

import java.util.Scanner;

public class UserInfoMainTest {

	public static void main(String[] args) {
		
		//코드를 실행하는 쪽
		Scanner scanner = new Scanner(System.in);
		
		int memberId = scanner.nextInt();
		
		UserInfo user1 = new UserInfo();
		
		System.out.print("아이디: ");
		user1.username = scanner.next();
		
		System.out.print("비밀번호: ");
		user1.password = scanner.next();
		
		System.out.print("이름: ");
		user1.name = scanner.next();
		
		System.out.print("생년월일: ");
		user1.birthYear = scanner.next();
		
		System.out.print("폰 번호: ");
		user1.phoneNumber = scanner.next();
		
		user1.showUserInfo();
		
		UserInfo info = new UserInfo();
		info.userSignUp();
		
	}

}
