package ch04_1;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BaseActivity extends JFrame{
	
	String name;
	JPanel panel;
	
	public BaseActivity(String name) {
		this.name = name;
		initData();
		setInitLayout();
	}
	
	protected void initData() {
		
	}
	
	protected void setInitLayout() {
		setTitle(name);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		setVisible(true);
		add(panel);
	}

}
