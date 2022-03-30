package ch08;

import java.util.ArrayList;

public class MainTestReview {

	public static void main(String[] args) {
		Book book1 = new Book(1, "흐르는강물처럼", "파울로코엘료");
		Book book2 = new Book(2, "플러터UI실전", "김근호");
		Book book3 = new Book(3, "무궁화꽃이피었습니다", "감진명");
		Book book4 = new Book(4, "리딩으로리드하라", "이지성");
		Book book5 = new Book(5, "사피엔스", "유발하라리");
		
		ArrayList<Book> cart = new ArrayList<>();
		
		// 데이터 추가 (C)
		cart.add(book1);
		cart.add(book4);
		cart.add(book5);
		// 기존 배열 --> 길이를 미리 정해둠
		// ArrayList --> 크기가 유동적, 더 유용하게 사용 가능
		
		for(Book book: cart) {
			System.out.println(book);
		}
		
		// 데이터 삭제 (D)
		cart.remove(0);
		
		for (Book book : cart) {
			System.out.println(book);
		}
		
		// 데이터 조회 (R)
		System.out.println("데이터 조회");
		for(int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i));
			// 재정의
		}
		
		// 데이터 수정 (U)
		cart.set(0, book3);
		
		System.out.println("데이터 수정");
		for (Book book : cart) {
			System.out.println(book);
		}
		

	}

}
