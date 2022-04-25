package ch01;

import java.io.BufferedReader;
import java.net.Socket;

/*
 * 서버에서 소켓을 통해 클라이언트와 통신이 연결되면
 * 이 클래스에서 User를 동록하고 채팅을 주고받을 수 있도록 함
 */
public class ReceiveThread implements Runnable {
	
	Socket socket;
	BufferedReader bufferedReader;
	String userName;
	User user;
	
	public ReceiveThread() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			while(true) {
				String msg = bufferedReader.readLine();
				user.sendMessage(msg);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
