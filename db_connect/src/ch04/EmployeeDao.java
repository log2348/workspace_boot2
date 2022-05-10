package ch04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDao implements IEmployeeDao {

	private DBClient dbClient;
	private Connection connection;

	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public EmployeeDao() {
		dbClient = DBClient.getInstance();
		connection = dbClient.getConnection();
	}

	// 직함 받아서 해당 직함의 직원 이력 출력 - INNER JOIN
	@Override
	public ArrayList<EmployeeDto> showTitleEmpInfo(String title) {
		
		ArrayList<EmployeeDto> resultList = new ArrayList<EmployeeDto>();
		
		String joinQuery = "SELECT * "
						+ "FROM employees AS a "
						+ "INNER JOIN titles AS b "
						+ "ON a.emp_no = b.emp_no "
						+ "WHERE b.title = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(joinQuery);
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmpNo(resultSet.getString("emp_no"));
				dto.setFirstName(resultSet.getString("first_name"));
				dto.setTitle(resultSet.getString("title"));
				resultList.add(dto);
				}
		} catch (Exception e) {
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


	// 부서명 받아서 해당 부서의 역대 매니저 정보 출력 - LEFT JOIN
	@Override
	public ArrayList<EmployeeDto> showManagerInfo(String deptName) {

		ArrayList<EmployeeDto> resultList = new ArrayList<EmployeeDto>();

		String joinQuery = "SELECT * "
						+ "FROM employees AS a "
						+ "LEFT JOIN dept_manager AS b "
						+ "ON a.emp_no = b.emp_no "
						+ "WHERE b.dept_no = (SELECT dept_no " 
											+ "FROM departments "
											+ "WHERE dept_name = ?) ";
		try {
			preparedStatement = connection.prepareStatement(joinQuery);
			preparedStatement.setString(1, deptName);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmpNo(resultSet.getString("emp_no"));
				dto.setFirstName(resultSet.getString("first_name"));
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
	
	// 풀네임 받아서 연봉 받은 횟수 반환 - INNER JOIN
	@Override
	public int showSalaryCount(String firstName, String lastName) {
		int salaryCount = 0;
		String joinQuery = "SELECT *, COUNT(*) "
							+ "FROM salaries AS s "
							+ "INNER JOIN employees AS e "
							+ "ON s.emp_no = e.emp_no "
							+ "WHERE e.first_name = ? "
							+ "AND e.last_name = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(joinQuery);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			resultSet = preparedStatement.executeQuery();

			// while문에 안 넣으면 예외 발생 - Before start of result set
			while(resultSet.next()) {
				salaryCount = resultSet.getInt("COUNT(*)");
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
		
		return salaryCount;
		
	}

	// 현재 해당 직함을 가진 직원 수 반환 - RIGHT JOIN
	@Override
	public int showTitleEmpNumber(String title) {
		
		int employeeNum = 0;
		
		String joinQuery = "SELECT *, COUNT(*) AS total "
							+ "FROM employees AS a "
							+ "RIGHT JOIN titles AS b "
							+ "ON a.emp_no = b.emp_no "
							+ "WHERE b.to_date = '9999-01-01' "
							+ "AND b.title = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(joinQuery);
			preparedStatement.setString(1, title);		
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				employeeNum = resultSet.getInt("total");				
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
		
		return employeeNum;

	}

	// 직원이 가장 최근에 받은 연봉(최고연봉)이 얼마인지 반환 - INNER JOIN
	@Override
	public int showSalary(String first_name, String last_name) {
		
		int recentSalary = 0;
		
		String joinQuery = "SELECT *, MAX(salary) max_salary "
							+ "FROM employees e "
							+ "INNER JOIN salaries s "
							+ "ON e.emp_no = s.emp_no "
							+ "WHERE e.first_name = ? "
							+ "AND e.last_name = ? ";
				
		try {
			preparedStatement = connection.prepareStatement(joinQuery);
			preparedStatement.setString(1, first_name);
			preparedStatement.setString(2, last_name);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				recentSalary = resultSet.getInt("max_salary");
				
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
		
		return recentSalary;
	}
}
