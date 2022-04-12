package ver1;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Player extends JLabel implements Moveable {

	// 위치상태
	private int x;
	private int y;

	// 플레이어의 방향
	private PlayerWay playerWay;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean kitchenUp;
	private boolean deliveryUp;
	private boolean kitchenDown;
	private boolean deliveryDown;

	// 플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;

	// 이미지 저장
	private ImageIcon kitPlayerF; // 키친에서의 앞모습
	// 일단 뒤(Top)으로 갈땐 뒷면말고 left/right모습으로 가기로
	private ImageIcon kitPlayerL; // 키친에서의 왼쪽모습
	private ImageIcon kitPlayerR; // 키친에서의 오른쪽모습

	private ImageIcon delPlayerL; // 배달맵에서의 왼쪽모습
	private ImageIcon delPlayerR;// 배달맵에서의 오른쪽모습
	// 배달맵에서는 앞모습뒷모습 둘 다 없음

	// TODO 나중에 객체랑 상호작용해주는 부분 구현필요할 듯
	// 예를들어 벽에 충돌한 상태

	public Player() {
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}

	private void initObject() {
		kitPlayerF = new ImageIcon("images/LoopyKit_front.png");
		kitPlayerL = new ImageIcon("images/LoopyKit_left.png");
		kitPlayerR = new ImageIcon("images/LoopyKit_right.png");

		delPlayerL = new ImageIcon("images/LoopyDel_left.png");
		delPlayerR = new ImageIcon("images/LoopyDel_right.png");

	}

	private void initSetting() {
		x = 0;
		y = 0;

		left = false;
		right = false;
		kitchenUp = false;
		kitchenDown = false;
		deliveryUp = false;
		deliveryDown = false;

		playerWay = PlayerWay.RIGHT;
		setIcon(kitPlayerF);
		setSize(55, 80); // 사이즈 통일
		setLocation(x, y);

	}

	private void initBackgroundPlayerService() {
//new Thread(new BackgroundPlayerService(this)).start();
	}

	@Override
	public void left() {
		System.out.println("left");
		playerWay = PlayerWay.LEFT;
		left = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {
					setIcon(kitPlayerL);
					x = x - SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void right() {
		System.out.println("right");
		playerWay = playerWay.RIGHT;
		right = true;
		new Thread(new Runnable() {
			@Override
			public void run() {

				while (right) {
					setIcon(kitPlayerR);
					x = x + SPEED;
					setLocation(x, y);

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	@Override
	public void kitchenUp() {
		System.out.println("up in 주방");
		kitchenUp = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (kitchenUp) {
					y = y - SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();

	}

	@Override
	public void kitchenDown() {
		System.out.println("down in 주방");
		kitchenDown = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (kitchenDown) {
					setIcon(kitPlayerF);
					y = y + SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}).start();

	}

	@Override
	public void deliveryUp() {
		System.out.println("up in 배달맵");
		deliveryUp = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30 / JUMPSPEED; i++) {
					y = y - JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				deliveryUp = false;
				deliveryDown();

			}
		}).start();

	}

	@Override
	public void deliveryDown() {
		System.out.println("down in 배달맵");
		deliveryDown = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (deliveryDown) {
					y = y + JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(3);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
				deliveryDown = false;
			}
		}).start();

	}

} // end of class
