package ch02;

import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) {

		MemberInfoDao memberInfoDao = new MemberInfoDao();
		
		//ArrayList<MemberDto> data =  memberInfoDao.select();
		//System.out.println(data);
		
		// 위 구문 주석 안 하면 자원 닫혀서 오류 발생
		// object -->
		MemberDto dto = new MemberDto("abc", "강감찬", "서울시 동작구");
		//memberInfoDao.insert(dto);
		//memberInfoDao.update(dto);
		
		int returnRow = memberInfoDao.delete("abc");
		System.out.println("삭제된 행 : " + returnRow);
	}

}
