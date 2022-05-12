package teamProject.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieAndActorDao implements DaoInterface<Dto> {
	
	private DBClient dbClient;
	private Connection connection;
	
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	private List<Dto> resultList = null;
	
	public MovieAndActorDao() {
		dbClient = DBClient.getInstance();
		connection = dbClient.getConnection();
	}

	@Override
	public List<Dto> loadListMoive() {
		resultList = new ArrayList<Dto>();
		String selectQuery = "SELECT * FROM movie ";

		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				MovieDto movieDto = new MovieDto();
				movieDto.setTitle(resultSet.getString("title"));
				resultList.add(movieDto);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}

	@Override
	public List<Dto> loadListActor() {
		resultList = new ArrayList<Dto>();
		String selectQuery = "SELECT * FROM actor ";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				ActorDto actorDto = new ActorDto();
				actorDto.setActorName(resultSet.getString("actorName"));
				
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

	@Override
	public List<Dto> loadRecentMovie() {
		resultList = new ArrayList<Dto>();
		String selectQuery = "SELECT *\r\n"
							+ "FROM movie\r\n"
							+ "ORDER BY releaseDate DESC ";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				MovieDto dto = new MovieDto();
				dto.setTitle(resultSet.getString("title"));
				
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

	// 영화 제목 검색 -> 영화 정보(영화 성적 포함) / 배우 이름 / 배역 이름
	@Override
	public Dto SearchMovieInfo(String movieName) {
		ArrayList<String> actorList = new ArrayList<String>();
		ArrayList<String> scoreList = new ArrayList<String>();
		
		resultList = new ArrayList<Dto>();
		MovieDto movieDto = new MovieDto();
		ActorDto actorDto = new ActorDto();
		ScoreDto scoreDto = new ScoreDto();
		
		String selectQuery = "SELECT *, r.roleName\r\n"
				+ "FROM movie m\r\n"
				+ "INNER JOIN role r\r\n"
				+ "ON m.title = r.title\r\n"
				+ "INNER JOIN score s\r\n"
				+ "ON m.title = s.title\r\n"
				+ "WHERE m.title = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, movieName);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				movieDto.setTitle(resultSet.getString("title"));
				movieDto.setGenre(resultSet.getString("genre"));
				movieDto.setStarScore(resultSet.getFloat("starScore"));
				movieDto.setReleaseDate(resultSet.getString("releaseDate"));
				movieDto.setImageFileName(resultSet.getString("image"));
				
				actorDto.setActorName(resultSet.getString("actorName"));
				actorDto.getRoleDto().setRoleName(resultSet.getString("roleName"));
				
				scoreDto.setAudience(resultSet.getInt("audience"));
				scoreDto.setSales(resultSet.getInt("sales"));
				
				movieDto.setActorDto(actorDto);
				movieDto.setScoreDto(scoreDto);
				
				resultList.add(movieDto);
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
		
		return movieDto;
	}

	// 배우 정보 + 출연 영화 제목 + 배역 정보
	@Override
	public List<Dto> SearchActorInfo(String actorName) {
		
		resultList = new ArrayList<Dto>();
		
		ActorDto actorDto = null;
		RoleDto roleDto = null;
		
		String selectQuery = "SELECT *\r\n"
				+ "FROM actor a\r\n"
				+ "INNER JOIN role r\r\n"
				+ "ON a.actorName = r.actorName\r\n"
				+ "WHERE a.actorName = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, actorName);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				actorDto = new ActorDto();
				roleDto = new RoleDto();
				actorDto.setActorName(resultSet.getString("actorName"));
				actorDto.setBirthYear(resultSet.getInt("birthYear"));
				actorDto.setHeight(resultSet.getInt("height"));
				actorDto.setWeight(resultSet.getInt("weight"));
				actorDto.setPartner(resultSet.getString("partner"));
				actorDto.setImageFileName(resultSet.getString("image"));
				actorDto.setTitle(resultSet.getString("title"));

				roleDto.setRoleName(resultSet.getString("roleName"));
				roleDto.setCategory(resultSet.getString("category"));
				
				actorDto.setRoleDto(roleDto);
				
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

}
