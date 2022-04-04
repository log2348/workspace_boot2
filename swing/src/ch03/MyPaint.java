package ch03;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

// 외부 클래스 (Outer class)
public class MyPaint extends JFrame {

	MyPanel myPanel;

	public MyPaint() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("직접 그려보기 연습");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myPanel = new MyPanel();

	}

	private void setInitLayout() {
		setVisible(true);
		add(myPanel);
	}

	public static void main(String[] args) {
		new MyPaint();
	}

	// 내부 클래스
	private class MyPanel extends JPanel {

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawString("안녕, Java 2D ~", 100, 200);

			g.drawLine(20, 30, 100, 100);

			g.drawRect(100, 100, 150, 150); // 사각형

		}

	} // end of inner class
} // end of class
