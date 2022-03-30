package ch09;

import java.util.ArrayList;

public class BookDaoMySql implements BookDao {
	// 인터페이스를 활용해서 기능 구현
	// ArrayList 사용

	ArrayList<Book> books = new ArrayList<>();;

	@Override
	public void insertBookInfo(Book book) {
		books.add(book);
		System.out.println("' " + book.getTitle() + " '이(가) 추가되었습니다.");
	}

	@Override
	public void updateBookInfo(Book book) {
		books.set(book.getBookId() - 1, book);
		System.out.println("책의 정보가 수정되었습니다.");

	}

	@Override
	public void deleteBookInfo(Book book) {
		books.remove(book.getBookId() - 1);
		System.out.println("' " + book.getTitle() + " '이(가) 삭제되었습니다.");
		
		if(books.isEmpty()) {
			System.out.println("삭제할 책이 없습니다.");
		}

	}

	@Override
	public void showBookInfo(Book book) {
		
		for (int i = 0;i < books.size(); i++) {
			System.out.println(book);
		}

	}

}
