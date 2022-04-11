package tenco.com.test_16;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// 메인 쓰레드 바쁨 --> 키보드 이벤트 처리
// 백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player; // 포함관계 (composition)

	public BackgroundPlayerService(Player player) {
		this.player = player;

		try {
			image = ImageIO.read(new File("images/backgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// 쓰레드가 아니라 while문이 돌아가는 것 -- 쓰레드는 한번 작동하고 소멸

		// 색상 확인
		while (true) {
			try {
				Color leftColor = new Color(image.getRGB(player.getX() - 10, player.getY() + 25));
				Color rightColor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY() + 25));
				
				int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5)
						+ image.getRGB(player.getX() + 50 - 10, player.getY() + 50 + 5);

				// -1(흰색)이 아니라는 것은 빨간색이거나 파란색이다.
				if (bottomColor != -2) {
					player.setDown(false);
				} else { // -2일때 : 바닥색 하얀색
					// 점프 하는 순간 down 메서드가 호출
					if (!player.isUp() && !player.isDown()) { // 질문
						player.down();
					}
				}

				if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
					System.out.println("왼쪽 벽에 충돌했어 !!");
					player.setLeftWallCrash(true);
					player.setLeft(false);
				} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
					System.out.println("오른쪽 벽에 충돌했어 !!");
					player.setRightWallCrash(true);
					player.setRight(false);
				} else {
					player.setLeftWallCrash(false);
					player.setRightWallCrash(false);
				}

//				System.out.println("==============================================");
//				System.out.println("왼쪽 색상 : " + leftColor);
//				System.out.println("오른쪽 색상 : " + rightColor);
//				System.out.println("==============================================");

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("캐릭터가 범위를 벗어났습니다.");
			}

			try {
				/*
				 * 캐릭터가 이동될 때 값을 못 찾는 경우가 있다.
				 * 이동 속도보다 더 빠르게 움직여야 해결 가능
				 */
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
