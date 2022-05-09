package ch03;

import java.util.ArrayList;

public class MainTest2 {

	public static void main(String[] args) {
		ShopDbDao shopDbDao = new ShopDbDao();

		ArrayList<UserDto> data = shopDbDao.innerJoin("이순신");
		
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}

	}
}
