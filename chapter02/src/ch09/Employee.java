package ch09;

public class Employee {
	
	public static int serialNum;
	
	private int employeeId;
	private String employeeName;
	private String department;
	
	public Employee() {
		serialNum++;
		this.employeeId = serialNum;
	}
	
	public void showInfo() {
		System.out.println("사원 번호 : " + employeeId);
		System.out.println("사원 이름 : " + employeeName);
		System.out.println("사원 부서 : " + department);
	}

	// 단축키 사용(Alt + Shift + s)
	// getter, setter
	public static int getSerialNum() { // static 프로그램이 시작되자마자
		// Static 주의점
		// Static 영역에서 멤버변수에 접근할 수 없다.
		// String temp = serialNum + "_" + employeeId; // 멤버변수 - 객체가 메모리에 올라가야(new 키워드) 사용할 수 있는 변수
		return serialNum;
	}

	public static void setSerialNum(int serialNum) {
		Employee.serialNum = serialNum;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		// 홍길동_사원번호
		this.employeeName = employeeName + "_" + serialNum;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	// 1. static 변수
	// 2. 멤버 변수
	// 3. 지역 변수
	// 결론 : 어느 메모리에 위치하느냐에 따라 이름을 구분지어 말할 수 있다.
	
}
