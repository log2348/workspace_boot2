package socket_pratice.clone;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame implements ActionListener {

	private JPanel backgroundPanel;
	private JPanel loginPanel;
	private JPanel waitingRoomPanel;
	private JTabbedPane Jtab;
	private JTextField inputHostIp;
	private JTextField inputportNumber;
	private JTextField inputUsername;
	private JTextField inputChatting;
	private JTextArea showChatting;
	private JLabel hostIPLabel;
	private JLabel portLabel;
	private JLabel usernameLabel;
	private JButton connectBtn;
	private JButton sendMessageBtn;
	private JButton sendNoteBtn;
	private JButton joinRoomBtn;
	private JButton makeRoomBtn;
	private JButton exitRoomBtn;
	private JButton endBtn;
	private JList totalUserList;
	private JList totalChattingRoomList;

	private Socket socket;
	private String ip;
	private int port;
	private String username;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	private Vector<String> user_Vclist = new Vector<String>();
	private Vector<String> roomList_vc = new Vector<String>();
	private StringTokenizer st;
	private String my_roomName;

	public Client() {
		initData();
		addListener();
	}

	private void initData() {
		
		setBounds(100, 100, 474, 483);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backgroundPanel = new JPanel();
		backgroundPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		backgroundPanel.setLayout(null);

		setContentPane(backgroundPanel);
	
		Jtab = new JTabbedPane(JTabbedPane.TOP);
		Jtab.setBounds(12, 27, 328, 407);
		backgroundPanel.add(Jtab);

		loginPanel = new JPanel();
		Jtab.addTab("로그인", null, loginPanel, null);
		loginPanel.setLayout(null);

		hostIPLabel = new JLabel("Host_IP ");
		hostIPLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		hostIPLabel.setBounds(12, 25, 91, 15);
		loginPanel.add(hostIPLabel);

		inputHostIp = new JTextField();
		inputHostIp.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		inputHostIp.setBounds(112, 21, 199, 21);
		loginPanel.add(inputHostIp);
		inputHostIp.setColumns(10);

		portLabel = new JLabel("Server_Port");
		portLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		portLabel.setBounds(12, 72, 91, 15);
		loginPanel.add(portLabel);

		inputportNumber = new JTextField();
		inputportNumber.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		inputportNumber.setBounds(112, 69, 199, 21);
		loginPanel.add(inputportNumber);
		inputportNumber.setColumns(10);

		usernameLabel = new JLabel("username");
		usernameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		usernameLabel.setBounds(12, 122, 91, 15);
		loginPanel.add(usernameLabel);

		inputUsername = new JTextField();
		inputUsername.setBounds(112, 119, 199, 21);
		inputUsername.setColumns(10);
		loginPanel.add(inputUsername);

		JLabel img_lbl = new JLabel("input the image");
		img_lbl.setIcon(new ImageIcon());
		img_lbl.setBounds(12, 213, 299, 155);
		loginPanel.add(img_lbl);

		connectBtn = new JButton("connect");
		connectBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		connectBtn.setBounds(214, 162, 97, 23);
		loginPanel.add(connectBtn);

		waitingRoomPanel = new JPanel();
		Jtab.addTab("대기실", null, waitingRoomPanel, null);
		waitingRoomPanel.setLayout(null);

		JLabel totalList_lbl = new JLabel("전체 접속자");
		totalList_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		totalList_lbl.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		totalList_lbl.setBounds(12, 28, 102, 15);
		waitingRoomPanel.add(totalList_lbl);

		JLabel roomList_lbl = new JLabel("채팅방 목록");
		roomList_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		roomList_lbl.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		roomList_lbl.setBounds(209, 27, 102, 15);
		waitingRoomPanel.add(roomList_lbl);

		totalUserList = new JList();
		totalUserList.setBounds(12, 69, 102, 257);
		waitingRoomPanel.add(totalUserList);

		totalChattingRoomList = new JList();
		totalChattingRoomList.setBounds(209, 69, 102, 257);
		waitingRoomPanel.add(totalChattingRoomList);

		sendNoteBtn = new JButton("쪽지 보내기");
		sendNoteBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		sendNoteBtn.setBounds(12, 345, 102, 23);
		waitingRoomPanel.add(sendNoteBtn);

		joinRoomBtn = new JButton("채팅방 참여");
		joinRoomBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		joinRoomBtn.setBounds(209, 345, 102, 23);
		waitingRoomPanel.add(joinRoomBtn);
		inputHostIp.setText("127.0.0.1");

		JPanel panel_2 = new JPanel();
		Jtab.addTab("채팅", null, panel_2, null);
		panel_2.setLayout(null);

		showChatting = new JTextArea();
		showChatting.setEnabled(false);
		showChatting.setEditable(false);
		showChatting.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		showChatting.setBounds(0, 0, 323, 337);
		panel_2.add(showChatting);

		inputChatting = new JTextField();
		inputChatting.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		inputChatting.setBounds(0, 347, 214, 21);
		panel_2.add(inputChatting);
		inputChatting.setColumns(10);

		sendMessageBtn = new JButton("전 송");
		sendMessageBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		sendMessageBtn.setBounds(226, 346, 97, 23);
		panel_2.add(sendMessageBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(0, 0, 323, 337);
		panel_2.add(scrollPane);

		makeRoomBtn = new JButton("방 만들기");
		makeRoomBtn.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		makeRoomBtn.setBounds(352, 93, 97, 23);
		backgroundPanel.add(makeRoomBtn);

		exitRoomBtn = new JButton("방 나가기");
		exitRoomBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		exitRoomBtn.setBounds(352, 150, 97, 23);
		backgroundPanel.add(exitRoomBtn);
		exitRoomBtn.setEnabled(false);
		endBtn = new JButton("종료");
		endBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		endBtn.setBounds(352, 398, 97, 23);
		backgroundPanel.add(endBtn);
		setVisible(true);

	}

	private void connectServer() {
		try {
			// 서버에 접속합니다.
			socket = new Socket(ip, port);
			network();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "연결실패!", "알림", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "연결실패!", "알림", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void network() {

		try {
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			os = socket.getOutputStream();
			dos = new DataOutputStream(os);

			username = inputUsername.getText().trim();
			sendmessage(username);

			// 벡터에 유저의 id 를 저장하고 리스트 화면에 추가시켜준다.
			user_Vclist.add(username);
			totalUserList.setListData(user_Vclist);

			Thread cth = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							// 서버로부터 수신된 메세지
							String msg = dis.readUTF();
							inmessage(msg); // 서버로부터 메시지 받아서 문자열 비교해서 기능 처리
						} catch (IOException e) {
							try {
								user_Vclist.removeAll(user_Vclist);
								roomList_vc.removeAll(roomList_vc);
								totalUserList.setListData(user_Vclist);
								totalChattingRoomList.setListData(roomList_vc);
								showChatting.setText("\n");
								is.close();
								os.close();
								dis.close();
								dos.close();
								socket.close();
								JOptionPane.showMessageDialog(null, "서버가 종료됨!", "알림", JOptionPane.ERROR_MESSAGE);
								break;
							} catch (Exception e2) {
								return;
							}
						}
					}
				}
			});
			cth.start();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "연결실패!", "알림", JOptionPane.ERROR_MESSAGE);
		} // Stream 준비완료
		connectBtn.setEnabled(false);
	}

	private void inmessage(String str) {

		st = new StringTokenizer(str, "/");

		String protocol = st.nextToken();
		String message = st.nextToken();

		System.out.println("프로토콜" + protocol);
		System.out.println("메세지" + message);

		if (protocol.equals("NewUser")) {
			user_Vclist.add(message);
			totalUserList.setListData(user_Vclist);
		} else if (protocol.equals("OldUser")) {
			user_Vclist.add(message);
			totalUserList.setListData(user_Vclist);
		} else if (protocol.equals("Note")) {
			st = new StringTokenizer(message, "@");
			String user = st.nextToken();
			String note = st.nextToken();
			JOptionPane.showMessageDialog(null, note, user + "로 부터 온 메세지", JOptionPane.CLOSED_OPTION);
		} else if (protocol.equals("CreateRoom")) {
			// 방만들기가 성공했을 경우
			my_roomName = message;
			joinRoomBtn.setEnabled(false);
			exitRoomBtn.setEnabled(true);
			makeRoomBtn.setEnabled(false);
		} else if (protocol.equals("CreateRoomFail")) {
			JOptionPane.showMessageDialog(null, "같은 방 이름이 존재합니다.!", "알림", JOptionPane.ERROR_MESSAGE);
		} else if (protocol.equals("new_Room")) {
			roomList_vc.add(message);
			totalChattingRoomList.setListData(roomList_vc);
		} else if (protocol.equals("Chatting")) {
			String msg = st.nextToken();
			showChatting.append(message + " : " + msg + "\n");
		} else if (protocol.equals("OldRoom")) {
			roomList_vc.add(message);
			totalChattingRoomList.setListData(roomList_vc);
		} else if (protocol.equals("JoinRoom")) {
			my_roomName = message;
			JOptionPane.showMessageDialog(null, "채팅방 (  " + my_roomName + " ) 에 입장완료", "알림",
					JOptionPane.INFORMATION_MESSAGE);
			showChatting.setText("");
		} else if (protocol.equals("UserOut")) {
			user_Vclist.remove(message);
			sendmessage("OutRoom/" + my_roomName);
		} else if (protocol.equals("UserData_Updata")) {
			totalUserList.setListData(user_Vclist);
			totalChattingRoomList.setListData(roomList_vc);
		} else if (protocol.equals("OutRoom")) {
			showChatting.append("*** (( " + my_roomName + "에서 퇴장 ))***\n");
			my_roomName = null;
			makeRoomBtn.setEnabled(true);
			exitRoomBtn.setEnabled(false);
		} else if (protocol.equals("EmptyRoom")) {
			roomList_vc.remove(message);
			// 클라이언트가 강제 종료 되었고 방이 비었을때 방 목록에서 그 방을 없애준다.
		} else if (protocol.equals("ErrorOutRoom")) {
			roomList_vc.remove(message);
		}
	}

	private void sendmessage(String msg) {
		try {
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addListener() {
		connectBtn.addActionListener(this);
		sendMessageBtn.addActionListener(this);
		sendNoteBtn.addActionListener(this);
		joinRoomBtn.addActionListener(this);
		inputChatting.addActionListener(this);
		endBtn.addActionListener(this);
		makeRoomBtn.addActionListener(this);
		exitRoomBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == connectBtn) {
			if (inputHostIp.getText().length() == 0) {
				inputHostIp.setText("IP를 입력하세요");
				inputHostIp.requestFocus();
			} else if (inputportNumber.getText().length() == 0) {
				inputportNumber.setText("포트번호를 입력하세요");
				inputportNumber.requestFocus();
			} else if (inputUsername.getText().length() == 0) {
				inputUsername.setText("id 를 입력하세요");
				inputUsername.requestFocus();
			} else {
				ip = inputHostIp.getText();
				try {
					port = Integer.parseInt(inputportNumber.getText().trim());
				} catch (Exception e2) {
					inputportNumber.setText("잘못 입력하였습니다.");
				}
				username = inputUsername.getText().trim();
				// 서버 연결
				connectServer();
				setTitle("[" + username + " ] 님 깨알톡에 오신걸 환경합니다.");
			}
		} else if (e.getSource() == sendMessageBtn) {
			System.out.println("전송");
			sendmessage("Chatting/" + my_roomName + "/" + inputChatting.getText().trim());
		} else if (e.getSource() == sendNoteBtn) {
			System.out.println("쪽지 보내기");
			String user = (String) totalUserList.getSelectedValue();

			if (user != null) {
				String note = JOptionPane.showInputDialog("보낼메세지");
				if (note != null) {
					sendmessage("Note/" + user + "@" + note);
				}
			} else {
				JOptionPane.showMessageDialog(null, "대상을 선택하세요", "알림", JOptionPane.ERROR_MESSAGE);

			}
		} else if (e.getSource() == joinRoomBtn) {
			System.out.println("방입장버튼 클릭");
			String joinRoom = (String) totalChattingRoomList.getSelectedValue();
			exitRoomBtn.setEnabled(true);
			makeRoomBtn.setEnabled(false);
			sendmessage("JoinRoom/" + joinRoom);
		} else if (e.getSource() == inputChatting) {
			if (inputChatting.getText().length() == 0) {
				System.out.println("이게 0값으로 들어가나?"); // 아무것도 입력안하고 버튼 클릭시 예외 발생(NoSuchElementException)
				sendmessage("Chatting/" + my_roomName + "/" + inputChatting.getText() + "   ");
			} else {
				sendmessage("Chatting/" + my_roomName + "/" + inputChatting.getText());
			}
		} else if (e.getSource() == makeRoomBtn) {
			System.out.println("방 만들기");
			String roomName = JOptionPane.showInputDialog("방 이름을 입력하세요");
			if (roomName != null) {
				sendmessage("CreateRoom/" + roomName);
			}
		} else if (e.getSource() == exitRoomBtn) {
			System.out.println("방 나가기");
			sendmessage("OutRoom/" + my_roomName);
		} else if (e.getSource() == endBtn) {
			System.exit(0);
		}
		inputChatting.setText("");
	}

	public static void main(String[] args) {
		new Client();
	}
}
