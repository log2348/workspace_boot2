package ch02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Server {

	private ServerSocket serverSocket;
	private Socket socket;
	private int port;
	
	private Server mContext = this;

	Vector<User> users = new Vector<User>();
	Vector<Room> rooms = new Vector<Room>();

	ServerGUI serverGUI;

	public Server() {
		serverGUI = new ServerGUI(this);
	}

	public void startServer(int port) {

		try {
			serverGUI.getOutputMessage().append("서버를 시작합니다.\n");
			serverSocket = new ServerSocket(port);
			System.out.println("서버 소켓 생성");
			connectClient();

		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("서버 연결 실패");
		}

	}

	private void connectClient() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						serverGUI.getOutputMessage().append("사용자 접속 대기...\n");
						socket = serverSocket.accept();

						User user = new User(mContext, socket);
						serverGUI.getOutputMessage().append("[ " + user.getUserName() + " ] 님이 입장하셨습니다.\n");
						
						user.start();
						users.add(user);


					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("서버 중지");
						break;
					}

				}

			}
		}).start();

	}

	// 서버에 연결된 모든 사용자에게 메시지 보냄
	public void broadcast(String str) {
		for (int i = 0; i < users.size(); i++) {
			users.get(i).sendMessage(str);
		}
	}

	public void getProtocol(String str) {
		StringTokenizer stringTokenizer = new StringTokenizer(str, "/");

		String protocol = stringTokenizer.nextToken();
		String message = stringTokenizer.nextToken();

		if (protocol.equals("Whisper")) { // 귓속말
			stringTokenizer = new StringTokenizer(message, "@");
			String targetUser = stringTokenizer.nextToken();
			String content = stringTokenizer.nextToken();

			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				if (user.getName().equals(user)) {
					user.sendMessage("Whisper/" + user.getName() + "@" + content);
				} else {
					System.out.println("존재하지 않는 사용자입니다.");
				}
			}
		} else if (protocol.equals("CreateRoom")) {
			for (int i = 0; i < rooms.size(); i++) {
				Room room = rooms.get(i);

				// 같은 이름의 방 존재여부 확인
				if (room.roomTitle.equals(room.roomTitle)) {
					//user.sendMessage("CreateRoomFail/ok");
					break;
				} else {
					//Room newRoom = new Room(message, user);
					//rooms.add(newRoom);
					//user.sendMessage("CreateRoom/" + room.roomTitle);
					broadcast("NewRoom/" + message);
				}
			}
		} else if (protocol.equals("JoinRoom")) {
			for (int i = 0; i < rooms.size(); i++) {
				Room room = rooms.elementAt(i);
				if (room.roomTitle.equals(message)) {
					//room.broadcastRoom("Chatting/ [ " + user.getName() + " ] 님이 입장하셨습니다.");
					//room.addUser(user);
					//user.sendMessage("JoinRoom/" + message);
				}
			}
		} else if (protocol.equals("Chatting")) {
			String msg = stringTokenizer.nextToken();
			for (int i = 0; i < rooms.size(); i++) {
				Room room = rooms.elementAt(i);

				// 채팅 방 이름 확인해서 해당하는 채팅방에 메시지 띄우기
				if (room.roomTitle.equals(message)) {
					//room.broadcastRoom("Chatting/" + user.getName() + "/" + msg);
				}
			}
		} else if (protocol.equals("ExitRoom")) {
			for (int i = 0; i < rooms.size(); i++) {
				Room room = rooms.elementAt(i);
				if (room.roomTitle.equals(message)) {

				}
			}
		}
	}
	

	public static void main(String[] args) {
		new Server();
	}

}
