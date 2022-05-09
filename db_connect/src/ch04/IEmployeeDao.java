package ch04;

import java.util.ArrayList;

public interface IEmployeeDao {
	
	// JOIN문
	ArrayList<EmployeeDto> showTitleEmpInfo(String title);
	ArrayList<EmployeeDto> showManagerInfo(String deptName);
	int getSalaryCount(String firstName, String lastName);
	int showNumberOfTitle(String title);
	int showSalary(String first_name, String last_name);

}
