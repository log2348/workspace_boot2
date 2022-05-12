package ch02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDao implements IManager {

	private DBClient dbClient;
	private Connection connection;

	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public ManagerDao() {
		dbClient = DBClient.getInstance();
		connection = dbClient.getConnection();
	}

	// 새로운 영화 삽입
	@Override
	public void insertMovieInfo(String title, String date, float StarScore, String genre, String imageFileName) {

		String insertQuery = "INSERT INTO movie VALUES (?, ?, ?, ?, ?);";

		try {
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, date);
			preparedStatement.setFloat(3, StarScore);
			preparedStatement.setString(4, genre);
			preparedStatement.setString(5, imageFileName);

			resultSet = preparedStatement.executeQuery();

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

	}

	// 영화 제목 받아서 삭제
	@Override
	public void deleteMovieInfo(String title) {
		String deleteQuery = "DELETE FROM movie WHERE title = ? ";

		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setString(1, title);

			resultSet = preparedStatement.executeQuery();

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

	}

	@Override
	public void updateStarScore(float starScore, String title) {

		String updateQuery = "UPDATE movie SET starScore = ? WHERE title = ? ";

		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setFloat(1, starScore);
			preparedStatement.setString(2, title);

			resultSet = preparedStatement.executeQuery();

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

	}

	@Override
	public void updateSales(int sales, String title) {
		String updateQuery = "UPDATE score SET sales = ? WHERE title = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(1, sales);
			preparedStatement.setString(2, title);
				
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

	}

}
