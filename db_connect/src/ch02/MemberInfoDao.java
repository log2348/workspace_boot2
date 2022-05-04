package ch02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberInfoDao implements IMemberInfoDao {

	private static final String TABLE_NAME = "memberTBL";
	// DBClient를 통해서 DB 접속 처리를 하자!
	private DBClient dbClient;
	private Connection conn;
	
	public MemberInfoDao() {
		dbClient = DBClient.getInstance();
		conn = dbClient.getConnection();
	}
	
	@Override
	public ArrayList<MemberDto> select() {
		
		ArrayList<MemberDto> dataResult = new ArrayList<MemberDto>();
		
		// 밑에서 무조건 초기화되기 때문에 변수명만 선언
		String sqlFormat;
		String sql;
		
		sqlFormat = "SELECT * FROM %s";
		sql = String.format(sqlFormat, TABLE_NAME);
		
		// 연결 실패시 값이 안 들어갈 수 있기 때문에 선언과 동시에 초기화
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setMemberId(rs.getString("memberId"));
				dto.setMemberName(rs.getString("memberName"));
				dto.setMemberAddress(rs.getString("memberAddress"));
				
				dataResult.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 자원 닫아주기
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return dataResult;
	}

	@Override
	public int insert(MemberDto dto) {
		String sqlFormat = "INSERT INTO %s VALUES('%s', '%s', '%s')";
		String sql = String.format(sqlFormat, TABLE_NAME, dto.getMemberId(), dto.getMemberName(), dto.getMemberAddress());
		
		Statement stmt = null;
		int result = 0;
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			System.out.println("result : 행(레코드) 개수 : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public int update(MemberDto dto) {
		// 해당 레코드 존재 여부 검사 select
		String sqlFormat = "UPDATE %s SET memberName = '%s' WHERE memberId = '%s'";
		String sql = String.format(sqlFormat, TABLE_NAME, dto.getMemberName(), dto.getMemberId());
		int result = 0;
		
		// 오토 클로즈
		try(Statement stmt = conn.createStatement()) {
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int delete(String memberId) {
		// 값이 존재하지 않아도 동작 처리됨
		String sqlFormat = "DELETE FROM %s WHERE memberId = '%s'";
		String sql = String.format(sqlFormat, TABLE_NAME, memberId);
		int result = 0;
		
		try(Statement stmt = conn.createStatement()) {
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
