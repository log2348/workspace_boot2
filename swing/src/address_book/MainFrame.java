package address_book;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;

	BorderLayout borderLayout;
	FlowLayout flowLayout;

	private JPanel panel;

	public MainFrame() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("주소록 메인");
		setSize(500, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		button1 = new JButton("주소록 전체");
		button2 = new JButton("친구");
		button3 = new JButton("회사");
		button4 = new JButton("학교");
		button5 = new JButton("가족");
		button6 = new JButton("추가");

		borderLayout = new BorderLayout();
		flowLayout = new FlowLayout(FlowLayout.RIGHT, 0, 0);

		panel = new JPanel();
		panel.setSize(500, 100);
		panel.setBackground(Color.DARK_GRAY);

	}

	private void setInitLayout() {
		setVisible(true);
		setResizable(false);

		add(panel);
		add(button1, borderLayout.NORTH);
		add(button6, borderLayout.SOUTH);

		panel.add(button2, flowLayout);
		panel.add(button3, flowLayout);
		panel.add(button4, flowLayout);
		panel.add(button5, flowLayout);

	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
