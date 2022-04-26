package ch02;

public interface ChatService {
	
	void whisper(String msg);
	
	void createRoom(String roomTitle);
	
	void deleteRoom(String roomTitle);

}
