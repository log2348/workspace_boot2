package tenco.com.test_19;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	private BubbleFrame mContext;
	// 2단계
	private Bubble bubbleContext = this;

	// 의존성 컴포지션
	private Player player;
	private BackgroundBubbleObserver backgroundBubbleObserver;
	// Array
	private Enemy enemy;

	// 위치 상태
	private int x;
	private int y;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;

	// 적군을 맞춘 상태
	private int state; // 0 - 기본, 1 - 적군을 가둔 상태

	private ImageIcon bubble; // 기본 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울이 터진 상태

	// 의존 주입(Dependency Injection, DI) -> 생성자에 주입(구현방법)
	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.enemy = mContext.getEnemy();
		initObject();
		initSetting();
		initThread();

	}

	private void initObject() {
		bubble = new ImageIcon("images/bubble.png");
		bubbled = new ImageIcon("images/bubbled.png");
		bomb = new ImageIcon("images/bomb.png");
		backgroundBubbleObserver = new BackgroundBubbleObserver(this);
	}

	private void initSetting() {
		left = false;
		right = false;
		up = false;

		x = player.getX();
		y = player.getY();

		setIcon(bubble);
		setSize(50, 50);

		state = 0;

	}

	private void initThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (player.getPlayerWay() == PlayerWay.LEFT) {
					left();
				} else {
					right();
				}

			}
		}).start();
	}

	@Override
	public void left() {
		left = true;
		for (int i = 0; i < 250; i++) {
			x--;
			setLocation(x, y);
			// 현재 색상 체크(메소드 호출)
			if (backgroundBubbleObserver.checkLeftWall()) {
				left = false;
				break;
			}

			if ((Math.abs(x - enemy.getX()) < 10) && (Math.abs(y - enemy.getY()) > 0)
					&& (Math.abs(y - enemy.getY()) < 50)) {
				if (enemy.isLive()) {
					attack();
					break;
				}
			}

			threadSleep(1);
		}
		left = false;
		up();

	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 250; i++) {
			x++;
			setLocation(x, y);
			if (backgroundBubbleObserver.checkRightWall()) {
				right = false;
				break;
			}

			if ((Math.abs(x - enemy.getX()) < 10) && (Math.abs(y - enemy.getY()) > 0)
					&& (Math.abs(y - enemy.getY()) < 50)) {
				if (enemy.isLive()) {
					attack();
					break;
				}
			}
			threadSleep(1);
		}
		right = false;
		up();

	}

	@Override
	public void up() {
		up = true;

		while (up) {
			y--;
			setLocation(x, y);
			if (backgroundBubbleObserver.checkTopWall()) {
				up = false;
				break;
			}
			
			if(state == 0) {
				threadSleep(2);
			} else {
				threadSleep(10);
			}
			threadSleep(1);
		}
		up = false;
		if (state == 0) {
			removeBubble(); // 버블 객체 2초 뒤에 메모리에서 사라지도록

		}

	}

	public void removeBubble() {

		try {
			Thread.sleep(2000);
			setIcon(bomb);
			Thread.sleep(1000);
			bubbleContext = null; // 메모리에서 제거(가비지 컬렉터)
			setIcon(null);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void attack() {
		state = 1;
		setIcon(bubbled);
		enemy.setLive(false);
		// 적군에 살아있다, 죽었다 상태값 부여
		mContext.remove(enemy); // 메모리에서 지우기
		mContext.repaint();
		// 가비지 컬렉터 즉시 발생되지 않음
	}

	private void threadSleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
