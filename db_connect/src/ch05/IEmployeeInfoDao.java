package ch05;

import java.util.ArrayList;

public interface IEmployeeInfoDao {
	
	// 서브쿼리 사용한 기능구현 (서비스 로직)
	
	// 해당 부서에서 현재 근무 중인 직원 정보 출력
	ArrayList<EmployeeInfoDto> showDeptEmpInfo(String deptName);
	
	// 현재 해당 직함을 가진 직원 정보 출력
	ArrayList<EmployeeInfoDto> showPresenTitleEmp(String title);
	
	// 해당 부서에서 현재 근무 중인 매니저 정보 출력
	ArrayList<EmployeeInfoDto> showPresentManager(String deptName);
	
	// 해당 부서에서 가장 최근 입사한 직원 순으로 직원 정보 출력
	ArrayList<EmployeeInfoDto> orderByHireDate(String deptName);
	
	// 직원 이름으로 직함 조회
	String getTitle(String firstName, String lastName);
	
}
