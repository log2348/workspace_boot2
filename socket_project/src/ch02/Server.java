package ch02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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

	// 서버에 연결된 모든 사용자에게 메시지 보냄
	public void broadcast(String str) {
		for (int i = 0; i < users.size(); i++) {
			UserSocket userSocket = users.get(i);
			userSocket.sendMessage(str);
		}
	}

	public static void main(String[] args) {
		new Server();
	}

}
