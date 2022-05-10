package ch05;

import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) {
		
		EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
		ArrayList<EmployeeInfoDto> data;	
				
		// 현재 직함이 Staff인 직원 정보 출력
		data = employeeInfoDao.showPresenTitleEmp("Staff");
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}	
		
		
		// Marketing 팀의 현재 팀장(매니저) 정보 출력
		/*
		data = employeeInfoDao.showPresentManager("Marketing");
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
		*/
		
		
		// Mary Sluis의 직함 출력
		// System.out.println("title : " + employeeInfoDao.getTitle("Mary", "Sluis"));
		
		
		// 개발 부서에서 가장 최근에 입사한 사람 출력
		/*
		data = employeeInfoDao.orderByHireDate("Development");
		System.out.println(data.get(0));
		*/

		// 현재 Finance 부서에서 근무 중인 전체 직원 정보 출력
		/*
		data = employeeInfoDao.showDeptEmpInfo("Finance");	
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
		*/
				
	}

}
