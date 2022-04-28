package ch03;

import java.util.Vector;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room {

	Server mContext;
	String roomTitle;
	Vector<UserSocket> roomUser = new Vector<UserSocket>();

	public Room(String roomTitle, UserSocket userSocket) {
		this.roomTitle = roomTitle;
		this.roomUser.add(userSocket);

		userSocket.setRoomTitle(roomTitle);
	}

	// 같은 채팅방에 있는 유저들에게 출력
	public void broadcastRoom(String str) {
		for (int i = 0; i < roomUser.size(); i++) {
			UserSocket userSocket = roomUser.get(i);
			userSocket.sendMessage(str);
		}
	}

	public void addUser(UserSocket user) {
		roomUser.add(user);
	}
	
	public void deleteUser(UserSocket user) {
		roomUser.remove(user);
	}

}
