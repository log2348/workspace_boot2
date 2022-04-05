package ch07;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoveIcon extends JFrame implements KeyListener {

	private BufferedImage bgImage;
	private BufferedImage imgIcon;
	private String bgImageFileName = "flower.jpg";
	private String imageIconFileName = "butterfly.png";

	int xPoint = 200;
	int yPoint = 200;

	private ImagePanel imagePanel;

	public MoveIcon() {
		initData();
		setInitLayout();
		addEventListener();

	}

	private void initData() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 이미지
		try {
			bgImage = ImageIO.read(new File(bgImageFileName));
			imgIcon = ImageIO.read(new File(imageIconFileName));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
			// 프로그램 종료되는 코드
			// System.exit(0);
		}
		imagePanel = new ImagePanel();
	}

	private void setInitLayout() {
		setVisible(true);
		setResizable(false);
		add(imagePanel);
	}

	private void addEventListener() {
		this.addKeyListener(this);
	}

	private class ImagePanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
			g.drawImage(imgIcon, xPoint, yPoint, 100, 100, null);

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_UP) {
			yPoint = (yPoint < 0) ? 0 : yPoint - 10;
		} else if (keyCode == KeyEvent.VK_DOWN) {
			yPoint = (yPoint > 380) ? 380 : yPoint + 10;
		} else if (keyCode == KeyEvent.VK_LEFT) {
			xPoint = (xPoint < 0) ? 0 : xPoint - 10;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			xPoint = (xPoint > 380) ? 380 : xPoint + 10;
			System.out.println("x좌표 : " + xPoint);
			System.out.println("y좌표 : " + yPoint);
		}

		repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public static void main(String[] args) {
		new MoveIcon();
	}

}
