package ch02;

import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class ManagerGUI extends JFrame {

	private JButton insertBtn;
	private JButton updateSalesBtn;
	private JButton updateStarScoreBtn;
	private JButton deleteBtn;

	private JTabbedPane menuTab;

	private JPanel backgroundPanel;
	private JPanel updatePanel;
	private JPanel insertPanel;
	private JPanel listPanel;
	
	private JTextField txtTitle;
	private JTextField txtReleaseDate;
	private JTextField txtImageFileName;
	private JTextField txtSales;
	private JTextField txTStarScore;
	
	private ScrollPane scrollPane;
	
	private JList<String> movieList;

	public ManagerGUI() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("영화 정보 관리 프로그램");
		setSize(500, 700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		insertBtn = new JButton("등록");
		updateSalesBtn = new JButton();
		updateStarScoreBtn = new JButton();
		deleteBtn = new JButton("삭제");
		
		txtTitle = new JTextField();
		txtReleaseDate = new JTextField();

		menuTab = new JTabbedPane(JTabbedPane.TOP);

		backgroundPanel = new JPanel();
		updatePanel = new JPanel();
		insertPanel = new JPanel();
		listPanel = new JPanel();
		
		movieList = new JList<String>();
		scrollPane = new ScrollPane();

		menuTab.addTab("목록", null, listPanel, null);
		menuTab.addTab("영화 등록", null, insertPanel, null);
		menuTab.addTab("정보 수정", null, updatePanel, null);

		setVisible(true);
	}

	private void setInitLayout() {
		backgroundPanel.setLayout(null);
		updatePanel.setLayout(null);
		insertPanel.setLayout(null);
		listPanel.setLayout(null);
		
		menuTab.setBounds(0, 0, getWidth(), getHeight());
		backgroundPanel.add(menuTab);
		
		txtTitle.setBounds(250, 500, 100, 25);
		
		insertBtn.setBounds(190, 350, 85, 30);
		scrollPane.setBounds(60, 75, 370, 440);
		
		movieList.setBounds(65, 70, 350, 400);
		scrollPane.add(movieList);
		
		listPanel.add(scrollPane);
		updatePanel.add(txtTitle);
		insertPanel.add(insertBtn);
		
		
		setContentPane(backgroundPanel);
	}

	public static void main(String[] args) {
		new ManagerGUI();
	}

}
