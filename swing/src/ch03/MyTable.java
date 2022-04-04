package ch03;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyTable extends JFrame{
	
	private JTable table = new JTable(5, 3);
	
	private final String[] titles = { "이름", "전화번호", "비고"};
	private JTextField[] contents = new JTextField[5];
	
	private JScrollPane jScrollPane = new JScrollPane();

	public MyTable() {
		
	}
	
	private void initData() {
		setTitle("My Table");
	}
	
	private void setInitLayout() {
		setVisible(true);
		setResizable(false);
		
	}
	
}
