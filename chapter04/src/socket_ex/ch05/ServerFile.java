package socket_ex.ch05;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;

public class ServerFile extends JFrame {
	/*
	 * 양방향 통신, 다중 접속 기능 구현
	 */

	ServerFile mContext;
	ServerSocket serverSocket;
	
	Vector<UserSocket> sockets = new Vector<UserSocket>();

	public ServerFile() {

		System.out.println("1. >>>>>>> 서버 소켓 시작 <<<<<<<");

		try {
			serverSocket = new ServerSocket(10000); // 포트번호

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 추가 기능 구현시 새로운 쓰레드 생성하기

		// 새로운 소켓 생성 (실제 통신할 녀석)
		while (true) {
			try {
				Socket socket = serverSocket.accept(); // 사용자가 들어올 때까지 멈춰있다
				UserSocket userSocket = new UserSocket(this, socket);
				userSocket.start();
				sockets.add(userSocket);
				System.out.println("계속 도나요??");
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	// 생성된 UserSocket에 접근해서 하나씩 메시지 보내기
	// 방송하다 (전체 방송)
	public void broadcast(String msg) {
		for(int i = 0; i < sockets.size(); i++) {
			sockets.get(i).sendMessage(msg);
		}
	}

	public static void main(String[] args) {
		new ServerFile();
	}

}
