package thread_Ex;

class BookStore {

	private static int totalBookNumber = 100;
	private String bookTitle;

	public static int getTotalBookNumber() {
		return totalBookNumber;
	}

	public static void setTotalBookNumber(int totalBookNumber) {
		BookStore.totalBookNumber = totalBookNumber;
	}

	public synchronized void restockBook(int bookNumber) {
		totalBookNumber += bookNumber;
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("책이 " + bookNumber + "권 입고되었습니다.");
		System.out.println("현재 재고 : 총 " + getTotalBookNumber() + "권");

	}

	public synchronized void buyBook(int bookNumber) {
		totalBookNumber -= bookNumber;
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("책이 " + bookNumber + "권 판매되었습니다.");
		System.out.println("현재 재고 : 총 " + getTotalBookNumber() + "권");
	}

}

class Seller extends Thread {

	BookStore bookStore;

	public Seller(BookStore bookStore) {
		this.bookStore = bookStore;
	}

	@Override
	public void run() {
		bookStore.restockBook(200);
	}

}

class Consumer extends Thread {

	public Consumer() {
		
	}

	@Override
	public void run() {
		new BookStore().buyBook(5);
	}
}

public class SharedResource2 {

	public static void main(String[] args) {
		BookStore bookStore = new BookStore();
		
		Seller seller = new Seller(bookStore);
		Consumer consumer = new Consumer();
		
		seller.start();
		
		consumer.start();

	}

}
