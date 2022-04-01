package ch02;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutEx extends JFrame {
	
	// 배열 또는 ArrayList로 변경
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	
	private GridLayout gridLayout;
	
	public GridLayoutEx() {
		initData();
		setInitLayout();
	}
	
	private void initData() {
		button1 = new JButton("가");
		button2 = new JButton("나");
		button3 = new JButton("다");
		button4 = new JButton("라");
		button5 = new JButton("마");

		buttons.add(button1);
		buttons.add(button2);
		buttons.add(button3);
		buttons.add(button4);
		buttons.add(button5);
		
		gridLayout = new GridLayout(3, 2); // 행, 열

	}
	
	private void setInitLayout() {
		setVisible(true);
		setLayout(gridLayout);
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
	}
	
	public static void main(String[] args) {
		new GridLayoutEx();
	}

}
