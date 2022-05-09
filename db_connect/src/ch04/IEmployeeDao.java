package ch04;

import java.util.ArrayList;

public interface IEmployeeDao {
	
	// JOIN
	ArrayList<EmployeeDto> showTitleEmpInfo(String title);
	ArrayList<EmployeeDto> showManagerInfo(String deptName);
	int showSalaryCount(String firstName, String lastName);
	int showTitleEmpNumber(String title);
	int showSalary(String first_name, String last_name);

}
