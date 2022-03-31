package ch11;

public class BookArray implements BookService {

	Book[] books = new Book[10];

	@Override
	public void addBook(Book book) {
		for (int i = 0; i < books.length; i++) {
			if (books[i] == null) {
				System.out.println("책을 추가합니다.");
				books[i] = book;
				return;
			}
		}
		System.out.println("저장 공간이 부족합니다.");
	}

	@Override
	public void updateBook(String title, Book book) {
		System.out.println("책의 정보를 수정합니다.");

		for (int i = 0; i < books.length; i++) {
			if(books[i] == null) {
				continue;
			} else if (books[i].getTitle().equals(title)) {
				books[i] = book;
				return;
			}
			System.out.println(title + " 제목의 책이 존재하지 않습니다.");
		}

	}

	@Override
	public void deleteBook(String title) {
		// 배열의 요소 삭제 - 앞으로 당겨와서 채워주기

		for (int i = 0; i < books.length - 1; i++) {
			if(books[i] == null) {
				continue;
			}else if (books[i].getTitle().equals(title)) {
				books[i] = books[i + 1];
				System.out.println(title + "이(가) 삭제되었습니다.");
				return;
			}
		}
		System.out.println(title + " 제목의 책이 존재하지 않습니다.");
	}

	@Override
	public void selectedByTitleBook(String title) {
		System.out.println("책의 정보를 조회합니다.");
		for (int i = 0; i < books.length; i++) {
			if(books[i] == null) {
				System.out.println("저장된 책이 없습니다.");
				continue;
			} else if (books[i].getTitle().equals(title)) {
				System.out.println(books[i]);
				return;
			}
		}
		System.out.println(title + " 제목의 책이 존재하지 않습니다.");
	}

	@Override
	public void showAllBook() {
		System.out.println("모든 책의 정보를 출력합니다.");
		for (int i = 0; i < books.length; i++) {
			if (books[i] == null) {
				continue;
			} else {
				System.out.println(books[i]);
				
			}
		}
		System.out.println("저장된 책이 없습니다.");

	}

}
