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

	// 직함 이름 받아서 그에 해당하는 직원 정보 출력
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
				dto.setEmp_no(resultSet.getString("emp_no"));
				dto.setFirst_name(resultSet.getString("first_name"));
				dto.setTitle(title);
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


	// 부서이름 받아서 해당 부서의 역대 매니저 정보 출력
	@Override
	public ArrayList<EmployeeDto> showManagerInfo(String deptName) {

		ArrayList<EmployeeDto> resultList = new ArrayList<EmployeeDto>();

		String joinQuery = "SELECT * "
						+ "FROM employees AS a "
						+ "LEFT JOIN dept_manager AS b "
						+ "ON a.emp_no = b.emp_no "
						+ "WHERE b.dept_no = (SELECT dept_no " 
											+ "FROM departments "
											+ "WHERE dept_name = ? )";
		try {
			preparedStatement = connection.prepareStatement(joinQuery);
			preparedStatement.setString(1, deptName);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmp_no(resultSet.getString("emp_no"));
				dto.setFirst_name(resultSet.getString("first_name"));
				dto.setDept_no(resultSet.getString("dept_no"));
				dto.setDept_name(deptName);
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
	
	// 이름 받아서 연봉 받은 횟수 조회
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

			// while문에 안 넣으면 오류 발생
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

	// 직함명 받아 현재 근무하는 해당 직함의 직원 수 출력
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

			// while문에 안 넣으면 오류 발생
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


	// 직원이 가장 최근에 받은 연봉이 얼마인지 반환
	@Override
	public int showSalary(String first_name, String last_name) {
		
		String joinQuery = "SELECT *, MAX(salary) AS max_salary "
				+ "FROM employees e "
				+ "INNER JOIN salaries s "
				+ "ON e.emp_no = s.emp_no "
				+ "WHERE e.first_name = ? "
				+ "AND e.last_name = ? ";
		
		int recentSalary = 0;
		
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
