package ch11_1;

import java.util.Scanner;

public class MainTest {

	public static void main(String[] args) {
		
		BookClient bookClient = new BookClient();
		// 다형성
		BookService bookArrayList = new BookArrayList();

		Scanner scanner = new Scanner(System.in);
		String selectMenu = null;
		
		do {
			System.out.println("----------------");
			System.out.println("1. 책 생성");
			System.out.println("2. 책 조회");
			System.out.println("3. 책 삭제");
			System.out.println("4. 책 전체 조회");
			System.out.println("5. 책 정보 수정");
			System.out.println("0. 프로그램 종료");
			System.out.println("----------------");
			
			selectMenu = scanner.nextLine();
			
			if(selectMenu.equals("0")) {
				System.out.println("프로그램을 종료합니다.");
				scanner.close();
				
			} else if(selectMenu.equals("1")) {
				
				System.out.println("책 제목을 입력하세요.");
				String title = bookArrayList.removeBlankString(scanner.nextLine());
				
				System.out.println("작가의 이름을 입력하세요.");
				String author = bookArrayList.removeBlankString(scanner.nextLine());
				
				Book book = bookClient.createBook(title, author);
				bookArrayList.addBook(book);
				
			} else if(selectMenu.equals("2")) {
				System.out.println("책 제목을 입력하세요.");
				String title = bookArrayList.removeBlankString(scanner.nextLine());
				bookArrayList.selectedByTitleBook(title);
				
			} else if(selectMenu.equals("3")) {
				System.out.println("삭제하려는 책 제목을 입력하세요.");
				String title = bookArrayList.removeBlankString(scanner.nextLine());
				
				bookArrayList.deleteBook(title);
				
			} else if(selectMenu.equals("4")) {
				System.out.println("저장 되어있는 책 목록 조회");
				bookArrayList.showAllBook();
				
			} else if(selectMenu.equals("5")) {
				System.out.println("수정하려는 책 제목을 입력하세요.");
				String savedBookTitle = bookArrayList.removeBlankString(scanner.nextLine());
				
				Book savedBook;
				//savedBook = bookClient.createBook(savedBookTitle, savedBook.getAuthor());
				// 여기서 제목 쳤을 때 존재하지 않으면 존재하지 않는다고 출력해야하는데
				// 새로운 책 제목을 입력하라고 바로 넘어감
				// 수정필요
				
				// 순서 이상
				bookArrayList.updateBook(savedBookTitle, null);
				
				System.out.println("새로운 책 제목을 입력하세요.");
				String title = bookArrayList.removeBlankString(scanner.nextLine());
				
				System.out.println("새로운 작가 이름을 입력하세요.");
				String author = bookArrayList.removeBlankString(scanner.nextLine());
				
				Book newBook = bookClient.createBook(title, author);
				bookArrayList.updateBook(savedBookTitle, newBook);

				
			} else {
				System.out.println("잘못된 입력입니다.");
			}
			
		} while (!(selectMenu.equals("0"))); // 반복조건

	}

}
