package ch11_1;


public class BookArray implements BookService {

	Book[] books = new Book[10];
	
	@Override
	public void addBook(Book book) {
		System.out.println("----- 책 추가 -----");
		books[book.getId()-1] = book; 
	}

	@Override
	public void updateBook(Book book) {
		System.out.println("----- 책 정보 수정 -----");
		
		for(int i = 0; i < books.length; i++) {
			System.out.println("수정할 책 제목을 입력하세요.");
			//String title = scanner.nextLine();
			//if(books[i].getTitle().equals(title)) {
				// 수정할 책이 있는 경우
				
			}
			
		}

	@Override
	public void deleteBook(String title) {
		// 배열 요소 삭제 - 앞으로 당겨와서 채워주기
		for(int i = 0; i < books.length - 1; i++) {
			books[i] = books[i+1];
		}
		
	}

	@Override
	public void selectedByTitleBook(String title) {
		System.out.println("----- 책 정보 조회 -----");
		for(int i = 0; i < books.length; i++) {
			if(books[i].getTitle().equals(title)) {
				System.out.println(books[i]);
				return;
			}
		}
		
	}

	@Override
	public void showAllBook() {
		System.out.println(">>>>>> 현재 저장된 데이터 확인 <<<<<<");
		for (int i = 0; i < books.length; i++) {
			System.out.println(books[i]);
		}
		
	}

	@Override
	public String removeBlankString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBook(String title, Book book) {
		// TODO Auto-generated method stub
		
	}
	
}
