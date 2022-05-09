package ch05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeInfoDao implements IEmployeeInfoDao {
	
	private DBClient dbClient;
	private Connection connection;
	
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	private ArrayList<EmployeeInfoDto> resultList;
	
	public EmployeeInfoDao() {
		dbClient = DBClient.getInstance();
		connection = dbClient.getConnection();
	}

	// 중첩 쿼리문 사용
	// 해당 부서에서 현재 근무 중인 직원 정보 출력
	@Override
	public ArrayList<EmployeeInfoDto> showDeptEmpInfo(String deptName) {
		
		resultList = new ArrayList<EmployeeInfoDto>();
		
		String selectQuery = "SELECT * "
							+ "FROM employees AS a "
							+ "LEFT JOIN dept_emp AS b "
							+ "on a.emp_no = b.emp_no "
							+ "WHERE b.dept_no = (SELECT dept_no "
												+ "FROM departments AS d "
												+ "WHERE dept_name = ?) "
							+ "AND to_date = '9999-01-01' ";
		try {
			
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, deptName);
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				EmployeeInfoDto dto = new EmployeeInfoDto();
				dto.setEmpNo(resultSet.getString("emp_no"));
				dto.setFirstName(resultSet.getString("first_name"));
				dto.setLastName(resultSet.getString("last_name"));
				dto.setDeptNo(resultSet.getString("dept_no"));
				dto.setDeptName(deptName);
						
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

	// 부서명 받아서 현재 근무 중인 매니저 정보 출력 (중첩 서브쿼리)
	@Override
	public ArrayList<EmployeeInfoDto> showPresentManagerInfo(String deptName) {
		
		resultList = new ArrayList<EmployeeInfoDto>();
		String selectQuery = "SELECT * "
							+ "FROM employees as A "
							+ "WHERE A.emp_no = (SELECT emp_no "
												+ "FROM dept_manager "
												+ "WHERE to_date = '9999-01-01' "
												+ "AND dept_no = (SELECT dept_no "
																+ "FROM departments "
																+ "WHERE dept_name = ?) "
												+ ") ";
		
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, deptName);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				EmployeeInfoDto dto = new EmployeeInfoDto();
				dto.setEmpNo(resultSet.getString("emp_no"));
				dto.setFirstName(resultSet.getString("first_name"));
				dto.setLastName(resultSet.getString("last_name"));
				dto.setDeptName(deptName);
				resultList.add(dto);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
		
	}

	// 인라인 뷰 사용
	// 해당 직함의 직원 정보 전체 조회
	@Override
	public ArrayList<EmployeeInfoDto> showTitleEmpInfo(String title) {
		
		ArrayList<EmployeeInfoDto> resultList = new ArrayList<EmployeeInfoDto>();
		
		String selectQuery = "SELECT * "
								+ "FROM employees as a, (SELECT * "
														+ "FROM titles "
														+ "WHERE title = ?) AS b "
								+ "WHERE a.emp_no = b.emp_no ";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				EmployeeInfoDto dto = new EmployeeInfoDto();
				dto.setEmpNo(resultSet.getString("emp_no"));
				dto.setFirstName(resultSet.getString("first_name"));
				dto.setLastName(resultSet.getString("last_name"));
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

	// 인라인 뷰 사용
	// 입력한 직원의 직함 출력
	@Override
	public String getTitle(String firstName, String lastName) {

		String title = null;
		String selectQuery = "SELECT *, (SELECT b.title "
										+ "FROM titles as b "
										+ "WHERE a.emp_no = b.emp_no "
										+ "GROUP BY emp_no) as title "
							+ "FROM employees as a "
							+ "WHERE a.first_name = ? and a.last_name = ? ";
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				title = resultSet.getString("title");
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
		return title;
	}

	// 해당 부서에서 가장 최근 입사한 직원 순으로 출력
	@Override
	public ArrayList<EmployeeInfoDto> orderByHireDate(String deptName) {
		
		ArrayList<EmployeeInfoDto> resultList = new ArrayList<EmployeeInfoDto>();
		String selectQuery = "SELECT * "
							+ "FROM employees as a "
							+ "LEFT join dept_emp as b "
							+ "ON a.emp_no = b.emp_no "
							+ "WHERE b.dept_no = (select dept_no "
												+ "from departments as d "
												+ "where dept_name = ?) "
							+ "ORDER BY a.hire_date DESC ";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, deptName);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				EmployeeInfoDto dto = new EmployeeInfoDto();
				dto.setEmpNo(resultSet.getString("emp_no"));
				dto.setFirstName(resultSet.getString("first_name"));
				dto.setLastName(resultSet.getString("last_name"));
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
