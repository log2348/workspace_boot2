package ch02;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientGUI extends JFrame implements ActionListener {

	private JPanel backgroundLogInPanel; // 로그인 창 배경
	private JPanel logInPanel;
	private JPanel mainPanel;
	private JPanel waitingRoomPanel;
	private JPanel chattingRoomPanel; // 채팅 창

	private JButton logInBtn;
	private JTextField txtIp;
	private JTextField txtPort;
	private JTextField txtUserName;

	private JLabel ipLabel;
	private JLabel portLabel;
	private JLabel userNameLabel;
	private JLabel totalUserLabel;
	private JLabel totalRoomLabel;

	private JButton sendBtn;
	private JButton makeRoomBtn;
	private JButton enterRoomBtn;
	private JButton ExitRoomBtn;

	private JTextArea outputMessage; // 전송한 텍스트 화면에 출력
	private JTextField inputMessage; // 채팅 메시지 입력란

	private JTabbedPane menuTab;

	private JList totalUserList;
	private JList totalRoomList;

	private Client client;

	public ClientGUI(Client client) {
		this.client = client;

		initObject();
		setInitLayout();
		addListener();
	}

	private void initObject() {
		setTitle("My Small Talk");
		setSize(500, 700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		chattingRoomPanel = new JPanel();
		waitingRoomPanel = new JPanel();

		menuTab = new JTabbedPane(JTabbedPane.TOP);
		sendBtn = new JButton("전송");
		makeRoomBtn = new JButton("방 생성");
		enterRoomBtn = new JButton("방 입장");
		ExitRoomBtn = new JButton("방 나가기");
		logInBtn = new JButton("로그인");

		totalUserList = new JList();
		totalRoomList = new JList();

		outputMessage = new JTextArea();
		inputMessage = new JTextField();

		totalRoomLabel = new JLabel("전체 방");
		totalUserLabel = new JLabel("전체 접속자");
		userNameLabel = new JLabel("ID : ");
		ipLabel = new JLabel("IP : ");
		portLabel = new JLabel("포트 번호 : ");

		menuTab.setBounds(0, 0, getWidth(), getHeight());
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(null);
		mainPanel.add(menuTab);

		menuTab.addTab("대기실", null, waitingRoomPanel, null);
		menuTab.addTab("채팅방", null, chattingRoomPanel, null);

		logInPanel = new JPanel();
		backgroundLogInPanel = new JPanel();
		txtIp = new JTextField();
		txtPort = new JTextField();
		txtUserName = new JTextField();

		txtIp.setText(client.getIp());

		setVisible(true);
	}

	private void setInitLayout() {
		chattingRoomPanel.setLayout(null);
		waitingRoomPanel.setLayout(null);
		
		outputMessage.setEditable(false);

		chattingRoomPanel.add(inputMessage);
		chattingRoomPanel.add(outputMessage);
		chattingRoomPanel.add(totalUserList);
		chattingRoomPanel.add(sendBtn);

		waitingRoomPanel.add(makeRoomBtn);
		waitingRoomPanel.add(enterRoomBtn);
		waitingRoomPanel.add(totalRoomList);
		waitingRoomPanel.add(totalUserList);

		waitingRoomPanel.add(totalRoomLabel);
		waitingRoomPanel.add(totalUserLabel);

		chattingRoomPanel.add(ExitRoomBtn);

		backgroundLogInPanel.add(logInPanel);
		backgroundLogInPanel.setLayout(null);
		backgroundLogInPanel.setBackground(Color.lightGray);

		logInPanel.add(logInBtn);
		logInPanel.add(ipLabel);
		logInPanel.add(portLabel);
		logInPanel.add(txtIp);
		logInPanel.add(txtPort);
		logInPanel.add(txtUserName);
		logInPanel.add(userNameLabel);

		totalRoomLabel.setBounds(270, 30, 150, 50);
		totalUserLabel.setBounds(20, 30, 150, 50);

		totalUserList.setBounds(20, 70, 200, 300);
		totalRoomList.setBounds(270, 70, 200, 300);

		inputMessage.setBounds(30, 500, 330, 25);
		outputMessage.setBounds(30, 100, 400, 400);

		sendBtn.setBounds(370, 500, 70, 30);
		ExitRoomBtn.setBounds(370, 600, 100, 30);
		enterRoomBtn.setBounds(350, 450, 100, 30);
		makeRoomBtn.setBounds(350, 400, 100, 30);
		logInBtn.setBounds(150, 300, 100, 30);

		txtIp.setBounds(170, 60, 100, 20);
		txtPort.setBounds(170, 110, 100, 20);
		txtUserName.setBounds(170, 170, 100, 20);

		logInPanel.setBackground(Color.white);
		logInPanel.setLayout(null);
		logInPanel.setBounds(40, 30, 400, 600);

		ipLabel.setBounds(70, 55, 80, 30);
		portLabel.setBounds(70, 105, 80, 30);
		userNameLabel.setBounds(70, 165, 80, 30);

		setContentPane(backgroundLogInPanel);

	}

	private void addListener() {
		logInBtn.addActionListener(this);
		sendBtn.addActionListener(this);
		makeRoomBtn.addActionListener(this);
		enterRoomBtn.addActionListener(this);
		ExitRoomBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedBtn = (JButton) e.getSource();

		if (selectedBtn == logInBtn) {
			System.out.println("로그인 버튼 클릭");

			if (txtIp.getText().length() == 0) {
				txtIp.requestFocus();
				JOptionPane.showMessageDialog(null, "IP 주소를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);

			} else if (txtPort.getText().length() == 0) {
				txtPort.requestFocus();
				JOptionPane.showMessageDialog(null, "포트 번호를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);

			} else if (txtUserName.getText().length() == 0) {
				txtUserName.requestFocus();
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);

			} else {
				client.setIp(txtIp.getText());
				client.setPort(Integer.parseInt(txtPort.getText()));
				client.setUserName(txtUserName.getText().trim());
				client.connectServer();
				
				outputMessage.append("[ " +client.getUserName() + " ] 님이 입장하셨습니다.\n");
				setTitle("[ " + txtUserName.getText() + " ] 님의 My Small Talk");
				setContentPane(mainPanel);
				mainPanel.updateUI();
			}

		} else if (selectedBtn == sendBtn) {
			System.out.println("클라이언트가 메시지 전송");
			client.sendMessage("Chatting/" + client.getRoomTitle() + "/" + inputMessage.getText().trim());
			outputMessage.append(client.getUserName() + " : " + inputMessage.getText() + "\n");
			inputMessage.setText(null);

		} else if (selectedBtn == makeRoomBtn) {
			System.out.println("방 만들기 버튼 클릭");
			String roomTitle = JOptionPane.showInputDialog("방 이름을 입력하세요.");
			client.rooms.add(roomTitle);
			totalRoomList.setListData(client.rooms);
			JOptionPane.showMessageDialog(null, "방 생성 완료", "알림", JOptionPane.CLOSED_OPTION);
			setTitle("[ " + roomTitle + " ] 입장");

			if (roomTitle != null) {
				client.sendMessage("CreateRoom/" + roomTitle);
			}

		} else if (selectedBtn == ExitRoomBtn) {
			System.out.println("방 나가기");
			client.sendMessage("ExitRoom/" + client.getRoomTitle());
			//outputMessage.append("[ " + client.getUserName() + " ] 님이 " + client.getRoomTitle() + "에서 퇴장하셨습니다.\n");

		} else if (selectedBtn == enterRoomBtn) {
			System.out.println("방 입장 버튼 클릭");
			
			// 목록에 있는 방 선택해서 입장
			String enterRoom = (String) totalRoomList.getSelectedValue();
			client.sendMessage("EnterRoom/" + enterRoom);
			
			JOptionPane.showMessageDialog(null, "채팅방 [  " + enterRoom + " ] 에 입장완료", "알림",
					JOptionPane.INFORMATION_MESSAGE);

		}

	}
}
