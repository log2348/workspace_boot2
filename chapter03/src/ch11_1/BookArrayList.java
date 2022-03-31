package ch11_1;

import java.util.ArrayList;

public class BookArrayList implements BookService {
	
	// Book 객체를 ArrayList 자료 구조에 저장하기
	private ArrayList<Book> books = new ArrayList<>();
	
	@Override
	public void addBook(Book book) {
		System.out.println("저장합니다.");
		books.add(book);
		showAllBook();		
	}
	
	// 책의 타이틀로 책 존재여부 확인
	// 있다면 매개변수로 넘어오는 Book 객체를 수정한다.
	@Override
	public void updateBook(String title, Book book) {
		System.out.println("수정합니다.");
		// 책의 제목으로 해당 책의 인덱스 번호를 찾아야한다.
		int bookIndex = -1;
		for(int i = 0; i < books.size(); i++) {
			if(books.get(i).getTitle().equals(title)) {
				// 타이틀이 같다면 책이 존재
				bookIndex = i;
			}
		}
		
		if(bookIndex == -1) {
			System.out.println(title + " 제목의 책이 존재하지 않습니다.");
		} else {
			books.set(bookIndex, book);
		}
		showAllBook();
		
	}

	@Override
	public void deleteBook(String title) {
		System.out.println("삭제합니다.");
		boolean deleteOk = false;
		
		for(int i = 0; i < books.size(); i++) {
			if(books.get(i).getTitle().equals(title)) {
				books.remove(i);
				deleteOk = true;
			}
		}
		
		if(deleteOk) {
			System.out.println(title + " 책을 삭제하였습니다.");
		} else {
			System.out.println(title + " 책이 존재하지 않습니다.");
		}
		
		showAllBook();
		
	}
	
	
	// 책 제목에 해당하는 객체의 정보를 출력합니다.
	@Override
	public void selectedByTitleBook(String title) {
		System.out.println("책의 정보를 조회합니다.");
		for(int i = 0; i < books.size(); i++) {
			if(books.get(i).getTitle().equals(title)) {
				System.out.println(books.get(i));
				return; // 실행의 흐름 멈추도록
			}
		}
		System.out.println(title + " 제목으로 책을 찾을 수 없습니다.");
	}
	
	// 모든 데이터를 출력
	@Override
	public void showAllBook() {
		System.out.println(">>>>> 현재 저장된 데이터 확인 <<<<<");
		for(Book book : books) {
			System.out.println(book);
		}
	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		
	}
	
	public String removeBlankString(String str) {
		String result = str.replace(" ", "");
		return result;
	}

}
