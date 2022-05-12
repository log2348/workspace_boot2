package ch01;

import java.util.ArrayList;

public interface IUserDao {
	
	// 배우 이름 검색 -> 출연 영화명 + 배우 정보 출력
	ArrayList<Dto> selectActorInfo(String actorName);
	
	// 영화목록 최신순으로 출력
	ArrayList<Dto> orderByreleaseDate();
	
	// 영화명 검색
	ArrayList<Dto> selectMovieInfo();
	
}
