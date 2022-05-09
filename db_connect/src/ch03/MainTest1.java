package ch03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ch02.DBClient;

public class MainTest1 {

	public static void main(String[] args) {

		/*
		 * object는 main메서드에서 new 하기 때문에 언제 태어나서 언제 죽을 수 있는 것을 말하고 static 같은 것들은 프로그램
		 * 시작부터 끝까지 존재한다.
		 */
		DBClient client = DBClient.getInstance();
		Connection connection = client.getConnection();
		ResultSet resultSet = null;


		try {
			// 데이터 조회 (select)
			// Statement stmt = connection.createStatement();
			String selectQuery1 = "SELECT * FROM memberTbl where memberId = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery1);
			preparedStatement.setString(1, "jee"); // 물음표 첫번째

			resultSet = preparedStatement.executeQuery();

			System.out.println("");
			String selectQuery2 = "SELECT * FROM memberTbl WHERE memberId IN(?,?) ";
			preparedStatement = connection.prepareStatement(selectQuery2);
			preparedStatement.setString(1, "jee");
			preparedStatement.setString(2, "Dang");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println(resultSet.getString("memberId"));
				System.out.println(resultSet.getString("memberName"));
				System.out.println(resultSet.getString("memberAddress"));
			}

			// 데이터 등록 (insert)
			/*
			String inseretQuery = "INSERT INTO memberTbl VALUES(?, ?, ?) ";
			preparedStatement = connection.prepareStatement(inseretQuery);

			// 매핑
			preparedStatement.setString(1, "boot1");
			preparedStatement.setString(2, "개발자");
			preparedStatement.setString(3, "서울 판교");
			
			int resultCount = 0;
			resultCount = preparedStatement.executeUpdate();
			
			if(resultCount >= 1) {
				System.out.println("정상 등록 되었습니다.");
			} else {
				System.out.println("동일한 아이디가 존재하거나 잘못된 입력입니다.");
			}
			*/
			
			// 데이터 수정 (update)		
			String updateQuery = "UPDATE memberTbl SET memberName = ? WHERE memberId = ? ";
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, "이름변경1");
			preparedStatement.setString(2, "boot1");
			
			int updateCount = preparedStatement.executeUpdate();
			System.out.println("updateCount : " + updateCount);
			
			
			// 데이터 삭제 (delete)
			String deleteQuery = "DELETE FROM memberTbl WHERE memberId = ? ";
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setString(1, "boot1");
			
			int deleteCount = preparedStatement.executeUpdate();
			System.out.println("deleteCount : " + deleteCount);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
