package ch02;

import java.util.Vector;

public class Room {

	String roomTitle;
	Vector<User> roomUser = new Vector<User>();

	public Room(String roomTitle, User user) {
		this.roomTitle = roomTitle;
		this.roomUser.add(user);

		user.setRoomTitle(roomTitle);
	}

	public void broadcastRoom(String str) {
		for (int i = 0; i < roomUser.size(); i++) {
			User user = roomUser.elementAt(i);
			user.sendMessage(str);
		}
	}

	public void addUser(User user) {
		roomUser.add(user);
	}
}
