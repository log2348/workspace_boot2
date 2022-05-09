package ch04;

public interface IEmployeeDao {
	
	// 최소 5개 이상 조인문
	
	void innerJoin();
	void leftJoin();
	void rightJoin();
	void crossJoin();

}
