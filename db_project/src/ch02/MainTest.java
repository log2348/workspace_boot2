package teamProject.db;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

	public static void main(String[] args) {
		List<Dto> data = new ArrayList<Dto>();
		MovieAndActorDao dao= new MovieAndActorDao();
		
		data = dao.SearchActorInfo("마동석");
		
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
			System.out.println(data.get(i).getTitle());
		}

	}

}
