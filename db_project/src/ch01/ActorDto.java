package ch01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto extends MovieDto {
	
	private int weight;
	private int height;
	private int birthYear;
	private String partner;
	
	@Override
	public String toString() {
		return super.toString();
	}

}
