package ch04;

import java.util.Scanner;

public class UserInfoTest {

	public static void main(String[] args) {
		UserInfo userInfo1 = new UserInfo("101");
		UserInfo userInfo2 = new UserInfo("102", "홍길동");
		UserInfo userInfo3 = new UserInfo("102", "홍길동", "01012341234");
		
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		System.out.println("문자열 입력 받는 방법: " + name);

	}

}
