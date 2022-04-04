package ch03;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyImageFrame2 extends JFrame {

	// 코드를 조금 수정해주세요
	// 상수, static 활용해서 처리해주세요

	private static final int X = 0;
	private static final int Y = 0;

	private static final int BACKGROUND_WIDTH = 500;
	private static final int BACKGROUND_HEIGHT = 500;

	private static int icon_width;
	private static int icon_height;

	private BufferedImage backgroundImage;
	private BufferedImage imageIcon;
	private MyImagePanel myImagePanel;

	private Scanner scanner;

	public MyImageFrame2() {
		initData();
		setInitLayout();
	}

	private void initData() {
		scanner = new Scanner(System.in);

		setTitle("이미지 백그라운드 연습");
		setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		System.out.print("백그라운드 이미지 파일명 : ");
		String imageFileName = scanner.nextLine();

		System.out.print("아이콘 파일명 : ");
		String iconFileName = scanner.nextLine();

		System.out.println("아이콘 사이즈 입력");
		System.out.print("가로 : ");
		icon_width = scanner.nextInt();
		System.out.print("세로 : ");
		icon_height = scanner.nextInt();

		scanner.close();

		try {
			backgroundImage = ImageIO.read(new File(imageFileName));
			imageIcon = ImageIO.read(new File(iconFileName));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}

		myImagePanel = new MyImagePanel();

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

			g.drawImage(backgroundImage, X, Y, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
			g.drawImage(imageIcon, X, Y, icon_width, icon_height, null);

		}
	} // end of inner class

} // end of outer class
