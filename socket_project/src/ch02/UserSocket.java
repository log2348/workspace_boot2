package ch02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import ch02.Server.Room;
import ch02.Server.User;

public class UserSocket extends Thread {

	Server mContext;
	private Socket userSocket;

	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;

	private InputStream inputStream;
	private OutputStream outputStream;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	private String name;

	public UserSocket(Server mContext, Socket socket) {
		this.mContext = mContext;
		this.userSocket = socket;

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			inputStream = userSocket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			outputStream = userSocket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
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
						// mContext.outputMessage.append("[" + name + "]" + msg + "\n");
						System.out.println("User msg : " + msg);
						receiveMessage(msg);
						mContext.broadcast(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	public void receiveMessage(String msg) {
		StringTokenizer stringTokenizer = new StringTokenizer(msg, "/");

		String protocol = stringTokenizer.nextToken();
		String message = stringTokenizer.nextToken();

		if (protocol.equals("w")) { // 귓속말 기능
			for (int i = 0; i < mContext.users.size(); i++) {
				User user = mContext.users.get(i);
				if (this.getName().equals(user)) {
					sendMessage("w/" + user.getName() + "@" + msg);
				}
			}
		} else if (protocol.equals("CreateRoom")) {
			for (int i = 0; i < mContext.rooms.size(); i++) {
				Room room = mContext.rooms.get(i);
				if (room.roomTitle.equals(room.roomTitle)) { // 같은 방 이름 존재여부 확인
					sendMessage("CreateRoomFail/ok");
					break;
				} else {
					sendMessage("CreateRoom/" + room.roomTitle);
				}
			}
		} else if (protocol.equals("JoinRoom")) {
			for (int i = 0; i < mContext.rooms.size(); i++) {
				Room room = mContext.rooms.elementAt(i);
				if (room.roomTitle.equals(message)) {
					sendMessage("CreateRoomFail/ok");
					break;
				}
			}
		} else if (protocol.equals("OutRoom")) {

		} else if (protocol.equals("Chatting")) {
			String chatMsg = stringTokenizer.nextToken();
			for (int i = 0; i < mContext.rooms.size(); i++) {
				Room room = mContext.rooms.elementAt(i);
				if (room.roomTitle.equals(message)) {
					// room.roomBroadcast("Chatting/" + name + "/" + chatMsg);
				}
			}
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

}
