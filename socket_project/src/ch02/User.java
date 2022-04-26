package ch02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends Thread {

	private Socket socket;
	
	private Server mContext;

	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;

	private String userName;
	private String roomTitle;

	Vector<Room> rooms = new Vector<>();

	public User(Server mContext, Socket socket) {
		this.socket = socket;
		this.mContext = mContext;
		

		try {
			socket.setSoTimeout(30000);
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			//userName = bufferedReader.readLine();
			//mContext.serverGUI.getOutputMessage().append("[ " + userName + " ] 님이 입장하셨습니다.\n");
			mContext.broadcast("NewUser/" + userName);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("사용자 스트림 연결 실패");
		}

	}

	@Override
	public void run() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {					
						String msg = bufferedReader.readLine();
						mContext.serverGUI.getOutputMessage().append("[" + userName + "]" + msg + "\n");
						System.out.println("User msg : " + msg);
						mContext.getProtocol(msg);
						mContext.broadcast(msg);
					} catch (IOException e) {
						e.printStackTrace();
						break;
					}
				}

			}
		}).start();

	}

	public void sendMessage(String msg) {
		try {
			bufferedWriter.write(msg + "\n");
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
