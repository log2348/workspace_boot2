package ch03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ch02.DBClient;

public class ShopDbDao implements IShopDbDao {

	private DBClient client;
	private Connection connection;

	public ShopDbDao() {
		client = DBClient.getInstance();
		connection = client.getConnection();
	}

	@Override
	public ArrayList<UserDto> innerJoin(String userName) {

		ArrayList<UserDto> resultData = new ArrayList<UserDto>();

		try {
			String sql = "SELECT * FROM userTbl AS a INNER JOIN buyTbl AS b ON a.userName = b.userName where a.userName = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				UserDto userDto = new UserDto();
				userDto.setUserName(resultSet.getString("userName"));
				userDto.setBirthYear(resultSet.getInt("birthYear"));
				userDto.setProdName(resultSet.getString("prodName"));
				userDto.setAmount(resultSet.getInt("amount"));
				userDto.setAddress(resultSet.getString("addr"));
				resultData.add(userDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultData;

	}

	@Override
	public ArrayList<UserDto> leftJoin(String userName) {

		ArrayList<UserDto> resultData = new ArrayList<UserDto>();

		try {

			String sql = "SELECT * FROM userTbl AS a LEFT JOIN buyTbl AS b ON a.userName = b.userName WHERE b.userName = ? ";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				UserDto userDto = new UserDto();
				userDto.setUserName(resultSet.getString("userName"));
				userDto.setProdName(resultSet.getString("prodName"));
				userDto.setAmount(resultSet.getInt("amount"));
				userDto.setBirthYear(resultSet.getInt("birthYear"));
				userDto.setAddress(resultSet.getString("addr"));
				resultData.add(userDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultData;

	}

	@Override
	public ArrayList<UserDto> rightJoin(String userName) {

		ArrayList<UserDto> resultData = new ArrayList<UserDto>();

		try {
			PreparedStatement preparedStatement = null;
			String sql = "SELECT * FROM buyTbl AS a RIGHT JOIN userTbl AS b ON a.userName = b.userName WHERE b.userName = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				UserDto userDto = new UserDto();
				userDto.setUserName(resultSet.getString("userName"));
				userDto.setBirthYear(resultSet.getInt("birthYear"));
				userDto.setAddress(resultSet.getString("addr"));
				resultData.add(userDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultData;

	}

	// 고객명으로 구매내역 조회하기
	@Override
	public ArrayList<UserDto> buyInfo(String userName) {

		ArrayList<UserDto> buyInfo = new ArrayList<UserDto>();

		try {
			String sql = "SELECT * FROM buyTbl WHERE userName = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				UserDto userDto = new UserDto();
				userDto.setUserName(resultSet.getString("userName"));
				userDto.setProdName(resultSet.getString("prodName"));
				userDto.setAmount(resultSet.getInt("amount"));
				buyInfo.add(userDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return buyInfo;
	}

}
