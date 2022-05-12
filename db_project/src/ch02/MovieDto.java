package teamProject.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto extends Dto {

	private String releaseDate;
	private String genre;	
	private float starScore;	
	private String imageFileName;
	
	private ActorDto actorDto;
	private ScoreDto scoreDto;
	
	/*
	ArrayList<String> actorList = new ArrayList<String>();
	ArrayList<ScoreDto> scoreList = new ArrayList<ScoreDto>();
	
	
	class MovieActorList {
		String acotorName;
		String category;
		String roleName;
	}
	
	class MovieScoreList {
		int audience;
		float starScore;
	}
	*/

}
