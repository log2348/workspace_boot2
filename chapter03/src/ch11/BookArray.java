package ch11;

public class BookArray implements BookService {

	Book[] books = new Book[10];

	@Override
	public void addBook(Book book) {
		for (int i = 0; i < books.length; i++) {
			if (books[i] == null) {
				books[i] = book;
				System.out.println("추가되었습니다.");
				return;
			}
		}
		System.out.println("저장 공간이 부족합니다.");
	}

	@Override
	public void updateBook(String title, Book book) {

		boolean updateCheck = false;

		for (int i = 0; i < books.length; i++) {
			if (books[i] != null) {
				if (books[i].getTitle().equals(title)) {
					books[i] = book;
					updateCheck = true;
				}
			}
		}
		if (updateCheck) {
			System.out.println("수정이 완료되었습니다.");
		} else {
			System.out.println("입력하신 제목의 책이 존재하지 않습니다.");
		}

	}

	@Override
	public void deleteBook(String title) {
		// 배열의 요소 삭제 - 앞으로 당겨와서 채워주기
		boolean deleteCheck = false;

		for (int i = 0; i < books.length; i++) {
			if (books[i] != null) {
				if (books[i].getTitle().equals(title)) {
					for (int j = i; j < books.length - j; j++) {
						books[j] = books[j + 1];
						deleteCheck = true;
					}
				}
			}
		}

		if (deleteCheck) {
			System.out.println("'" + title + "'이(가) 삭제되었습니다.");
		} else {
			System.out.println("입력하신 제목의 책이 존재하지 않습니다.");

		}
	}

	// 책 한권의 정보 출력
	@Override
	public void selectedByTitleBook(String title) {
		System.out.println("'" + title + "'의 정보를 조회합니다.");

		for (int i = 0; i < books.length; i++) {
			if (books[i] != null) {
				if (books[i].getTitle().equals(title)) {
					System.out.println(books[i]);
					return;
				}
			}
		}
		System.out.println("입력하신 제목의 책이 존재하지 않습니다.");
	}

	// 저장된 책 모두 출력
	@Override
	public void showAllBook() {
		System.out.println("모든 책의 정보를 출력합니다.");

		boolean flag = false;

		for (int i = 0; i < books.length; i++) {
			if (books[i] != null) {
				System.out.println(books[i]);
				flag = true;
			}
		}

		if (flag == false) {
			System.out.println("저장된 데이터가 없습니다.");
		}

	}

}
