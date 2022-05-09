package ch05;

import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) {
		EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
		ArrayList<EmployeeInfoDto> data;
		
		
		// Staff 직함의 직원 정보 출력		
		/*
		data = employeeInfoDao.showTitleEmpInfo("Staff");
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
		*/
		
		// Finance 부서에서 현재 근무 중인 매니저 조회
		
		data = employeeInfoDao.showPresentManagerInfo("Finance");
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
		
		
		
		// Mary Sluis가 근무하고 있는 부서 출력
		//System.out.println(employeeInfoDao.getTitle("Mary", "Sluis"));
		
		
		// 개발 부서에서 가장 최근에 입사한 사람 출력
		//data = employeeInfoDao.orderByHireDate("development");
		//System.out.println(data.get(0));

		// 현재 Finance 부서의 전체 직원 정보 출력
		/*
		data = employeeInfoDao.showDeptEmpInfo("Finance");	
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
		*/
	}

}
