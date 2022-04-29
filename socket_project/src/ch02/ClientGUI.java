package ch02;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
	
	private ScrollPane scrollPane;

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
	private JButton createRoomBtn;
	private JButton enterRoomBtn;
	private JButton exitRoomBtn;
	private JButton whisperBtn;

	private JTextArea outputMessage; // 전송한 텍스트 화면에 출력
	private JTextField inputMessage; // 채팅 메시지 입력란

	private JTabbedPane menuTab;

	private JList<String> totalUserList;
	private JList<String> totalRoomList;

	Vector<String> userSockets = new Vector<String>();
	Vector<String> rooms = new Vector<String>();

	private Client client;

	public ClientGUI(Client client) {
		this.client = client;

		initObject();
		setInitLayout();
		addListener();
	}

	private void initObject() {
		setTitle("MY SMALL TALK");
		setSize(500, 700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		chattingRoomPanel = new JPanel();
		waitingRoomPanel = new JPanel();

		menuTab = new JTabbedPane(JTabbedPane.TOP);
		sendBtn = new JButton("전송");
		createRoomBtn = new JButton("방 만들기");
		enterRoomBtn = new JButton("방 입장하기");
		exitRoomBtn = new JButton("방 나가기");
		logInBtn = new JButton("로그인");
		whisperBtn = new JButton("쪽지 보내기");
		
		scrollPane = new ScrollPane();

		totalUserList = new JList();
		totalRoomList = new JList();

		outputMessage = new JTextArea();
		inputMessage = new JTextField();

		totalRoomLabel = new JLabel("전체 방");
		totalUserLabel = new JLabel("전체 접속자");
		userNameLabel = new JLabel("ID : ");
		ipLabel = new JLabel("IP : ");
		portLabel = new JLabel("포트 번호 : ");

		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(null);
		mainPanel.add(menuTab);
		

		menuTab.setBounds(0, 0, getWidth(), getHeight());
		menuTab.addTab("대기실", null, waitingRoomPanel, null);
		menuTab.addTab("채팅방", null, chattingRoomPanel, null);

		logInPanel = new JPanel();
		backgroundLogInPanel = new JPanel();
		txtIp = new JTextField();
		txtPort = new JTextField();
		txtUserName = new JTextField();

		txtIp.setText(client.getIp());

		
	}

	private void setInitLayout() {
		chattingRoomPanel.setLayout(null);
		waitingRoomPanel.setLayout(null);
		backgroundLogInPanel.setLayout(null);
		logInPanel.setLayout(null);

		outputMessage.setEditable(false);

		chattingRoomPanel.add(inputMessage);
		chattingRoomPanel.add(outputMessage);
		chattingRoomPanel.add(totalUserList);
		chattingRoomPanel.add(sendBtn);
		chattingRoomPanel.add(exitRoomBtn);
		chattingRoomPanel.add(scrollPane);

		waitingRoomPanel.add(createRoomBtn);
		waitingRoomPanel.add(enterRoomBtn);
		waitingRoomPanel.add(totalRoomList);
		waitingRoomPanel.add(totalUserList);
		waitingRoomPanel.add(totalRoomLabel);
		waitingRoomPanel.add(totalUserLabel);
		waitingRoomPanel.add(whisperBtn);

		backgroundLogInPanel.add(logInPanel);
		backgroundLogInPanel.setBackground(Color.lightGray);
		waitingRoomPanel.setBackground(Color.lightGray);
		chattingRoomPanel.setBackground(Color.lightGray);

		logInPanel.add(logInBtn);
		logInPanel.add(ipLabel);
		logInPanel.add(portLabel);
		logInPanel.add(txtIp);
		logInPanel.add(txtPort);
		logInPanel.add(txtUserName);
		logInPanel.add(userNameLabel);
		
		scrollPane.add(outputMessage);
		scrollPane.setBounds(35, 105, 420, 440);

		totalRoomLabel.setBounds(250, 30, 150, 50);
		totalUserLabel.setBounds(20, 30, 150, 50);

		totalUserList.setBounds(25, 70, 200, 300);
		totalRoomList.setBounds(255, 70, 200, 300);

		inputMessage.setBounds(45, 505, 320, 25);
		outputMessage.setBounds(40, 100, 400, 400);

		sendBtn.setBounds(370, 505, 75, 25);
		exitRoomBtn.setBounds(350, 70, 90, 25);
		enterRoomBtn.setBounds(355, 450, 100, 30);
		createRoomBtn.setBounds(355, 400, 100, 30);
		logInBtn.setBounds(150, 300, 100, 30);
		whisperBtn.setBounds(130, 400, 100, 30);

		txtIp.setBounds(170, 60, 100, 20);
		txtPort.setBounds(170, 110, 100, 20);
		txtUserName.setBounds(170, 170, 100, 20);

		logInPanel.setBackground(Color.white);
		logInPanel.setBounds(40, 30, 400, 600);

		ipLabel.setBounds(70, 55, 80, 30);
		portLabel.setBounds(70, 105, 80, 30);
		userNameLabel.setBounds(70, 165, 80, 30);

		setContentPane(backgroundLogInPanel);
		setVisible(true);
	}

	private void addListener() {
		whisperBtn.addActionListener(this);
		logInBtn.addActionListener(this);
		sendBtn.addActionListener(this);
		createRoomBtn.addActionListener(this);
		enterRoomBtn.addActionListener(this);
		exitRoomBtn.addActionListener(this);
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

				setTitle("[ " + txtUserName.getText() + " ] 님의 SMALL TALK");
				setContentPane(mainPanel);
				mainPanel.updateUI();
			}

		} else if (selectedBtn == sendBtn) {
			System.out.println("채팅 전송");
			if(inputMessage.getText() != null && !inputMessage.getText().equals("")) {
				client.sendMessage("Chatting/" + client.getClientRoomTitle() + "/" + client.getUserName() + "/" + inputMessage.getText());
				inputMessage.setText(null);
			}

		} else if (selectedBtn == createRoomBtn) {
			System.out.println("방 만들기 버튼 클릭");
			String roomTitle = JOptionPane.showInputDialog("방 이름을 입력하세요.");

			if (roomTitle != null) {
				client.sendMessage("CreateRoom/" + roomTitle);
				
			} else {
				JOptionPane.showMessageDialog(null, "방 제목을 입력하세요.", "알림", JOptionPane.CLOSED_OPTION);
			}

		} else if (selectedBtn == exitRoomBtn) {
			System.out.println("방 나가기");
			client.sendMessage("ExitRoom/" + client.getClientRoomTitle());
			//outputMessage.append("[ " + client.getUserName() + " ] 님이 [" + client.getClientRoomTitle() + " ]에서 퇴장하셨습니다.\n");
			setTitle("[ " + txtUserName.getText() + " ] 님의 SMALL TALK");
			menuTab.setSelectedComponent(waitingRoomPanel);

		} else if (selectedBtn == enterRoomBtn) {
			System.out.println("방 입장 버튼 클릭");

			// 목록에 있는 방 선택해서 입장
			String selectedRoom = (String) totalRoomList.getSelectedValue();
			if(selectedRoom != null) {
				setTitle("[ " + selectedRoom + " ] 방 입니다.");
				client.sendMessage("EnterRoom/" + selectedRoom);
				client.setClientRoomTitle(selectedRoom);
				menuTab.setSelectedComponent(chattingRoomPanel);
				
			} else {
				JOptionPane.showMessageDialog(null, "방을 선택하세요.", "알림", JOptionPane.CLOSED_OPTION);
			}


		} else if (selectedBtn == whisperBtn) {
			System.out.println("귓속말 보내기");

			String selectedUser = (String) totalUserList.getSelectedValue();
			if (selectedUser != null) {
				String message = JOptionPane.showInputDialog("보낼 메시지");
				if (message != null) {
					client.sendMessage("Whisper/" + selectedUser + "@" + message);
				} else {
					JOptionPane.showMessageDialog(null, "내용을 입력하세요.", "알림", JOptionPane.CLOSED_OPTION);
				}

			} else {
				JOptionPane.showMessageDialog(null, "보낼 대상을 선택하세요.", "알림", JOptionPane.CLOSED_OPTION);
			}

		}

	}
}
