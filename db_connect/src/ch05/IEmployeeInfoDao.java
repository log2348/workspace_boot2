package ch05;

import java.util.ArrayList;

public interface IEmployeeInfoDao {
	
	// 서브쿼리 사용
	// 기능구현 (서비스 로직)
	ArrayList<EmployeeInfoDto> showDeptEmpInfo(String deptName);
	ArrayList<EmployeeInfoDto> showTitleEmpInfo(String title);
	ArrayList<EmployeeInfoDto> showPresentManagerInfo(String deptName);
	ArrayList<EmployeeInfoDto> orderByHireDate(String deptName);
	String getTitle(String firstName, String lastName);
}
