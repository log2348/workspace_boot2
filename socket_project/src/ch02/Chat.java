package ch02;

public interface Chat {
	
	void whisper(String msg);
	
	void createRoom(String roomTitle);
	
	void deleteRoom(String roomTitle);

}
