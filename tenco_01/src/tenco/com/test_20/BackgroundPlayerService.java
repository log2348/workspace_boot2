package tenco.com.test_20;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

// 메인 쓰레드 바쁨 --> 키보드 이벤트 처리
// 백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player; // 포함관계 (composition)

	private List<Bubble> bubbles = new ArrayList<Bubble>();

	private static final int XPOINT = 50;
	private static final int YPOINT = 50;
	private static final int CENTER_POINT = 25;

	public BackgroundPlayerService(Player player) {
		this.player = player;
		this.bubbles = player.getBubbles();

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

				// 1. 버블 충돌 체크
				for (int i = 0; i < bubbles.size(); i++) {
					Bubble targetBubble = bubbles.get(i);
					if (targetBubble.getState() == 1) {

						if ((Math.abs(player.getX() - targetBubble.getX()) < 10)
								&& (Math.abs(player.getY() - targetBubble.getY()) > 0)
								&& (Math.abs(player.getY() - targetBubble.getY()) < 50)) {

							System.out.println("플레이어 물방울 충돌");

							new Thread(new Runnable() {

								@Override
								public void run() {
									targetBubble.removeBubble();

								}
							}).start();
							break;
						}
					}
				}

				Color leftColor = new Color(image.getRGB(player.getX() - 10, player.getY() + CENTER_POINT));
				Color rightColor = new Color(image.getRGB(player.getX() + XPOINT + 10, player.getY() + CENTER_POINT));

				int bottomColor = image.getRGB(player.getX() + 10, player.getY() + YPOINT)
						+ image.getRGB(player.getX() + XPOINT - 10, player.getY() + YPOINT + 5);

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

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("캐릭터가 범위를 벗어났습니다.");
			}

			try {
				// 캐릭터가 이동될 때 값을 못 찾는 경우가 있다. 이동 속도보다 더 빠르게 움직여야 해결 가능
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
