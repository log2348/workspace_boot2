package socket_pratice.clone;

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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Server extends JFrame implements ActionListener {

	private JPanel panel;
	private ScrollPane scrollPane;
	private JTextField inputPortNum; // 포트번호 입력 칸
	private JTextArea textArea; // 서버가 방송하는 칸(broadCast), 입력X
	private JLabel portNumLabel;
	private JButton serverStartBtn;
	private JButton serverStopBtn;

	private ServerSocket serverSocket;
	private Socket socket;
	private int port;

	private Vector<User> users = new Vector<>();
	private Vector<ChattingRoom> chattingRooms = new Vector<ChattingRoom>();

	public Server() {
		initData();
		addListener();
		inputPortNum.requestFocus();
	}

	private void initData() {
		setBounds(100, 100, 350, 410);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);

		panel = new JPanel();
		scrollPane = new ScrollPane();
		textArea = new JTextArea();
		portNumLabel = new JLabel("포트 번호 : ");
		inputPortNum = new JTextField();
		serverStartBtn = new JButton();
		serverStopBtn = new JButton();

		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		panel.add(scrollPane);
		panel.add(inputPortNum);
		panel.add(serverStartBtn);
		panel.add(serverStopBtn);

		scrollPane.setBounds(10, 10, 309, 229);
		scrollPane.add(textArea);

		textArea.setEditable(false);
		textArea.setBounds(12, 11, 310, 230);

		portNumLabel.setBounds(12, 273, 82, 15);

		inputPortNum.setBounds(98, 270, 82, 15);
		inputPortNum.setColumns(10);

		serverStartBtn.setBounds(12, 315, 154, 23);
		serverStopBtn.setBounds(168, 315, 154, 23);
		serverStopBtn.setEnabled(false);
	}

	private void addListener() {
		inputPortNum.addActionListener(this);
		serverStartBtn.addActionListener(this);
		serverStopBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == serverStartBtn) {
			if (inputPortNum.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "값을 입력하세요.", "알림", JOptionPane.ERROR_MESSAGE);
			} else {
				port = Integer.parseInt(inputPortNum.getText());
				startNetwork();
				inputPortNum.setEditable(false); // 입력되면 수정 못하도록
				serverStartBtn.setEnabled(false);
				serverStopBtn.setEnabled(true);

			}
		} else if (e.getSource() == serverStopBtn) {
			try {
				serverSocket.close();
				users.removeAllElements();
				chattingRooms.removeAllElements();
				inputPortNum.setEditable(true);
				serverStartBtn.setEnabled(true);
				serverStopBtn.setEnabled(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	private void startNetwork() {
		try {
			serverSocket = new ServerSocket(port);
			textArea.append("서버를 시작합니다.");
			connect();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미 사용 중인 포트입니다.", "알림", JOptionPane.ERROR_MESSAGE);
			serverStartBtn.setEnabled(true);
			serverStopBtn.setEnabled(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "잘못입력하셨습니다.", "알림", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void connect() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						textArea.append("사용자의 접속을 기다립니다.\n");
						socket = serverSocket.accept(); // 클라이언트가 서버에 접속

						new User(socket).start(); // 쓰레드 등록
					} catch (Exception e) {
						textArea.append("서버가 중지되었습니다. 다시 시작하려면 시작 버튼을 눌러주세요.");
						break;
					}
				}

			}
		}).start();
	}

	// 전체 사용자에게 메시지 보냄 (방송하다)
	public void broadCast(String msg) {
		for (int i = 0; i < users.size(); i++) {
			User user = users.elementAt(i);
			user.sendMessage(msg);
		}
	}

	class User extends Thread {

		private InputStream is;
		private OutputStream os;
		private DataInputStream dis;
		private DataOutputStream dos;

		private Socket userSocket;
		private String username;
		private String currentChattingRoomTitle;


		private boolean roomCheck = true;

		public User(Socket socket) {
			this.userSocket = socket;
			network();
		}

		private void network() {
			try {
				is = userSocket.getInputStream();
				dis = new DataInputStream(is);
				os = userSocket.getOutputStream();
				dos = new DataOutputStream(os);

				// 처음 접속시 ID 입력받음
				username = dis.readUTF();
				textArea.append("[[" + username + "]] 입장\n");

				broadCast("NewUser/" + username);

				for (int i = 0; i < users.size(); i++) {
					User user = users.elementAt(i);
					sendMessage("OldUser/" + user.username);
				}

				for (int i = 0; i < chattingRooms.size(); i++) {
					ChattingRoom chattingRoom = chattingRooms.elementAt(i);
					sendMessage("OldRoom/" + chattingRoom.roomTitle);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Stream 설정 에러", "알림", JOptionPane.ERROR_MESSAGE);
			}
		}

		@Override
		public void run() {
			while (true) {
				try {
					String msg = dis.readUTF();
					textArea.append("[[" + username + "]]" + msg + "\n");
					receiveMessage(msg);
				} catch (Exception e) {
					try {
						textArea.append(username + "님의 접속이 끊어졌습니다.\n");
						dos.close();
						dis.close();
						userSocket.close();
						users.remove(this);
						chattingRooms.remove(this);
						broadCast("UserOut/" + username);
						broadCast("ErrorOutRoom/" + username);
						broadCast("UserDataUpdate/ok");
						break;
					} catch (Exception e1) {
						e.printStackTrace();
						break;
					}
				}
			}
		}

		private void receiveMessage(String str) {
			StringTokenizer st = new StringTokenizer(str, "/");

			String protocol = st.nextToken();
			String message = st.nextToken();

			System.out.println("protocol : " + protocol);
			System.out.println("message : " + message);

			if (protocol.equals("Note")) { // 쪽지보내기
				System.out.println(message);
				st = new StringTokenizer(message, "@");
				String user = st.nextToken();
				String note = st.nextToken();

				for (int i = 0; i < users.size(); i++) {
					User u = users.elementAt(i);
					if (u.username.equals(user)) {
						u.sendMessage("Note/" + username + "@" + note);
					}
				}

			} else if (protocol.equals("CreateRoom")) { // 방 만들기
				for (int i = 0; i < chattingRooms.size(); i++) {
					ChattingRoom room = chattingRooms.elementAt(i);
					if (room.roomTitle.equals(message)) { // 같은 방 이름 존재여부 확인
						sendMessage("CreateRoomFail/ok");
						roomCheck = false;
						break;
					} else {
						roomCheck = true;
					}
				}

				if (roomCheck) { // 같은 이름의 방이 없으면 방 생성
					ChattingRoom newChattingRoom = new ChattingRoom(message, this);
					chattingRooms.add(newChattingRoom);
					sendMessage("CreateRoom/" + message);
					broadCast("newRoom/" + message);
				}
			} else if (protocol.equals("Chatting")) {
				String msg = st.nextToken();
				for (int i = 0; i < chattingRooms.size(); i++) {
					ChattingRoom r = chattingRooms.elementAt(i);
					if (r.roomTitle.equals(message)) {
						r.broadCastRoom("Chatting/" + username + "/" + msg);
					}
				}
			} else if (protocol.equals("JoinRoom")) {
				for (int i = 0; i < chattingRooms.size(); i++) {
					ChattingRoom room = chattingRooms.elementAt(i);
					if (room.roomTitle.equals(message)) {
						room.broadCastRoom("Chatting/[[알림]]/(((" + username + "님 입장");
						room.addUser(this);
						sendMessage("JoinRoom/" + message);
					}
				}
			} else if (protocol.equals("OUtRoom")) {
				for (int i = 0; i < chattingRooms.size(); i++) {
					ChattingRoom room = chattingRooms.elementAt(i);
					if (room.roomTitle.equals(message)) {
						room.removeRoom(this);
						sendMessage("OutRoom/ok");
						break;
					}
				}
			}
		}

		private void sendMessage(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	} // end of User

	class ChattingRoom {

		String roomTitle;
		Vector<User> chattingRoomUser = new Vector<>();

		public ChattingRoom(String roomTitle, User user) {
			this.roomTitle = roomTitle;
			this.chattingRoomUser.add(user);

			user.currentChattingRoomTitle = roomTitle;
		}

		private void broadCastRoom(String str) {
			for (int i = 0; i < chattingRoomUser.size(); i++) {
				User user = chattingRoomUser.elementAt(i);
				user.sendMessage(str);
			}
		}

		private void addUser(User user) {
			chattingRoomUser.add(user);
		}

		@Override
		public String toString() {
			return roomTitle;
		}

		private void removeRoom(User user) {
			chattingRoomUser.remove(user);

			boolean isEmpty = chattingRoomUser.isEmpty();

			if (isEmpty) {
				for (int i = 0; i < chattingRoomUser.size(); i++) {
					ChattingRoom room = chattingRooms.elementAt(i);
					if (room.roomTitle.equals(roomTitle)) {
						chattingRooms.remove(this);
						broadCast("EmptyRoom/" + roomTitle);
						broadCast("UserDataUpdate/ok");
						break;
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		new Server();
	}

}
