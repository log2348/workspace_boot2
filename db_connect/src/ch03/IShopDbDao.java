package ch03;

import java.util.ArrayList;

public interface IShopDbDao {

	// userTbl. buyTbl 결과 *
	ArrayList<UserDto> innerJoin(String userName);
	
	// userTbl, buyTbl null 제거, 결과 *
	ArrayList<UserDto> leftJoin(String userName);
	
	// buyTbl, userTbl, 결과 *
	ArrayList<UserDto> rightJoin(String userName);
	
	// 구매 내역 들고오기
	ArrayList<UserDto> buyInfo(String userName);
	
}
