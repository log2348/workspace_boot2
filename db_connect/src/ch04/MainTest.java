package ch04;

import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) {
		
		EmployeeDao employeeDao = new EmployeeDao();
		ArrayList<EmployeeDto> data;
		
		data = employeeDao.showManagerInfo("Marketing");
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
		
		// Engineer 직함의 현재 직원 수
		//System.out.println("Engineer 직원 수 : " + employeeDao.showTitleEmpNumber("Engineer") + "명");
		
		// Gergi Facello가 최근에 받은 연봉
		// System.out.println(employeeDao.showSalary("Georgi", "Facello"));
		
		// Engineer인 직원 정보 출력
		/*
		data = employeeDao.showTitleEmpInfo("Engineer");
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));			
		}
		*/
		
		// Mary가 연봉 받은 횟수
		//System.out.println(employeeDao.showSalaryCount("Mary", "Sluis"));
	}

}
