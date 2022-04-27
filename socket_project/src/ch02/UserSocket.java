package ch02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSocket extends Thread {

	private Socket socket;

	private Server mContext;

	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;

	private String userName;
	private String roomTitle;

	Vector<Room> rooms = new Vector<>();

	public UserSocket(Server mContext, Socket socket) {
		this.socket = socket;
		this.mContext = mContext;

		try {
			
			
			
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			userName = bufferedReader.readLine();
			mContext.serverGUI.getOutputMessage().append("[ " + userName + " ] 님이 입장하셨습니다.\n");
			
			// 이시점에는 자기 자신을 방송을 못 받음 !~ 
			// 이유는 이코드 아래에서 백터에 저장 !!!
			mContext.broadcast("NewUser/" + userName); // 프로토콜
			// -----------------
			
			System.out.println("사이즈 확인 !! + " + mContext.users.size());
			// 기존 1 없기 때문에 
			// 1 
			
			// s1
			// 자기 자신한테 기존 사용자 정보를 전달해야 한다. 
			//sendMessage("OldUser/" + );
			for (int i = 0; i < mContext.users.size(); i++) {
				UserSocket userSocket = mContext.users.elementAt(i);
				System.out.println("서버에서 확인하는 olduser : " + userSocket.getUserName());
				// userSocket.sendMessage() <--- 절대 금지 여기서 
				sendMessage("OldUser/"+userSocket.getUserName());
				
			}
			
			// 2
			// 2 
			// 1 
			
			
//			// s2 
//			for (int i = 0; i < mContext.users.size(); i++) {
//				UserSocket userSocket = mContext.users.get(i);
//				userSocket.sendMessage("OldUser/" + userSocket.getUserName());
//			}
			
			mContext.users.add(this);
			

			// mContext.updateInfo();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("사용자 스트림 연결 실패");
		}
		
		

	}

	public void sendMessage(String msg) {
		try {
			bufferedWriter.write(msg + "\n");
			bufferedWriter.flush();
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
						String msg = bufferedReader.readLine();
						mContext.serverGUI.getOutputMessage().append("[" + userName + "]" + msg + "\n");
						mContext.getProtocol(msg);
					} catch (IOException e) {
						e.printStackTrace();
						mContext.serverGUI.getOutputMessage().append("[ " + userName + " ] 님의 접속이 끊어졌습니다.\n");
						break;
					}
				}

			}
		}).start();

	}

}
