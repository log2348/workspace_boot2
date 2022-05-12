package ch01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao implements IUserDao {
	
	private DBClient dbClient;
	private Connection connection;
	
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	private ArrayList<Dto> resultList;
	
	public UserDao() {
		dbClient = DBClient.getInstance();
		connection = dbClient.getConnection();
	}

	// 배우 이름 검색 -> 출연 영화명 + 배우 정보 출력
	@Override
	public ArrayList<Dto> selectActorInfo(String actorName) {
		resultList = new ArrayList<Dto>();
		
		String selectQuery = "SELECT a.*, r.title\r\n"
				+ "FROM actor a\r\n"
				+ "INNER JOIN role r\r\n"
				+ "ON a.actorName = r.actorName\r\n"
				+ "WHERE a.actorName = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, actorName);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				ActorDto actorDto = new ActorDto();
				actorDto.setActorName(resultSet.getString("actorName"));
				actorDto.setBirthYear(resultSet.getInt("birthYear"));
				actorDto.setHeight(resultSet.getInt("height"));
				actorDto.setWeight(resultSet.getInt("weight"));
				actorDto.setPartner(resultSet.getString("partner"));
				resultList.add(actorDto);

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
		
		return resultList;
		
	}

	// 영화목록 최신순으로 출력
	@Override
	public ArrayList<Dto> orderByreleaseDate() {
		
		resultList = new ArrayList<Dto>();
		String selectQuery = "SELECT *\r\n"
							+ "FROM movie\r\n"
							+ "ORDER BY releaseDate DESC ";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, "마동석");
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				MovieDto dto = new MovieDto();
				dto.setTitle(resultSet.getString("title"));
				dto.setGenre(resultSet.getString("genre"));
				dto.setStarScore(resultSet.getFloat("starScore"));
				dto.setReleaseDate(resultSet.getString("releaseDate"));
				dto.setImageFileName(resultSet.getString("image"));
				
				resultList.add(dto);
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
		
		return resultList;
		
		
		
	}

	// 영화명 검색
	@Override
	public ArrayList<Dto> selectMovieInfo() {
		
		resultList = new ArrayList<Dto>();
		String selectQuery = "SELECT *\r\n"
						+ "FROM movie m\r\n"
						+ "INNER JOIN role r\r\n"
						+ "ON m.title = r.title\r\n"
						+ "WHERE m.title = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, "어벤져스");
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				MovieDto dto = new MovieDto();
				dto.setTitle(resultSet.getString("title"));
				dto.setGenre(resultSet.getString("genre"));
				dto.setStarScore(resultSet.getFloat("starScore"));
				dto.setReleaseDate(resultSet.getString("releaseDate"));
				dto.setImageFileName(resultSet.getString("image"));
				resultList.add(dto);
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
		
		return resultList;
		
	}

}
