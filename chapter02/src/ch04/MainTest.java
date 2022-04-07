package ch04;

import java.util.Scanner;

public class MainTest {

	public static void main(String[] args) {
		Person person1 = new Person();
		Person person2 = new Person("Thomas", 37, 78, 180);
		
		OrderInfo order1 = new OrderInfo();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("접수 번호: ");
		order1.orderNumber = scanner.nextLine();
		
		System.out.print("주문자 전화번호: ");
		order1.phoneNumber = scanner.nextLine();
		
		System.out.print("주문 주소: ");
		order1.orderAdderess = scanner.nextLine();
		
		System.out.print("주문 날짜: ");
		order1.orderDate = scanner.nextLine();
		
		System.out.print("주문 가격: ");
		order1.totalPrice = scanner.nextInt();
		
		System.out.print("메뉴 번호: ");
		order1.menuNumber = scanner.nextInt();
		
		order1.showOrderInfo();

	}

}
