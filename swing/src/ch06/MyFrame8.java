package ch06;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame8 extends JFrame implements KeyListener {

	// 변수의 효용

	private BufferedImage bgImage;
	private BufferedImage iconImage;
	
	private static final String IMAGE_NAME= "flower.jpg";
	private static final String ICON_NAME= "butterfly.png";

	private MyImagePanel myImagePanel;

	private static int background_width = 700;
	private static int background_height = 700;

	private static int icon_x = 0;
	private static int icon_y = 0;

	// 내부 클래스 선언 - paint 오버라이드

	// 이벤트 리스너 등록
	public MyFrame8() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("도전 과제");
		setSize(background_width, background_height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		myImagePanel = new MyImagePanel();

		try {
			bgImage = ImageIO.read(new File(IMAGE_NAME));
			iconImage = ImageIO.read(new File(ICON_NAME));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}

	}

	private void setInitLayout() {
		setVisible(true);
		setResizable(false);
		add(myImagePanel);

	}

	private class MyImagePanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(bgImage, 0, 0, background_width, background_height, null);
			g.drawImage(iconImage, icon_x, icon_y, background_width / 4, background_height / 4, null);
		}

	}

	private void addEventListener() {
		this.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_UP) {
			icon_y = (icon_y < -10) ? -10 : icon_y - 10;
		} else if (keyCode == KeyEvent.VK_DOWN) {
			icon_y = (icon_y > 510) ? 510 : icon_y + 10;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			icon_x = (icon_x > 510) ? 510 : icon_x + 10;
		} else if (keyCode == KeyEvent.VK_LEFT) {
			icon_x = (icon_x < - 10) ? -10 : icon_x - 10;
		}
		// 로깅
		// System.out.println("x :" + icon_x);
		// System.out.println("y : " + icon_y);

		repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public static void main(String[] args) {
		new MyFrame8();
	}

}
