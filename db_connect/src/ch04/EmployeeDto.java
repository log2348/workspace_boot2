package ch04;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
	
	private String empNo;
	private String deptNo;
	private String deptName;	
	private String firstName;
	private String title;
	
	@Override
	public String toString() {
		return "직원 번호 : " + empNo
				+ ", 부서 번호 : " + deptNo
				+ ", 부서 이름 : " + deptName
				+ ", 이름 : " + firstName
				+ ", 직함 : " + title;	
	}

}
