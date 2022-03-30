package ch10;

public class MainTest {

	public static void main(String[] args) {
		
		BookClient bookClient = new BookClient();
		Book book = bookClient.createBookObj();
		
		bookClient.deleteBook("홍길동전");
		
		BookDaoMySql bookDaoMySql = new BookDaoMySql();
		bookDaoMySql.addBook(book);
		
		// 실행의 흐름 !!!!!
		// 1. 조회 2. 생성 3. 삭제  4. 수정

	}	

}