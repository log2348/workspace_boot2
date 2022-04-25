package ch01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

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

	private DataInputStream dataInputStream; // 따로 가공하지 않아도됨
	private DataOutputStream dataOutputStream;
	// String형으로 버퍼에 저장
	private BufferedWriter bufferedWriter; // 유저가 입력한 데이터 출력
	private BufferedReader keyboardReader; // 유저가 키보드에 입력한 데이터 읽기
	private BufferedReader bufferedReader; // 유저가 입력한 데이터 읽기

	private JButton logInBtn;
	private JButton sendBtn;
	private JLabel ipLabel;
	private JLabel portLabel;
	private JLabel userNameLabel;
	private JTextArea outputMessage; // 전송한 텍스트 화면에 출력
	private JTextField inputMessage; // 채팅 메시지 입력란
	private JTextField txtIp;
	private JTextField txtPort;
	private JTextField txtUserName;
	private JTextField txtRoomTitle;
	private JLabel logInPanel; // 로그인 창
	private JLabel chatPanel; // 채팅 창
	private JList<User> totalUserList;
	private JList<ChattingRoom> totalRoomList;

	private User user;

	public MyClient() {
		initData();
		initLayout();
		addListener();
	}

	private void initData() {
		setTitle("My Talk");
		setSize(500, 700);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		logInPanel = new JLabel();
		chatPanel = new JLabel();

		ipLabel = new JLabel("IP : ");
		portLabel = new JLabel("포트 번호 : ");
		userNameLabel = new JLabel("ID : ");
		txtIp = new JTextField();
		txtPort = new JTextField();

		txtIp.setText(ip);

		outputMessage = new JTextArea();
		inputMessage = new JTextField();
		txtUserName = new JTextField();
		txtRoomTitle = new JTextField();

		logInBtn = new JButton("로그인");
		sendBtn = new JButton("전송");

		totalRoomList = new JList<ChattingRoom>();
		totalUserList = new JList<User>();

	}

	private void initLayout() {
		logInPanel.setLayout(null);
		chatPanel.setLayout(null);

		logInBtn.setBounds(180, 300, 100, 30);
		sendBtn.setBounds(10, 400, 100, 30);
		ipLabel.setBounds(50, 50, 70, 30);
		portLabel.setBounds(50, 100, 70, 30);
		userNameLabel.setBounds(50, 150, 70, 30);
		txtIp.setBounds(150, 50, 70, 20);
		txtPort.setBounds(150, 100, 70, 20);
		txtUserName.setBounds(150, 150, 70, 20);
		outputMessage.setBounds(30, 100, 400, 400);
		inputMessage.setBounds(30, 500, 400, 30);

		logInPanel.add(logInBtn);
		logInPanel.add(ipLabel);
		logInPanel.add(portLabel);
		logInPanel.add(txtIp);
		logInPanel.add(txtPort);
		logInPanel.add(txtUserName);
		logInPanel.add(userNameLabel);

		chatPanel.add(inputMessage);
		chatPanel.add(outputMessage);
		chatPanel.add(totalRoomList);
		chatPanel.add(totalUserList);
		chatPanel.add(sendBtn);

		add(logInPanel);
		add(chatPanel);

		setContentPane(logInPanel);
	}

	private void connectServer() {
		try {
			// 클라이언트 소켓 생성
			socket = new Socket(ip, port);
			
			dataInputStream = new DataInputStream(socket.getInputStream());
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			
			// 유저네임 받아오기
			String userName = bufferedReader.readLine();
			
			dataOutputStream.writeUTF(userName);
			
			new Thread(new SendThread()).start();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("연결 실패");
		}
	}

	private void connectStream() {

		String userName = txtUserName.getText().trim();
		user.getUsers().add(user);
		sendMessage(userName);
		totalUserList.setListData(user.users);

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						// 서버로부터 메시지 받기
						String msg = bufferedReader.readLine();
						receiveMessage(msg);
					} catch (Exception e) {
						System.out.println("서버 종료");
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	// 서버로부터 메시지 받기
	private void receiveMessage(String msg) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						String msg = bufferedReader.readLine();
						user.receiveMessage(msg); // 프로토콜로 기능 처리
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	private void sendMessage(String msg) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			// 키보드 연결
			keyboardReader = new BufferedReader(new InputStreamReader(System.in));
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			bufferedWriter.write(msg);
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addListener() {
		logInBtn.addActionListener(this);
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
				setTitle("[ " + txtUserName.getText() + " ] 님의 My Talk");
				setContentPane(chatPanel);
				chatPanel.updateUI();
				chatPanel.add(inputMessage);
				chatPanel.add(outputMessage);
				chatPanel.add(totalRoomList);
				chatPanel.add(totalUserList);
			}
		}

	}

	public static void main(String[] args) {
		new MyClient();
	}

}
