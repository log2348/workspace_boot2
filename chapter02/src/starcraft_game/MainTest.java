package starcraft_game;

import starcraft_game.protoss.GateWay;
import starcraft_game.protoss.Porge;
import starcraft_game.protoss.Protoss;
import starcraft_game.terran.Barrak;
import starcraft_game.zerg.Hatchery;

public class MainTest {

	public static void main(String[] args) {
		// 게이트 생성
		// 생산된 유닛을  12마리 배열에 담아 주세요 !!
		
		GateWay gateway = new GateWay();
		gateway.createProtossUnit();
		// for --> 배열에 담을 수 있다
		
		//Hatchery hatchery = new Hatchery();
		//hatchery.createZergUnit();
		
		//Barrak barrak = new Barrak();
		//barrak.createTerranUnit();
		
		// 테란 (Barrak)
		// fireBat, Marine
		
		// 저그 (Hatchery)
		// Hydra, Ultra, 저글링
		
		Porge porge = new Porge();
		porge.upgradeAttack();
		
		
		
	}

}
