package tenco.com.test_00;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	private Player player;

	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);

	}

	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("C:\\workspace_boot2\\tenco_01\\images/backgroundMap.png")); // ImageIcon 사용시 파일명 오류 발견 어려움(디버깅 어려움)
		setContentPane(backgroundMap);

		player = new Player();
		add(player);

	}

	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); // absolute (좌표값으로 자유롭게 그림을 그릴 수 있다.)

		setLocationRelativeTo(null); // JFrame 가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
					if (!player.isUp() && !player.isDown()) {
						player.up();
					}
					break;
				case KeyEvent.VK_SPACE:
					add(player.attackBubble());
					break;
				}

			} // end of keyPressed

			@Override
			public void keyReleased(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;

				}
			}
		});

	}

	public static void main(String[] args) {
		new BubbleFrame();
	}

}
