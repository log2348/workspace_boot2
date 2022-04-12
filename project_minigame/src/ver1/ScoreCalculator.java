package ver1;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreCalculator {

	private Player player;
	
	private int grade;
	private int sales;
	
	public ScoreCalculator(Player player) {
		this.player = player;
	}
	
	private void completeDelivery() {
		
	}
	
	private void getDayGoal() {
		Random random = new Random();
		int goalSales = random.nextInt(10) * 10000;
	}
	
	
	
}
