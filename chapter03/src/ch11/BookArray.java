package ch11;

public class BookArray implements BookService {

	Book[] books = new Book[10];

	@Override
	public void addBook(Book book) {
		System.out.println("----- 책 추가 -----");
		for(int i = 0; i < books.length; i++) {
			if(books[i] == null) {
				books[i] = book;
				return;
			}
		}
		System.out.println("저장 공간이 부족합니다.");
	}

	@Override
	public void updateBook(String title, Book book) {
		System.out.println("----- 책 정보 수정 -----");

		for (int i = 0; i < books.length; i++) {
			

		}

	}

	@Override
	public void deleteBook(String title) {
		// 배열 요소 삭제 - 앞으로 당겨와서 채워주기
		for (int i = 0; i < books.length - 1; i++) {
			books[i] = books[i + 1];
		}

	}

	@Override
	public void selectedByTitleBook(String title) {
		System.out.println("----- 책 정보 조회 -----");
		for (int i = 0; i < books.length; i++) {
			if (books[i].getTitle().equals(title)) {
				System.out.println(books[i]);
				return;
			}
		}
		System.out.println(title + " 제목의 책이 존재하지 않습니다.");
	}

	@Override
	public void showAllBook() {
		System.out.println(">>>>>> 현재 저장된 데이터 확인 <<<<<<");
		for (int i = 0; i < books.length; i++) {
			System.out.println(books[i]);
		}

	}

}
