package ch02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

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

	private boolean roomCheck;

	public UserSocket(Server mContext, Socket socket) {
		this.socket = socket;
		this.mContext = mContext;

		try {

			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			userName = bufferedReader.readLine();

			updateInfo();

			// 기존 사용자에게 자신을 알린 후 벡터에 자신 추가
			mContext.broadcast("NewUser/" + userName);
			mContext.users.add(this);

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
						getProtocol(msg);
						mContext.saveFile(msg);
					} catch (IOException e) {
						e.printStackTrace();
						mContext.serverGUI.getOutputMessage().append("[ " + userName + " ] 님의 접속이 끊어졌습니다.\n");
						break;
					}
				}

			}
		}).start();

	}
	
	// 새로온 접속자에게 기존 유저들 정보 업데이트
	private void updateInfo() {
		for (int i = 0; i < mContext.users.size(); i++) {
			UserSocket userSocket = mContext.users.get(i);
			System.out.println("서버에서 확인하는 OldUser : " + userSocket.getUserName());
			sendMessage("OldUser/" + userSocket.getUserName());

		}

		for (int i = 0; i < mContext.rooms.size(); i++) {
			Room room = mContext.rooms.get(i);
			System.out.println("서버가 보내는 기존 방 리스트 : " + room.getRoomTitle());
			sendMessage("OldRoom/" + room.getRoomTitle());
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

			for (int i = 0; i < mContext.users.size(); i++) {
				UserSocket user = mContext.users.get(i);
				if (user.getUserName().equals(targetUser)) {
					sendMessage("Whisper/" + targetUser + "@" + content);
				}
			}
			break;

		case "CreateRoom":
			roomCheck = true;
			for (int i = 0; i < mContext.rooms.size(); i++) {
				Room room = mContext.rooms.get(i);

				// 같은 이름의 방 존재여부 확인
				if (room.getRoomTitle().equals(message)) {
					sendMessage("CreateRoomFail/");
					JOptionPane.showMessageDialog(null, "같은 방의 이름이 존재합니다.", "알림", JOptionPane.ERROR_MESSAGE);
					roomCheck = false;
				}
			}

			if (roomCheck) {
				Room newRoom = new Room(message, this);
				mContext.rooms.add(newRoom);
				sendMessage("CreateRoom/" + message);
				mContext.broadcast("NewRoom/" + message);

			}

			break;

		case "EnterRoom":
			for (int i = 0; i < mContext.rooms.size(); i++) {
				Room room = mContext.rooms.get(i);

				if (room.getRoomTitle().equals(message)) {
					room.addUser(this);
					setRoomTitle(message);
					room.broadcastRoom("Chatting/" + message + "/입장");
					sendMessage("EnterRoom/" + message);
				}
			}
			break;

		case "Chatting":

			String roomTitle = message;
			String msg = stringTokenizer.nextToken();
			

			for (int i = 0; i < mContext.rooms.size(); i++) {
				Room room = mContext.rooms.get(i);

				// 같은 채팅방 유저들 간에만 채팅 가능
				if (room.getRoomTitle().equals(roomTitle)) {
					room.broadcastRoom("Chatting/" + roomTitle + "/" + msg);
					System.out.println("서버에서 보내는 Chatting 프로토콜");
				}
			}


			break;

		case "ExitRoom":

			for (int i = 0; i < mContext.rooms.size(); i++) {
				Room targetRoom = mContext.rooms.get(i);

				if (targetRoom.getRoomTitle().equals(message)) {
					targetRoom.deleteUser(this);
					setRoomTitle(null);
					sendMessage("ExitRoom/" + message);
					targetRoom.broadcastRoom("Chatting/" + message + "/퇴장");
				}
			}
			break;
		case "NewRoom":
			mContext.broadcast("NewRoom/" + message);
			break;

		}

	}

	// 나와 연결되어있는 스트림을 통해서 보내는 개별 메시지
	public void sendMessage(String msg) {
		try {
			bufferedWriter.write(msg + "\n");
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}