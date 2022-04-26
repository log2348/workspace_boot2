package ch03;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// 서버가 열리고 클라이언트가 접속하면
// 클라이언트가 유저로 등록되면 채팅을 할 수 있음
public class MyServer extends JFrame implements ActionListener {

	private JButton serverStartBtn;
	private JButton saveBtn;
	private JButton sendBtn;

	private JLabel portLabel;
	private JTextArea outputMessage;
	private JTextField inputMessage;
	private JTextField txtport;
	private ScrollPane scrollPane;

	private ServerSocket serverSocket;
	private Socket socket;
	private int port;

	Vector<User> users = new Vector<User>(); // User 객체 담음
	Vector<Room> rooms = new Vector<>();

	public MyServer() {
		initData();
		initLayout();
		addListener();
		txtport.requestFocus();
	}

	private void initData() {
		setTitle("My Talk [ Server ]");
		setSize(500, 500);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		portLabel = new JLabel("포트 번호 : ");
		saveBtn = new JButton("파일 저장");
		// sendBtn = new JButton("전송");
		serverStartBtn = new JButton("서버 시작");
		inputMessage = new JTextField();
		outputMessage = new JTextArea();
		txtport = new JTextField();
		scrollPane = new ScrollPane();

	}

	private void initLayout() {
		setLayout(null);

		add(portLabel);
		add(serverStartBtn);
		add(txtport);
		add(inputMessage);
		add(outputMessage);
		add(serverStartBtn);
		// add(sendBtn);

		portLabel.setBounds(40, 5, 70, 30);
		txtport.setBounds(110, 8, 100, 25);

		//scrollPane.add(outputMessage);

		saveBtn.setBounds(210, 400, 70, 30);
		serverStartBtn.setBounds(300, 7, 100, 25);
		outputMessage.setBounds(30, 40, 420, 350);
	}

	private void startNetWork(int port) {

		try {
			serverSocket = new ServerSocket(port);
			outputMessage.append("서버를 시작합니다.\n");
			System.out.println("서버 소켓 생성");
			connect();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private void connect() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						outputMessage.append("접속 대기 중\n");
						socket = serverSocket.accept(); // 계속 대기
						User user = new User(socket);
						user.start();
						users.add(user);
						System.out.println("유저 생성 및 벡터 추가");

					} catch (IOException e) {
						e.printStackTrace();
						break;
					}

				}

			}
		}).start();

	}

	// 서버에 연결된 모든 사용자에게 메시지 보냄
	public void broadCast(String str) {
		for (int i = 0; i < users.size(); i++) {
			users.get(i).sendMessage(str);
		}
	}

	// 내부클래스
	class User extends Thread {

		private InputStream inputStream;
		private OutputStream outputStream;
		private DataInputStream dataInputStream;
		private DataOutputStream dataOutputStream;
		String userName;
		String myCurrentRoom;
		private Socket userSocket;

		private boolean roomCheck = true;

		public User(Socket socket) {
			this.userSocket = socket;
			network();
		}

		private void network() {
			try {
				inputStream = userSocket.getInputStream();
				dataInputStream = new DataInputStream(inputStream);
				outputStream = userSocket.getOutputStream();
				dataOutputStream = new DataOutputStream(outputStream);

				userName = dataInputStream.readUTF(); // 처음 입장시 아이디 입력받음
				outputMessage.append("[ " + userName + " ] 입장\n");

				// 기존사용자들에게 신규 유저의 접속을 알린다.
				broadCast("NewUser/" + userName);

				// 사용자에게 자신을 알린후 벡터에 자신을 추가한다.
				users.add(this);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {

			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							String msg = dataInputStream.readUTF();
							outputMessage.append("[" + userName + "]" + msg + "\n");
							System.out.println("User msg : " + msg);
							receiveMessage(msg);
							broadCast(msg);
						} catch (Exception e) {
							outputMessage.append(userName + " : 사용자접속끊어짐\n");
						}
					}

				}
			}).start();
		}

		public void receiveMessage(String msg) {
			StringTokenizer stringTokenizer = new StringTokenizer(msg, "/");

			String protocol = stringTokenizer.nextToken();
			String message = stringTokenizer.nextToken();

			if (protocol.equals("Whisper")) { // 귓속말 기능
				for (int i = 0; i < users.size(); i++) {
					User user = users.get(i);
					if (this.getName().equals(user)) {
						sendMessage("w/" + user.getName() + "@" + msg);
					}
				}
			} else if (protocol.equals("CreateRoom")) {
				for (int i = 0; i < rooms.size(); i++) {
					Room room = rooms.get(i);
					if (room.roomTitle.equals(room.roomTitle)) { // 같은 방 이름 존재여부 확인
						sendMessage("CreateRoomFail/ok");
						break;
					} else {
						sendMessage("CreateRoom/" + room.roomTitle);
					}
				}
			} else if (protocol.equals("JoinRoom")) {
				for (int i = 0; i < rooms.size(); i++) {
					Room room = rooms.elementAt(i);
					if (room.roomTitle.equals(message)) {
						sendMessage("CreateRoomFail/ok");
						break;
					}
				}
			} else if (protocol.equals("OutRoom")) {

			} else if (protocol.equals("Chatting")) {
				String chatMsg = stringTokenizer.nextToken();
				for (int i = 0; i < rooms.size(); i++) {
					Room room = rooms.elementAt(i);
					if (room.roomTitle.equals(message)) {
						room.roomBroadcast("Chatting/" + userName + "/" + chatMsg);
					}
				}
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

	}

	// 내부 클래스
	class Room {

		String roomTitle;
		Vector<User> roomUser = new Vector<User>();

		public Room(String roomName, User user) {
			this.roomTitle = roomName;
			this.roomUser.add(user);

			user.myCurrentRoom = roomName;
		}

		private void roomBroadcast(String str) {
			for (int i = 0; i < roomUser.size(); i++) {
				User user = roomUser.elementAt(i);
				user.sendMessage(str);
			}
		}

		private void addUser(User user) {
			roomUser.add(user);
		}

		@Override
		public String toString() {
			return roomTitle;
		}

		private void removeRoom(User u) {
			roomUser.remove(u);
			boolean empty = roomUser.isEmpty();
			if (empty) {
				for (int i = 0; i < rooms.size(); i++) {
					Room room = rooms.elementAt(i);
					if (room.roomTitle.equals(roomTitle)) {
						rooms.remove(this);
						broadCast("EmptyRoom/" + roomTitle);
						broadCast("UserData_Updata/ok");
						break;
					}
				}
			}
		}
	}

	/*
	 * public void saveFile() { BufferedReader bufferedReader = new
	 * BufferedReader(new InputStreamReader(socket.getInputStream()));
	 * 
	 * try (BufferedWriter bw = new BufferedWriter(new FileWriter("message.txt",
	 * true))) { String msg = bufferedReader.readLine(); bw.write(msg + "\n");
	 * bw.flush();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	private void addListener() {
		// sendBtn.addActionListener(this);
		serverStartBtn.addActionListener(this);
		saveBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedBtn = (JButton) e.getSource();
		if (selectedBtn == serverStartBtn) {
			System.out.println("서버 실행");
			port = Integer.parseInt(txtport.getText().trim());
			startNetWork(port);
		} else if (selectedBtn == saveBtn) {
			JOptionPane.showMessageDialog(null, "파일 저장 완료", "알림", JOptionPane.CLOSED_OPTION);
			// saveFile();
		}

	}

	public static void main(String[] args) {
		new MyServer();
	}

}
