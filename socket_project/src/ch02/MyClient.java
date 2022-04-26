package ch02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyClient extends JFrame implements ActionListener {

	private Socket socket;
	private String ip = "127.0.0.1";
	private int port;

	private InputStream inputStream;
	private OutputStream outputStream;
	private DataInputStream dataInputStream; // 따로 가공하지 않아도됨
	private DataOutputStream dataOutputStream;

	private String userName;
	private String roomTitle;

	private JButton logInBtn;
	private JButton sendBtn;
	private JButton makeRoomBtn;
	private JButton enterRoomBtn;
	private JButton outRoomBtn;

	private JLabel ipLabel;
	private JLabel portLabel;
	private JLabel userNameLabel;
	private JLabel totalUserLabel;
	private JLabel totalRoomLabel;
	private JTextArea outputMessage; // 전송한 텍스트 화면에 출력
	private JTextField inputMessage; // 채팅 메시지 입력란
	private JTextField txtIp;
	private JTextField txtPort;
	private JTextField txtUserName;
	private JTextField txtRoomTitle;
	private JPanel logInPanel; // 로그인 창
	private JPanel mainPanel;

	private JPanel makeRoomPanel;
	private JPanel chatPanel; // 채팅 창

	private JList totalUserList;
	private JList totalRoomList;

	Vector<String> users = new Vector<String>();
	Vector<String> rooms = new Vector<String>();

	StringTokenizer stringTokenizer;

	public MyClient() {
		initData();
		initLayout();
		addListener();
	}

	private void initData() {
		setTitle("My Small Talk");
		setSize(500, 700);
		setVisible(true);
		// setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		logInPanel = new JPanel();
		chatPanel = new JPanel();
		makeRoomPanel = new JPanel();

		ipLabel = new JLabel("IP : ");
		portLabel = new JLabel("포트 번호 : ");
		userNameLabel = new JLabel("ID : ");
		txtIp = new JTextField();
		txtPort = new JTextField();

		totalUserList = new JList();
		totalRoomList = new JList();
		
		totalUserList.setBounds(20, 70, 200, 300);
		totalRoomList.setBounds(270, 70, 200, 300);
		
		totalRoomLabel = new JLabel("전체 방");
		totalRoomLabel.setBounds(170, 30, 150, 50);
		
		totalUserLabel = new JLabel("전체 접속자");
		totalUserLabel.setBounds(20, 30, 150, 50);	

		txtIp.setText(ip);

		outputMessage = new JTextArea();
		inputMessage = new JTextField();
		txtUserName = new JTextField();
		txtRoomTitle = new JTextField();

		logInBtn = new JButton("로그인");
		sendBtn = new JButton("전송");
		makeRoomBtn = new JButton("방 생성");
		enterRoomBtn = new JButton("방 입장");
		outRoomBtn = new JButton("방 나가기");

	}

	private void initLayout() {
		logInPanel.setLayout(null);
		chatPanel.setLayout(null);
		makeRoomPanel.setLayout(null);

		logInBtn.setBounds(180, 300, 100, 30);
		ipLabel.setBounds(50, 50, 70, 30);
		portLabel.setBounds(50, 100, 70, 30);
		userNameLabel.setBounds(50, 150, 70, 30);
		txtIp.setBounds(150, 50, 70, 20);
		txtPort.setBounds(150, 100, 70, 20);
		txtUserName.setBounds(150, 150, 70, 20);

		inputMessage.setBounds(30, 500, 330, 25);
		outputMessage.setBounds(30, 100, 400, 400);
		sendBtn.setBounds(370, 500, 70, 30);
		outRoomBtn.setBounds(370, 600, 100, 30);
		
		enterRoomBtn.setBounds(350, 450, 100, 30);
		makeRoomBtn.setBounds(350, 400, 100, 30);
		
		txtRoomTitle.setBounds(20, 320, 400, 25);

		logInPanel.add(logInBtn);
		logInPanel.add(ipLabel);
		logInPanel.add(portLabel);
		logInPanel.add(txtIp);
		logInPanel.add(txtPort);
		logInPanel.add(txtUserName);
		logInPanel.add(userNameLabel);

		chatPanel.add(inputMessage);
		chatPanel.add(outputMessage);
		chatPanel.add(totalUserList);
		chatPanel.add(sendBtn);

		makeRoomPanel.add(txtRoomTitle);
		makeRoomPanel.add(makeRoomBtn);
		makeRoomPanel.add(enterRoomBtn);
		makeRoomPanel.add(totalRoomList);
		makeRoomPanel.add(totalUserList);
		
		makeRoomPanel.add(totalRoomLabel);
		makeRoomPanel.add(totalUserLabel);

		chatPanel.add(outRoomBtn);

		setContentPane(logInPanel);
	}

	private void connectServer() {
		try {
			// 클라이언트 소켓 생성
			socket = new Socket(ip, port);
			network();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("연결 실패");
		}
	}

	private void network() {
		try {
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);

			userName = txtUserName.getText().trim();
			sendMessage(userName);
			
			// 벡터에 유저 추가하고 리스트(화면) 추가
			users.add(userName);
			totalUserList.setListData(users);

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						String msg = dataInputStream.readUTF();
						receiveMessage(msg);
					} catch (Exception e) {
						e.printStackTrace();

					}

				}
			}).start();

		} catch (Exception e) {
			System.out.println("연결 실패");
		}
	}

	private void sendMessage(String msg) {
		try {
			dataOutputStream.writeUTF(msg);
			dataOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 프로토콜로 기능 처리
	private void receiveMessage(String msg) {

		stringTokenizer = new StringTokenizer(msg, "/");

		String protocol = stringTokenizer.nextToken();
		String message = stringTokenizer.nextToken();

		System.out.println("프로토콜" + protocol);
		System.out.println("메세지" + message);

		if (protocol.equals("NewUser")) {

		} else if (protocol.equals("OldUser")) {

		} else if (protocol.equals("Wisper")) {
			stringTokenizer = new StringTokenizer(message, "@");
			String userName = stringTokenizer.nextToken();
			String content = stringTokenizer.nextToken();
			JOptionPane.showMessageDialog(null, content, userName + "로부터 온 메시지", JOptionPane.CLOSED_OPTION);
		} else if (protocol.equals("CreateRoom")) {

		} else if (protocol.equals("CreateRoomFail")) {
			JOptionPane.showMessageDialog(null, "같은 방 이름이 존재합니다.!", "알림", JOptionPane.ERROR_MESSAGE);
		} else if (protocol.equals("NewRoom")) {

		} else if (protocol.equals("Chatting")) {

		} else if (protocol.equals("JoinRoom")) {
			roomTitle = message;
			JOptionPane.showMessageDialog(null, "채팅방 (  " + roomTitle + " ) 에 입장완료", "알림",
					JOptionPane.INFORMATION_MESSAGE);
			outputMessage.setText("");
		} else if (protocol.equals("UserOut")) {

		} else if (protocol.equals("OutRoom")) {

		} else if (protocol.equals("EmptyRoom")) {

		} else if (protocol.equals("ErrorOutRoom")) {

		}
	}

	private void addListener() {
		logInBtn.addActionListener(this);
		sendBtn.addActionListener(this);
		inputMessage.addActionListener(this);
		makeRoomBtn.addActionListener(this);
		outRoomBtn.addActionListener(this);
		enterRoomBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton selectedBtn = (JButton) e.getSource();

		if (selectedBtn == logInBtn) { // 서버 시작
			System.out.println("로그인 버튼 클릭");
			if (txtIp.getText().length() == 0) {
				System.out.println("ip 주소를 입력하세요.");
				txtIp.requestFocus();
				JOptionPane.showMessageDialog(null, "ip 주소를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
			} else if (txtPort.getText().length() == 0) {
				txtPort.requestFocus();
				JOptionPane.showMessageDialog(null, "포트 번호를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
			} else if (txtUserName.getText().length() == 0) {
				txtUserName.requestFocus();
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
			} else {
				ip = txtIp.getText();
				port = Integer.parseInt(txtPort.getText());

				connectServer();
				System.out.println("클라이언트 연결됨");
				setTitle("[ " + txtUserName.getText() + " ] 님의 My Small Talk");
				setContentPane(makeRoomPanel);
				makeRoomPanel.updateUI();
			}
		} else if (selectedBtn == sendBtn) {
			System.out.println("클라이언트가 메시지 전송");
			sendMessage("Chatting/" + roomTitle + "/" + inputMessage.getText().trim());
			outputMessage.append(userName + " : " + inputMessage.getText() + "\n");
			inputMessage.setText(null);
		} else if (selectedBtn == makeRoomBtn) {
			System.out.println("방 만들기 버튼 클릭");
			String roomTitle = txtRoomTitle.getText();
			rooms.add(roomTitle);
			totalRoomList.setListData(rooms);
			txtRoomTitle.setText(null);
			JOptionPane.showMessageDialog(null, "방 생성 완료", "알림", JOptionPane.CLOSED_OPTION);
			setTitle("[ " + roomTitle + " ] 입장");
			if (roomTitle != null) {
				sendMessage("CreateRoom/" + roomTitle);
				chatPanel.updateUI();
			}
		} else if (selectedBtn == outRoomBtn) {
			System.out.println("방 나가기");
			sendMessage("OutRoom/" + roomTitle);
			setContentPane(makeRoomPanel);
		} else if (selectedBtn == enterRoomBtn) {
			System.out.println("방 입장");
			String joinRoom = (String) totalRoomList.getSelectedValue();
			sendMessage("JoinRoom/" + joinRoom);
			setContentPane(chatPanel);
		}

	}

	public static void main(String[] args) {
		new MyClient();
	}

}
