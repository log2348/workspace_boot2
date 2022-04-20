package ch02;

// 호출자 : 인터페이스 멤버변수로 선언
public class MyArticle {
	
	String article;
	WriteArticle onWriteArticle;
	
	// 주소값 연결 : 생성자에서 연결하는 방법
	public MyArticle(String article, WriteArticle writeArticle) {
		this.article = article;
		this.onWriteArticle = writeArticle;
	}
	
	public void complete() {
		onWriteArticle.printArticle(article);
	}

}
