package ch01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
	
	protected String title;
	private String releaseDate;
	private String genre;	
	private float starScore;
	
	private String roleName;
	private String actorName;
	
	private int weight;
	private int height;
	private int birthYear;
	private String partner;

}
