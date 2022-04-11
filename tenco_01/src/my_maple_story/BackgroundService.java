package my_maple_story;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundService implements Runnable {
	private BufferedImage image;
	private Player player;

	public BackgroundService(Player player){
		this.player = player;

		try {
			image = ImageIO.read(new File("images/maple_backgroundService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {

		while (true) {
			try {
                // RGB 값으로 벽, 바닥 구분
                // 벽 - 빨간색(R: 255, G: 0, B: 0), 바닥 - 파란색(R: 0, G: 0, B: 255)

                // 캐릭터의 왼쪽 위 좌표의 RGB값
				Color leftColor = new Color(image.getRGB(player.getX(), player.getY()));
                // 캐릭터의 오른쪽 위 좌표의 RGB값
				Color rightColor = new Color(image.getRGB(player.getX() + 100, player.getY()));
                
                // 캐릭터의 하단 색상 확인(빨강, 파랑이 아니면 벽, 바닥 아니므로 낙하하도록)
				int bottomColor = image.getRGB(player.getX(), player.getY() + 120) // leftColor + y좌표값 증가
						+ image.getRGB(player.getX() + 100, player.getY() + 120); // rightColor + y좌표값 증가

				if (bottomColor != -2) {
                // leftColor = -1, rightColor = -1 이 아닐 때 --> 하얀색
					player.setDown(false);
				} else {
					if (!player.isUp() && !player.isDown()) {
						player.down();
					}
				}

				if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
                    // leftColor 빨간색일 때 
					System.out.println("왼쪽 벽에 충돌하였습니다.");
					player.setLeftWallCrash(true);
					player.setLeft(false);
				} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
                    // rightColor 빨간색일 때 
					System.out.println("오른쪽 벽에 충돌하였습니다.");
					player.setRightWallCrash(true);
					player.setRight(false);
				} else {
					player.setLeftWallCrash(false);
					player.setRightWallCrash(false);
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("캐릭터가 화면 밖으로 벗어났습니다.");
			}

			try {
				 // 캐릭터가 이동할 때 값을 못 찾는 경우 발생 --> 이동 속도보다 더 빠르게 움직여야 해결
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}