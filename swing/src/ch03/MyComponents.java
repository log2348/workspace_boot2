package ch03;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyComponents extends JFrame {

	// 포함관계
	private JPanel panel;
	private JButton button;
	private JLabel label;
	JTextField textField;
	private JPasswordField passwordField;
	private JCheckBox checkBox;

	public MyComponents() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("컴포넌트 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);

		panel = new JPanel();
		
		Dimension dimension = new Dimension(300, 300);
		
		panel.setPreferredSize(dimension);
		
		button = new JButton("button");
		label = new JLabel("label");
		textField = new JTextField("hint", 20);
		passwordField = new JPasswordField(20); // 20자
		checkBox = new JCheckBox("checkBox", true);
	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.LEFT, 100, 200)); // 바로 생성 가능 - 객체에 접근해서 값 지정할 일이 없다면

		add(panel);
		panel.setBackground(Color.lightGray);
		panel.add(button);
		panel.add(label);
		panel.add(textField);
		panel.add(passwordField);
		panel.add(checkBox);

	}

}
