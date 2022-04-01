package ch02;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame2 extends JFrame {

	private BorderLayout borderLayout;
	private ArrayList<JButton> buttons = new ArrayList<>();
	private ArrayList<String> titles = new ArrayList<>();
	private ArrayList<String> directions = new ArrayList<>();

	public MyFrame2() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("BorderLayout Test");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout = new BorderLayout();

		titles.add("동");
		titles.add("서");
		titles.add("남");
		titles.add("북");
		titles.add("센터");

		directions.add(BorderLayout.NORTH);
		directions.add(BorderLayout.CENTER);
		directions.add(BorderLayout.SOUTH);
		directions.add(BorderLayout.EAST);
		directions.add(BorderLayout.WEST);

		for (int i = 0; i < titles.size(); i++) {
			buttons.add(new JButton(titles.get(i)));
		}

//		buttons.add(new JButton(titles.get(0)));
//		buttons.add(new JButton(titles.get(1)));
//		buttons.add(new JButton(titles.get(2)));
//		buttons.add(new JButton(titles.get(3)));
//		buttons.add(new JButton(titles.get(4)));

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(borderLayout);

		for (int i = 0; i < directions.size(); i++) {
			add(buttons.get(i), directions.get(i));
		}

//		add(buttons.get(0), directions.get(0));
//		add(buttons.get(1), directions.get(1));
//		add(buttons.get(2), directions.get(2));
//		add(buttons.get(3), directions.get(3));
//		add(buttons.get(4), directions.get(4));
	}

}
