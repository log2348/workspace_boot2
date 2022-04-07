package ch05;

public class MainTest {

	public static void main(String[] args) {
		// 사용하기(실행의 흐름)
		UserInfoScanner userInfoScanner = new UserInfoScanner();
		UserInfo info = userInfoScanner.inputData();
		
		System.out.println("==== 사용자 정보 출력 ====");
		System.out.println("이름 : " + info.name);
		System.out.println("이메일 : " + info.email);
		System.out.println("주소 : " + info.address);
		System.out.println("전화번호 : " + info.phoneNumber);

	}

}