package ch02;

import java.util.Vector;

public class Room {

	Server mContext;
	String roomTitle;
	Vector<UserSocket> roomUser = new Vector<UserSocket>();

	public Room(String roomTitle, UserSocket userSocket) {
		this.roomTitle = roomTitle;
		this.roomUser.add(userSocket);

		userSocket.setRoomTitle(roomTitle);
	}

	public void broadcastRoom(String str) {
		for (int i = 0; i < roomUser.size(); i++) {
			UserSocket userSocket = roomUser.elementAt(i);
			userSocket.sendMessage(str);
		}
	}

	public void addUser(UserSocket user) {
		roomUser.add(user);
	}

}
