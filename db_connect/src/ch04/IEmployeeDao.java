package ch04;

import java.util.ArrayList;

public interface IEmployeeDao {
	
	// JOIN문 사용한 기능 구현 (서비스 로직)
	
	// 해당 직함의 직원 이력 출력
	ArrayList<EmployeeDto> showTitleEmpInfo(String title);
	
	// 부서명 받아서 해당 부서의 역대 매니저 정보 출력
	ArrayList<EmployeeDto> showManagerInfo(String deptName);
	
	// 이름 받아서 연봉 받은 횟수 반환
	int showSalaryCount(String firstName, String lastName);
	
	// 현재 해당 직함을 가진 직원 수 반환
	int showTitleEmpNumber(String title);
	
	// 직원이 가장 최근에 받은 연봉(최고연봉) 반환
	int showSalary(String first_name, String last_name);

}
