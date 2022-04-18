package tenco.com.test_00;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	// 2단계
	private Bubble bubbleContext = this;

	// 의존성 컴포지션
	private Player player;

	private BackgroundBubbleObserver backgroundBubbleObserver;

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

	/*
	 * 의존 주입(Dependency Injection, DI) -> 생성자에 주입(구현방법) 의존관계를 외부에서 결정하고 주입하는 것.
	 * Bubble이 어떤 값을 가질지 Player가 정한다.
	 */
	public Bubble(Player player) {
		// 의존성 컴포지션 관계
		this.player = player;
		initObject();
		initSetting();
		initThread();

	}

	private void initObject() {
		bubble = new ImageIcon("C:\\workspace_boot2\\tenco_01\\images/bubble.png");
		bubbled = new ImageIcon("C:\\workspace_boot2\\tenco_01\\images/bubbled.png");
		bomb = new ImageIcon("C:\\workspace_boot2\\tenco_01\\images/bomb.png");
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
		for (int i = 0; i < 400; i++) {
			x--;
			setLocation(x, y);
			// 현재 색상 체크(메소드 호출)
			if (backgroundBubbleObserver.checkLeftWall()) {
				left = false;
				break;
			}
			threadSleep(1);
		}
		left = false;
		up();

	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 400; i++) {
			x++;
			setLocation(x, y);
			if (backgroundBubbleObserver.checkRightWall()) {
				right = false;
				break;
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
			threadSleep(1);
		}
		up = false;
		removeBubble(); // 버블 객체 2초 뒤에 메모리에서 사라지도록

	}

	private void removeBubble() {
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

	private void threadSleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
