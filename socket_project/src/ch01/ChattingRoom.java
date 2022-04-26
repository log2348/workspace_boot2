package ch01;

import java.util.Vector;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChattingRoom {
	
	private String roomTitle;
	
	private User user;
	
	private Vector<User> roomUser = new Vector();
	
	public ChattingRoom(String roomTitle, User user) {
		this.roomTitle = roomTitle;
		this.roomUser.add(user);
		
		
	}
	
	private void broadcastRoom(String msg) {
		for (int i = 0; i < roomUser.size(); i++) {
			user = roomUser.elementAt(i);
			user.sendMessage(msg);
		}
	}
	

}
