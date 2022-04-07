package ch09;

import java.util.Scanner;

public class MakeReport {
	private Scanner sc;

	// StringBuilder 로 수정해 주세요
	private StringBuilder str;
	private String line = "=========================\n";
	private String title = "이름\t        주소 \t\t   전화번호\n";

	// hint --> 객체가 메모리 올라 갈때 생성자 !!
	public MakeReport() {
		str = new StringBuilder();
		// 필요값이 있으면 초기화 한다
		// 이스케이프 문자(제어문자)
		// 인쇄할 수 없거나 키보드로 표현할 수 없는 특별한 문자를 가리킨다.
		// 역슬래시와() 한개의 문자와 결합하여 사용한다.
	}

	private void makeHeader() {
		str.append(line);
		str.append(title);
		str.append(line);

	}

	private void generateBody() {
		str.append("홍길동\t");
		str.append("서울 \t");
		str.append("010-1234-1234\n");

	}

	private void makeFooter() {
		str.append(line);
	}

	public void getReport() {
		makeHeader();
		generateBody();
		makeFooter();
		System.out.println(str.toString());
	}
}
