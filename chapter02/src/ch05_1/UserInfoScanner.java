package ch05_1;

import java.util.Scanner;

public class UserInfoScanner {

		Scanner scanner;
		
		public UserInfoScanner() {
			this.scanner = new Scanner(System.in);
		}
		
		public UserInfo inputUserData() {
			System.out.print("사용자 이름 : ");
			String name = scanner.nextLine();
			
			System.out.print("사용자 아이디 : ");
			String id = scanner.nextLine();
			
			return new UserInfo(name, id);
			
		}
}
