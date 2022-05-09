package ch05;

import java.util.ArrayList;

public interface IReservationDao {
	// 서브쿼리
	// 기능구현 (서비스 로직)
	
	ArrayList<ReservationDto> checkReservationDate(String reserveDate);
	ArrayList<ReservationDto> checkReservationRoom(int roomNum);
	void scalarSubquery();
}
