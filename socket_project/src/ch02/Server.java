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
	private int port;

	private Server mContext = this;

	Vector<UserSocket> users = new Vector<UserSocket>();
	Vector<Room> rooms = new Vector<Room>();

	ServerGUI serverGUI;
	ClientGUI clientGUI;

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
						Socket socket = serverSocket.accept();
						System.out.println("사용자 소켓 생성");
						UserSocket userSocket = new UserSocket(mContext, socket);

//						users.add(userSocket);
						userSocket.start();

						

					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("서버 중지");
						break;
					}

				}

			}
		}).start();

	}

	// 새로 들어온 유저에게 기존 데이터 띄워지도록
	public void updateInfo() {

//		if (users.size() > 0 && rooms.size() > 0) {
//			for (int i = 0; i < users.size(); i++) {
//				UserSocket userSocket = users.elementAt(i);
//				sendMessage("OldUser/" + userSocket.getUserName());
//			}
//			for (int i = 0; i < rooms.size(); i++) {
//				Room room = rooms.elementAt(i);
//				sendMessage("OldRoom/" + room.roomTitle);
//			}

//		}

	}

	// 서버에 연결된 모든 사용자에게 메시지 보냄
	public void broadcast(String str) {
		for (int i = 0; i < users.size(); i++) {
			UserSocket userSocket = users.elementAt(i);
			userSocket.sendMessage(str);
		}
	}
	

	// 프로토콜 별 동작 수행하도록
	public void getProtocol(String str) {
		StringTokenizer stringTokenizer = new StringTokenizer(str, "/");

		String protocol = stringTokenizer.nextToken();
		String message = stringTokenizer.nextToken();

		switch (protocol) {
		case "Whisper":
			stringTokenizer = new StringTokenizer(message, "@");
			String targetUser = stringTokenizer.nextToken();
			String content = stringTokenizer.nextToken();

			for (int i = 0; i < users.size(); i++) {
				UserSocket userSocket = users.get(i);
				if (userSocket.getName().equals(userSocket)) {
					userSocket.sendMessage("Whisper/" + userSocket.getName() + "@" + content);
				} else {
					System.out.println("존재하지 않는 사용자입니다.");
				}
			}
			break;

		case "CreateRoom":
			for (int i = 0; i < rooms.size(); i++) {
				Room room = rooms.get(i);

				// 같은 이름의 방 존재여부 확인
				if (room.roomTitle.equals(room.roomTitle)) {
					//sendMessage("CreateRoomFail/ok");
					break;
				} else {
					Room newRoom = new Room(message, users.get(i));
					rooms.add(newRoom);
					//sendMessage("CreateRoom/" + room.roomTitle);
					broadcast("NewRoom/" + message);
				}
			}
			break;

		case "EnterRoom":
			for (int i = 0; i < rooms.size(); i++) {
				Room room = rooms.elementAt(i);
				if (room.roomTitle.equals(message)) {
					room.broadcastRoom("Chatting/ [ " + users.get(i).getName() + " ] 님이 입장하셨습니다.");
					room.addUser(users.get(i));
					//sendMessage("EnterRoom/" + message);
				}
			}
			break;

		case "Chatting":
			String msg = stringTokenizer.nextToken();
			for (int i = 0; i < rooms.size(); i++) {
				Room room = rooms.elementAt(i);

				// 채팅 방 이름 확인해서 해당하는 채팅방에 메시지 띄우기
				if (room.roomTitle.equals(message)) {
					room.broadcastRoom("Chatting/" + users.get(i).getUserName() + "/" + msg);
				}
			}
			break;

		}

	}

	public static void main(String[] args) {
		new Server();
	}

}
