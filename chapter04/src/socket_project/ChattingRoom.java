package socket_project;

import java.util.Vector;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChattingRoom {
	
	private String roomTitle;
	
	private User user;
	
	private Vector<User> rooms = new Vector();
	
	public ChattingRoom(User user) {
		this.user = user;
		
	}
	
	private void broadcastRoom(String msg) {
		for (int i = 0; i < rooms.size(); i++) {
			user = rooms.elementAt(i);
			user.sendMessage(msg);
		}
	}
	

}
