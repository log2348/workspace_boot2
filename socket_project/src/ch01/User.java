package ch01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Chat {

	private String name;
	private Socket userSocket;

	ChattingRoom chattingRoom;

	BufferedWriter bufferedWriter;
	BufferedReader bufferedReader;

	Vector<ChattingRoom> rooms = new Vector<ChattingRoom>();
	Vector<User> users = new Vector<User>();

	public User(Socket socket) {

	}

	@Override
	public void whisper(String msg) {
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if (this.getName().equals(user)) {
				user.sendMessage("w/" + name + "@" + msg);
			}
		}

	}

	@Override
	public void createRoom(String roomTitle) {
		for (int i = 0; i < rooms.size(); i++) {
			ChattingRoom room = rooms.get(i);
			if (room.getRoomTitle().equals(roomTitle)) { // 같은 방 이름 존재여부 확인
				sendMessage("CreateRoomFail/ok");
				break;
			} else {
				ChattingRoom newChattingRoom = new ChattingRoom(room.getRoomTitle(), this);
				rooms.add(newChattingRoom);
				sendMessage("CreateRoom/" + roomTitle);
			}
		}

	}
	
	@Override
	public void joinRoom(String roomTitle) {
//		for(int i = 0; i < rooms.size(); i++) {
//			ChattingRoom room = rooms.elementAt(i);
//			if(room.getRoomTitle().equals(message)) {
//				sendMessage("CreateRoomFail/ok");
//				break;
//			}
//		}
		
	}
	
	/*
	@Override
	public void deleteRoom(String roomTitle) {
		for (int i = 0; i < chattingRoom.getRooms().size(); i++) {
			ChattingRoom room = rooms.get(i);
			if (room.getRoomTitle().equals(roomTitle)) {
				room.getRooms().remove(i);
				break;
			} else {
				System.out.println("존재하지 않는 방입니다.");
			}
		}

	}
	*/

	public void sendMessage(String msg) {
		try {
			bufferedWriter.write(msg);
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void receiveMessage(String msg) {
		StringTokenizer st = new StringTokenizer(msg, "/");

		String protocol = st.nextToken();
		String message = st.nextToken();

		if (protocol.equals("w")) { // 귓속말 기능
			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				if (this.getName().equals(user)) {
					user.sendMessage("w/" + name + "@" + msg);
				}
			}
		} else if (protocol.equals("CreateRoom")) {
			for (int i = 0; i < rooms.size(); i++) {
				ChattingRoom room = rooms.get(i);
				if (room.getRoomTitle().equals(room.getRoomTitle())) { // 같은 방 이름 존재여부 확인
					sendMessage("CreateRoomFail/ok");
					break;
				} else {
					ChattingRoom newChattingRoom = new ChattingRoom(room.getRoomTitle(), this);
					rooms.add(newChattingRoom);
					sendMessage("CreateRoom/" + room.getRoomTitle());
				}
			}
		} else if (protocol.equals("JoinRoom")) {
			for(int i = 0; i < rooms.size(); i++) {
				ChattingRoom room = rooms.elementAt(i);
				if(room.getRoomTitle().equals(message)) {
					sendMessage("CreateRoomFail/ok");
					break;
				}
			}
		} else if(protocol.equals("JoinRoom")) {
			
		}
	}


}
