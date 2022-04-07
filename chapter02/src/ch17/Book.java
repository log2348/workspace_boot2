package ch17;

public class Book {
	
	int id;
	String title;
	// 포함관계
	Author author;
	
	public Book() {
		author = new Author("박지현", 1);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
	}
	
	
	
//	@Override
//	public String toString() {
//		System.out.println("작가에 대한 정보도 출력");
//		System.out.println(author.name);
//		System.out.println(author.age);
//		System.out.println("================");
//		
//		String str = "";
//		str += ">>>>>>>>>>\n";
//		str += "Book [id=\" + id + \", title=\" + title + \", author=\" + author + \"]\n";
//		str += ">>>>>>>>>>\n";
//		return str;
//		
//	}
	
	

}
