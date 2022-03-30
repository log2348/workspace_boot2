package ch09;

import java.util.Scanner;

public class Book {

	private static int bookId = 0;
	private String title = null;
	private String author = null;
	private Scanner scanner;
	
	public Book() {
		bookId++;		
	}	

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [책 제목=" + title + ", 저자=" + author + "]";
	}
	
	

}
