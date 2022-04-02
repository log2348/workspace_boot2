package ch11;

import java.util.Scanner;

public class MainArrayTest {

	public static void main(String[] args) {

		BookClient bookClient = new BookClient();

		BookService books = new BookArray();

		Scanner scanner = new Scanner(System.in);

		String selectMenu = null;

		do {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("1. 책 생성 2. 책 조회 3. 책 삭제 4. 책 전체 조회 5. 책 수정 0. 프로그램 종료");
			System.out.println("----------------------------------------------------------------------------");
			selectMenu = scanner.nextLine();

			if (selectMenu.equals("0")) {
				System.out.println("프로그램이 종료되었습니다.");
				scanner.close();

			} else if (selectMenu.equals("1")) {
				System.out.println("책 제목을 입력하세요.");
				String title = scanner.nextLine();

				System.out.println("작가의 이름을 입력하세요.");
				String author = scanner.nextLine();

				Book book = bookClient.createBook(title, author);
				books.addBook(book);

			} else if (selectMenu.equals("2")) {
				System.out.println("책 제목을 입력하세요.");
				String title = scanner.nextLine();
				books.selectedByTitleBook(title);

			} else if (selectMenu.equals("3")) {
				System.out.println("삭제하려는 책 제목을 입력하세요.");
				String title = scanner.nextLine();
				books.deleteBook(title);

			} else if (selectMenu.equals("4")) {
				books.showAllBook();

			} else if (selectMenu.equals("5")) {
				System.out.println("수정하려는 책 제목을 입력하세요.");
				String savedTitle = scanner.nextLine();

				System.out.println("새로운 책 제목을 입력하세요.");
				String title = scanner.nextLine();

				System.out.println("새로운 작가 이름을 입력하세요.");
				String author = scanner.nextLine();

				Book book = bookClient.createBook(title, author);
				books.updateBook(savedTitle, book);

			} else {
				System.out.println("잘못된 입력입니다.");
			}

		} while (!(selectMenu.equals("0")));

	}

}
