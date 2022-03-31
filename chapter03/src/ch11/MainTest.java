package ch11;

import java.util.Scanner;

public class MainTest {

	public static void main(String[] args) {
		
		BookClient bookClient = new BookClient();
		// 다형성
		BookService bookArrayList = new BookArrayList();
		
		Scanner scanner = new Scanner(System.in);

		String selectedMenu = "";
		
		// 샘플데이터 생성
		Book book1 = new Book(1, "홍길동전1", "홍가1");
		Book book2 = new Book(2, "홍길동전2", "홍가2");
		Book book3 = new Book(3, "홍길동전3", "홍가3");
		Book book4 = new Book(4, "홍길동전4", "홍가4");
		Book book5 = new Book(5, "홍길동전5", "홍가5");
		bookArrayList.addBook(book1);
		bookArrayList.addBook(book2);
		bookArrayList.addBook(book3);
		bookArrayList.addBook(book4);
		bookArrayList.addBook(book5);

		do {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("1. 책 생성 2. 책 조회 3. 책 삭제 4. 책 전체 조회 5. 책 수정 0. 프로그램 종료");
			System.out.println("----------------------------------------------------------------------------");
			selectedMenu = scanner.nextLine();
			
			if(selectedMenu.equals("0")) {
				System.out.println("프로그램을 종료 합니다.");
				scanner.close();
				
			} else if(selectedMenu.equals("1")) {
				bookClient.printTitle();
				
				System.out.println("책 제목을 입력하세요.");
				String title = scanner.nextLine();
				
				System.out.println("작가의 이름을 입력하세요.");
				String author = scanner.nextLine();
				
				Book book = bookClient.createBook(title, author);
				bookArrayList.addBook(book);
				
			} else if(selectedMenu.equals("2")) {
				System.out.println("책 제목을 입력하세요.");
				String title = scanner.nextLine();
				bookArrayList.selectedByTitleBook(title);
				
			} else if(selectedMenu.equals("3")) {
				System.out.println("삭제하려는 책 제목을 입력하세요.");
				String title = scanner.nextLine();
				bookArrayList.deleteBook(title);
				
			} else if(selectedMenu.equals("4")) {
				System.out.println("저장 되어있는 책 목록 조회");
				bookArrayList.showAllBook();
				
			} else if(selectedMenu.equals("5")) {
				System.out.println("수정하려는 책 제목을 입력하세요.");
				String savedTitle = scanner.nextLine();
				
				System.out.println("새로운 책 제목을 입력하세요.");
				String title = scanner.nextLine();
				
				System.out.println("새로운 작가 이름을 입력하세요.");
				String author = scanner.nextLine();
				
				Book book = bookClient.createBook(title, author);
				bookArrayList.updateBook(savedTitle, book);
				
			} else {
				System.out.println("잘못된 입력입니다.");
			}
			
		} while (!(selectedMenu.equals("0")));
		

	} // end of main
	
	public static String removeBlankString(String str) {
		
		String result1 = str.trim(); // 앞뒤 공백 제거
		String result2 = result1.replace(" ", "");
		return result2;
		
	}

} // end of class
