package tenco.com.test_18;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy extends JLabel implements Moveable {

	private BubbleFrame mContext;

	// 위치 상태
	private int x;
	private int y;

	// 플레이어의 방향
	private EnemyWay enemyWay;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 적군 속도 상태
	private final int SPEED = 2;
	private final int JUMPSPEED = 1;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	private ImageIcon enemyL;
	private ImageIcon enemyR;

	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}

	// 계속 돌아가도록
	private void initBackgroundPlayerService() {
		// new Thread(new BackgroundPlayerService(this)).start();

	}

	private void initObject() {
		enemyR = new ImageIcon("images/enemyR.png");
		enemyL = new ImageIcon("images/enemyL.png");
	}

	private void initSetting() {
		x = 500;
		y = 180;

		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;

		enemyWay = EnemyWay.LEFT;

		setIcon(enemyL);
		setSize(50, 50);
		setLocation(x, y);
	}

	// 이벤트 핸들러
	@Override
	public void left() {
		System.out.println("left");
		enemyWay = EnemyWay.LEFT;
		left = true;

		new Thread(new Runnable() {

			// while문 - 자연스러운 움직임
			@Override
			public void run() {

				while (left) {
					setIcon(enemyL);
					x -= SPEED;
					setLocation(x, y);

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				left = false;

			}
		}).start();
	}

	@Override
	public void right() {
		System.out.println("right");
		enemyWay = EnemyWay.RIGHT;
		right = true;

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (right) {
					setIcon(enemyR);
					x += SPEED;
					setLocation(x, y);

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				right = false;

			}
		}).start();
	}

	@Override
	public void up() {
		System.out.println("up");
		up = true;

		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 0; i < (130 / JUMPSPEED); i++) {
					y -= JUMPSPEED;
					setLocation(x, y);

					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				up = false;
				down();
			}

		}).start();
	}

	@Override
	public void down() {
		System.out.println("down");
		down = true;
		new Thread(new Runnable() {

			@Override
			public void run() {

				while (down) {
					y = y + JUMPSPEED;
					setLocation(x, y);

					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				down = false;
			}
		}).start();

	}

} // end of class
