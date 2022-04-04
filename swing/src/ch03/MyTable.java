package ch03;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyTable extends JFrame {

	private JTable table;

	private final String[] titles = { "이름", "전화번호", "비고" };
	private String[][] contents = {
			{ "박지현1", "010-0000-0000", ""},
			{ "박지현2", "010-0000-0000", ""},
			{ "박지현3", "010-0000-0000", ""}
			};

	private JScrollPane jScrollPane = new JScrollPane();

	public MyTable() {
		initData();
		setInitLayout();

	}

	private void initData() {
		table = new JTable(contents, titles); // (contents, header)
		JScrollPane jsp = new JScrollPane(table);

		add(jsp);
		setTitle("JTable 연습");
		setSize(500, 200);

	}

	private void setInitLayout() {
		setVisible(true);
		setResizable(false);

	}

	public static void main(String[] args) {
		new MyTable();
	}

}
