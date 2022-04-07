package ch04;

public class OrderInfo {
	
	String orderNumber;
	String phoneNumber;
	String orderAdderess;
	String orderDate;
	String orderTime;
	int menuNumber;
	int totalPrice;
	
	public OrderInfo() {
		
	}
	
	public OrderInfo(String orderNumber, String phoneNumber, String orderAdderess, String orderDate, String orderTime, int totalPrice, int menuNumber) {
		this.orderNumber = orderNumber;
		this.phoneNumber = phoneNumber;
		this.orderAdderess = orderAdderess;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.totalPrice = totalPrice;
		this.menuNumber = menuNumber;
		
	}
	
	public void showOrderInfo() {
		System.out.println("배달주문이 들어왔습니다.");
		System.out.println("접수 번호: " + orderNumber);
		System.out.println("주문자 전화 번호: " + phoneNumber);
		System.out.println("주문 주소: " + orderAdderess);
		System.out.println("주문 날짜: " + orderDate);
		System.out.println("주문 가격: " + totalPrice);
		System.out.println("메뉴 번호: " + menuNumber);
		
	}

}
