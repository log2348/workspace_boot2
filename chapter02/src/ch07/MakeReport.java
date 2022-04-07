package ch07;

import java.util.Scanner;

public class MakeReport {

	private String str;
	private String line = "=============================\n";
	private String title;
	private String name;
	private String address;
	private String phoneNumber;

	private Scanner scanner;

	public MakeReport() {
		// 필요값이 있으면 초기화 한다
		// 이스케이프 문자(제어문자)
		// 인쇄할 수 없거나 키보드로 표현할 수 없는 특별한 문자를 가리킨다.
		// 역슬래시(\)와 한개의 문자와 결합하여 사용한다.
		scanner = new Scanner(System.in);
		title = " 이름\t\t\t주소\t\t\t전화번호\n";

		System.out.print("이름 : ");
		this.name = scanner.nextLine();

		System.out.print("주소 : ");
		this.address = scanner.nextLine();

		System.out.print("전화번호 : ");
		this.phoneNumber = scanner.nextLine();

	}

	private void makeHeader() {
		str = line;
		str += title;
		str += line;

	}

	private void generateBody() {

		str += "홍길동\t\t";
		str += "서울\t\t";
		str += "010-1234-1234\n";
//		str += line;

	}

	private void makeFooter() {
		str += line;
	}

	public String getReport() {
		
		makeHeader();
		generateBody();
		str += this.name + "\t\t";
		str += this.address + "\t\t";
		str += this.phoneNumber + "\n";
		makeFooter();

		return this.str;
	}

}
