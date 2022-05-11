package ch01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) {
		
		DBClient client = DBClient.getInstance();
		ArrayList<MovieDto> resultList = new ArrayList<MovieDto>();
		
		Connection connection = client.getConnection();
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet;
		
		// 배우 이름 검색 -> 출연 영화 + 배우 정보 출력
		String selectQuery = "SELECT a.*, r.title\r\n"
				+ "FROM actor a\r\n"
				+ "INNER JOIN role r\r\n"
				+ "ON a.actorName = r.actorName\r\n"
				+ "WHERE a.actorName = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, "정유미");
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				MovieDto dto = new MovieDto();
				dto.setTitle(resultSet.getString("title"));
				dto.setActorName(resultSet.getString("actorName"));
				dto.setBirthYear(resultSet.getInt("birthYear"));
				dto.setHeight(resultSet.getInt("height"));
				dto.setWeight(resultSet.getInt("weight"));
				dto.setPartner(resultSet.getString("partner"));
				resultList.add(dto);
			}
			
			for (int i = 0; i < resultList.size(); i++) {
				System.out.println(resultList.get(i));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
