package ch02;

public interface IManager {
	
	// 영화 매출 수정
	void updateSales(int sales, String title);
	
	// 영화 평점 수정
	void updateStarScore(float starScore, String title);
	
	// 새로운 영화 정보 삽입
	void insertMovieInfo(String title, String date, float StarScore, String genre, String imageFileName);
	
	// 영화 삭제
	void deleteMovieInfo(String title);
}
