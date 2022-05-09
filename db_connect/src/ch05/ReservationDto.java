package ch05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
	
	private int id;
	private String name;
	private String reserveDate;
	private int roomNum;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
