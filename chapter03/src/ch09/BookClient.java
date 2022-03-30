package ch09;

import java.util.Scanner;

public class BookClient {

	// 스캐너 사용
	Scanner scanner;
	Book book;
	BookDaoMySql bookDaoMySql;

	// 생성
	public void userInput() {
		scanner = new Scanner(System.in);
		book = new Book();
		
		System.out.println("책의 정보를 입력합니다.");
		System.out.print("책 제목 : ");
		book.setTitle(scanner.nextLine());

		System.out.print("저자 : ");
		book.setAuthor(scanner.nextLine());
		
		// bookDaoMySql.insertBookInfo(book);
		// nullPointerException 해결하기
	}

	// 수정
	public void updateInfo() {
		bookDaoMySql.updateBookInfo(book);

	}

	// 삭제
	public void deleteInfo() {
		bookDaoMySql.deleteBookInfo(book);
	}

	// 출력
	public void showInfo() {
		bookDaoMySql.showBookInfo(book);
	}

}
