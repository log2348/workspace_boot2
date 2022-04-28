package ch03;

// 서버와 클라이언트에서 구현해야 할 기능 규정
public interface ChatService {
	
	void chat();
	void whisper();
	void createRoom();
	void enterRoom();
	void exitRoom();
	void newRoom();
	void newUser();
	void oldRoom();
	void oldUser();

}
