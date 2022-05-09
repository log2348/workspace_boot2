package ch03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private String userName;
	private int birthYear;
	private String address;
	
	private String prodName;
	private int amount;
}
