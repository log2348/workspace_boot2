package ch03;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyHousePanel extends JPanel {

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		// 지붕
		g.drawLine(250, 250, 400, 100);
		g.drawLine(550, 250, 400, 100);
		
		// 잔디
		g.drawString("*************************************************", 550, 560);
		g.drawString("***************************************************", 0, 560);
		
		// 몸체
		g.drawRect(250, 250, 300, 300);
		
		// 창문
		g.drawRect(300, 300, 70, 50);
		g.drawRect(430, 300, 70, 50);

		// 문
		g.drawRect(430, 400, 100, 150);

	}

}

public class MyHouseFrame extends JFrame {

	MyHousePanel myHousePanel;

	public MyHouseFrame() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("집 꾸미기");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myHousePanel = new MyHousePanel();

	}

	private void setInitLayout() {
		setVisible(true);
		add(myHousePanel);

	}

	public static void main(String[] args) {
		new MyHouseFrame();
	}

}
