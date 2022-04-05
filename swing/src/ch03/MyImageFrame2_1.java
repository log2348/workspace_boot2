package ch03;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyImageFrame2_1 extends JFrame {

	// 코드를 조금 수정해주세요
	// 상수 , 스태이틱 활요해서 처리해주세요

	private BufferedImage backgroudImage;
	private BufferedImage imageIcon;
	private MyImagePanel myImagePanel;
	private JButton button;

	public MyImageFrame2_1() {
		initData();
		setInitLayot();
	}

	private void initData() {
		setTitle("이미지 백그라운드 연습");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			backgroudImage = ImageIO.read(new File("image1.jpg"));
			imageIcon = ImageIO.read(new File("icon2.png"));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}

		myImagePanel = new MyImagePanel();

	}

	private void setInitLayot() {
		setVisible(true);
		setResizable(false);
		add(myImagePanel);
	}

	private class MyImagePanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// 이미지 2개를 그려주는 기능을 완료
			g.drawImage(backgroudImage, 0, 0, 500, 500, null);
			g.drawImage(imageIcon, 0, 0, 100, 100, null);

		}
	} // end of inner class

	// 메인 함수
	public static void main(String[] args) {
		new MyImageFrame2_1();

	} // end of main

} // end of outer class