package ch05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ch02.DBClient;

public class ReservationDao implements IReservationDao {
	
	private DBClient dbClient;
	private Connection connection;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public ReservationDao() {
		dbClient = DBClient.getInstance();
		connection = dbClient.getConnection();
	}

	// 중첩 쿼리문 사용
	@Override
	public ArrayList<ReservationDto> checkReservationDate(String reserveDate) {
		
		ArrayList<ReservationDto> reserveInfo = new ArrayList<ReservationDto>();
		
		try {
			String sql = "SELECT id, reserveDate, roomNum, name FROM reservation WHERE name IN (SELECT name FROM customer WHERE reserveDate = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, reserveDate);
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				ReservationDto reservationDto = new ReservationDto();
				
				reservationDto.setId(resultSet.getInt("id"));
				reservationDto.setName(resultSet.getString("name"));
				reservationDto.setReserveDate(resultSet.getString("reserveDate"));		
				reserveInfo.add(reservationDto);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reserveInfo;
		
	}

	// 인라인 뷰 사용
	@Override
	public ArrayList<ReservationDto> checkReservationRoom(int roomNum) {
		
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		ArrayList<ReservationDto> reserveInfo = new ArrayList<ReservationDto>();
		
		try {
			String sql = "SELECT name, reservedRoom FROM (SELECT name, reserveDate, roomNum FROM reservation WHERE roomNum = ?) as reservationInfo";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, roomNum);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reserveInfo;
		
	}

	// 스칼라 서브 쿼리 사용
	@Override
	public void scalarSubquery() {
		
		String sql = "SELECT *, (SELECT * FROM reservation AS B WHERE A.name = B.name) AS FROM customer AS A WHERE to_date = '9999-01-01";
		
		
	}

}
