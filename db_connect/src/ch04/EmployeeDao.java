package ch04;

import java.sql.Connection;

import ch02.DBClient;

public class EmployeeDao implements IEmployeeDao {
	
	private DBClient dbClient;
	private Connection connection;
	
	public EmployeeDao() {
		dbClient = DBClient.getInstance();
		connection = dbClient.getConnection();
	}

	@Override
	public void innerJoin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftJoin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rightJoin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crossJoin() {
		// TODO Auto-generated method stub
		
	}

}
