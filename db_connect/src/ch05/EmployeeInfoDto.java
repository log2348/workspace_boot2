package ch05;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeInfoDto {
	
	private String empNo;
	private String title;
	private String deptNo;
	private String deptName;
	private String firstName;
	private String lastName;
	
	@Override
	public String toString() {
		return "직원 번호 : " + empNo
				+ ", 부서 번호 : " + deptNo
				+ ", 부서 이름 : " + deptName
				+ ", 이름 : " + firstName
				+ ", 성 : " + lastName
				+ ", 직함 : " + title;	
	}

}
