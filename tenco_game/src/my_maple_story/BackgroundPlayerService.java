package tenco_game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundPlayerService implements Runnable {

	private BufferedImage backgroundImg;
	private Player player;

	public BackgroundPlayerService(Player player) {
		this.player = player;

		try {
			backgroundImg = ImageIO.read(new File("images/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
	
		while(true) {
			if(player.getX() == backgroundImg.getMinX()) {
				
				
			} else if(player.getX() == backgroundImg.getMinX()) {
				
			}
		}
		
	}

}
