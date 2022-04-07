package ch09;

public class Company {
	
	// 멤버변수
	private String companyName;
	private Employee employee;
	
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Company() {
	}
	
	public Company(String companyName) {
		this.companyName = companyName;
	}
	
	public void joinCompany(Employee employee) {
		this.employee = employee;
	}
	
	// 사원의 정보 출력
	public void showEmployeeInfo() {
		System.out.println(employee.getEmployeeId());
		System.out.println(employee.getEmployeeName());
		System.out.println(employee.getDepartment());
	}
	
	
}
