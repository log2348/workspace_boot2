package tenco.com.test_20;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends JLabel implements Moveable {
	
	private BubbleFrame mContext;
	private List<Bubble> bubbles;
	
	// 위치 상태
	private int x;
	private int y;

	// 플레이어의 방향
	private PlayerWay playerWay;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	private ImageIcon playerL;
	private ImageIcon playerR;

	public Player(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}

	// 계속 돌아가도록
	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();

	}

	private void initObject() {
		bubbles = new ArrayList<Bubble>();
		playerR = new ImageIcon("images/playerR.png");
		playerL = new ImageIcon("images/playerL.png");
	}

	private void initSetting() {
		x = 80;
		y = 535;

		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;

		playerWay = playerWay.RIGHT;

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	// 이벤트 핸들러
	@Override
	public void left() {
		System.out.println("left");
		playerWay = PlayerWay.LEFT;
		left = true;

		new Thread(new Runnable() {

			// while문 - 자연스러운 움직임
			@Override
			public void run() {

				while (left) {
					setIcon(playerL);
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
		playerWay = PlayerWay.RIGHT;
		right = true;

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (right) {
					setIcon(playerR);
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

	// 메소드 이름 : 동사 + 명사
	public void attackBubble() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Bubble bubble = new Bubble(mContext);
				mContext.add(bubble);
				bubbles.add(bubble);
				if(playerWay == PlayerWay.LEFT) {
					bubble.left();
				} else {
					bubble.right();
				}
			}
		}).start();
	}

} // end of class
