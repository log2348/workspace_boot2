package ch02;

import java.util.List;

public class MainTest {

	public static void main(String[] args) {

		ServerServiceDao serverServiceDao = new ServerServiceDao();
		
		List<Dto> data;
		
		/*
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
		*/

		Dto dto = new ActorDto();
		dto = serverServiceDao.SearchActorInfo("정유미");
		
		System.out.println(dto.getActorName());

			
		
		

	}

}
