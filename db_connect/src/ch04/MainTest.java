package ch04;

import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) {
		EmployeeDao employeeDao = new EmployeeDao();
		ArrayList<EmployeeDto> data;
//		data = employeeDao.showManagerInfo("development");
//		for (int i = 0; i < data.size(); i++) {
//			System.out.println(data.get(i));
//		}
		
		
//		System.out.println(employeeDao.showNumberOfTitle("Engineer"));
		
//		System.out.println(employeeDao.showSalary("Georgi", "Facello"));
		// Engineer인 직원 정보 출력
		/*
		data = employeeDao.showTitleEmpInfo("Engineer");
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
			
		}
		*/
		
		System.out.println(employeeDao.getSalaryCount("Mary", "Sluis"));
	}

}
