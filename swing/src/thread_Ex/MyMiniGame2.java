package thread_Ex;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyMiniGame2 extends JFrame implements ActionListener {

	private BufferedImage backgroundImg;
	private BufferedImage iconImg1;
	private BufferedImage iconImg2;

	private CustomJPanel customJPanel;

	private static int keyMoveXPoint = 0;
	private static int keyMoveYPoint = 0;

	private static int autoMoveXpoint = 0;

	private static final int MAX_X = 460;
	private static final int MAX_Y = 440;

	private static final int MIN_X = -20;
	private static final int MIN_Y = -20;

	private boolean isThread = true;

	private JPanel bottomPanel;

	private JButton moveButton;
	private JButton stopButton;

	private String backgroundImgName = "flower.jpg";
	private String iconImgName1 = "honeybee.png";
	private String iconImgName2 = "butterfly.png";

	public MyMiniGame2() {
		initData();
		setInitLayout();
		addEventListener();

		// 생성자에서 Thread start 처리
		new Thread(customJPanel).start();
		// customJPanel.run();

	}

	private void initData() {
		setSize(MAX_X, MAX_Y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		customJPanel = new CustomJPanel();
		customJPanel.setSize(MAX_X, MAX_Y);

		bottomPanel = new JPanel(new FlowLayout());

		moveButton = new JButton("MOVE");
		stopButton = new JButton("STOP");

		// TODO 파일 가져오기
		try {
			backgroundImg = ImageIO.read(new File(backgroundImgName));
			iconImg1 = ImageIO.read(new File(iconImgName1));
			iconImg2 = ImageIO.read(new File(iconImgName2));
		} catch (IOException e) {
			System.out.println("파일이 존재하지 않습니다.");
		}

	}

	private void setInitLayout() {
		setSize(600, 600);
		setVisible(true);
		setResizable(false);

		bottomPanel.add(moveButton);
		bottomPanel.add(stopButton);

		add(customJPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		this.requestFocusInWindow();

	}

	private void addEventListener() {

		moveButton.addActionListener(this);
		stopButton.addActionListener(this);

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO 이미지 2번을 키 이벤트를 받아서 동작 시켜주세요
				int keyCode = e.getKeyCode();

				if (keyCode == KeyEvent.VK_UP) {
					keyMoveYPoint = (keyMoveYPoint < MIN_Y) ? MIN_Y : keyMoveYPoint - 10;
				} else if (keyCode == KeyEvent.VK_DOWN) {
					keyMoveYPoint = (keyMoveYPoint > MAX_Y) ? MAX_Y : keyMoveYPoint + 10;
				} else if (keyCode == KeyEvent.VK_RIGHT) {
					keyMoveXPoint = (keyMoveXPoint > MAX_X) ? MAX_X : keyMoveXPoint + 10;
				} else if (keyCode == KeyEvent.VK_LEFT) {
					keyMoveXPoint = (keyMoveXPoint < MIN_X) ? MIN_X : keyMoveXPoint - 10;
				}

				repaint();

				// System.out.println("x : " + keyMoveYPoint); // -20 ~ 460
				// System.out.println("y : " + keyMoveYPoint); // -20 ~ 440

			}
		});

		this.requestFocusInWindow();

	}

	private class CustomJPanel extends JPanel implements Runnable {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// TODO 이미지 그리기 3개
			g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), null);
			g.drawImage(iconImg1, keyMoveXPoint, keyMoveYPoint, getWidth() / 4, getHeight() / 4, null);
			g.drawImage(iconImg2, autoMoveXpoint, MAX_Y - 100, getWidth() / 4, getHeight() / 4, null);
		}

		@Override
		public void run() {

			/*
			 * 이미지 3번을 좌 우 왔다갔다 while(true) <<-- 이미지 하나를 >>>>>> <<<<<<< 이동하도록
			 * 
			 * thread.sleep 사용
			 */
			boolean direction = true;

			while (isThread) {

				if (isThread) {
					if (direction) {
						autoMoveXpoint += 10;
					} else {
						autoMoveXpoint -= 10;
					}

					if (autoMoveXpoint == MAX_X) {
						direction = false;
					}

					if (autoMoveXpoint == MIN_X) {
						direction = true;
					}

					try {
						Thread.sleep(200);
					} catch (Exception e) {
						e.printStackTrace();
					}

				} // end of if - isThread

				repaint();
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton selectedBtn = (JButton) e.getSource();

		if (selectedBtn.getText().equals(this.moveButton.getText())) {
			isThread = true;
		} else if (selectedBtn.getText().equals(this.stopButton.getText())) {
			isThread = false;
		}

		requestFocusInWindow();
	}

	public static void main(String[] args) {
		new MyMiniGame2();
	}

}
