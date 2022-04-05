package address_book;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddFrame extends JFrame {

	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;

	private JButton button1;

	private JPanel panel;

	GridLayout gridLayout;

	Container contents = getContentPane();

	public AddFrame() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("주소록 추가하기");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		label1 = new JLabel("이름");
		label2 = new JLabel("전화번호");
		label3 = new JLabel("주소");
		label4 = new JLabel("그룹");

		button1 = new JButton("추가하기");

		gridLayout = new GridLayout(4, 2);
		gridLayout.setVgap(10);

		panel = new JPanel();

		textField1 = new JTextField(20);
		textField2 = new JTextField(20);
		textField3 = new JTextField(20);
		textField4 = new JTextField(20);
	}

	private void setInitLayout() {
		setVisible(true);
		setResizable(false);

		contents.setLayout(gridLayout);

		contents.add(label1);
		contents.add(textField1);
		contents.add(label2);
		contents.add(textField2);
		contents.add(label3);
		contents.add(textField3);
		contents.add(label4);
		contents.add(textField4);

	}

	public static void main(String[] args) {
		new AddFrame();
	}

}
