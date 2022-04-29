package ch03;

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
public class Client implements ChatService {

	// 통신 자원
	private Socket socket;
	private String ip = "127.0.0.1";
	private int port;

	// 입출력 스트림
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;

	private ClientGUI clientGUI;
	private ServerGUI serverGUI;

	private String userName;
	private String clientRoomTitle;

	private StringTokenizer stringTokenizer;

	// 프로토콜 문자열
	private String protocol;
	private String message;

	public Client() {
		clientGUI = new ClientGUI(this);
	}

	public void connectServer() {
		try {
			// 클라이언트 소켓 생성
			socket = new Socket(ip, port);
			connectStream();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("클라이언트 연결 실패");
		}
	}

	private void connectStream() {
		try {
			// 스트림 연결
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			userName = clientGUI.getTxtUserName().getText().trim();
			sendMessage(userName);

			clientGUI.userSockets.add(userName);

			clientGUI.getTotalUserList().setListData(clientGUI.userSockets);

			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							String msg = bufferedReader.readLine();
							setProtocol(msg);
						} catch (Exception e) {
							try {
								clientGUI.getOutputMessage().append("[ " + userName + " ] 사용자 접속 끊어짐\n");
								bufferedReader.close();
								bufferedWriter.close();
								socket.close();

								break;
							} catch (IOException e1) {
								e.printStackTrace();
								return;
							}

						}

					}

				}
			}).start();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("스트림 연결 실패");
		}
	}

	public void sendMessage(String msg) {
		try {
			bufferedWriter.write(msg + "\n");
			bufferedWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 프로토콜 별 동작 수행
	public void setProtocol(String str) {

		StringTokenizer stringTokenizer = new StringTokenizer(str, "/");

		String protocol = stringTokenizer.nextToken();
		String message = stringTokenizer.nextToken();

		System.out.println("프로토콜 : " + protocol);
		System.out.println("메시지 : " + message);

		switch (protocol) {
		case "Whisper":
			whisper();
			break;

		case "CreateRoom":
			clientRoomTitle = message;
			break;

		case "NewRoom":
			newRoom();
			break;

		case "Chatting":
			chat();
			break;

		case "EnterRoom":
			enterRoom();
			break;
		case "NewUser":
			newUser();
			break;

		case "ExitRoom":
			exitRoom();
			break;

		case "OldUser":
			oldUser();
			break;

		case "OldRoom":
			oldRoom();
			break;
		}

	}

	@Override
	public void chat() {
		String roomTitle = message;
		String chatUser = stringTokenizer.nextToken();		
		String msg = stringTokenizer.nextToken();

		if (!msg.equals("입장") && !msg.equals("퇴장")) {
			clientGUI.getOutputMessage().append(chatUser + " : " + msg + "\n");
		}

	}

	@Override
	public void whisper() {
		stringTokenizer = new StringTokenizer(message, "@");

		String userName = stringTokenizer.nextToken();
		String content = stringTokenizer.nextToken();
		JOptionPane.showMessageDialog(null, content, "[ " + this.userName + " ]님의 쪽지", JOptionPane.PLAIN_MESSAGE);

	}

	@Override
	public void createRoom() {
		clientRoomTitle = message;

	}

	@Override
	public void enterRoom() {
		clientRoomTitle = message;
		clientGUI.getOutputMessage().append("******* " + this.userName + " 님 입장 *******\n");

	}

	@Override
	public void exitRoom() {
		// clientGUI.getOutputMessage().append("****** " + this.userName + " 님 퇴장 ******\n");
		clientGUI.getOutputMessage().setText("");
		clientRoomTitle = "";

	}

	@Override
	public void newRoom() {
		clientGUI.rooms.add(message);
		clientGUI.getTotalRoomList().setListData(clientGUI.rooms);

	}

	@Override
	public void newUser() {
		clientGUI.userSockets.add(message);
		clientGUI.getTotalUserList().setListData(clientGUI.userSockets);

	}

	@Override
	public void oldRoom() {
		clientGUI.rooms.add(message);
		clientGUI.getTotalRoomList().setListData(clientGUI.rooms);

	}

	@Override
	public void oldUser() {
		clientGUI.userSockets.add(message);
		clientGUI.getTotalUserList().setListData(clientGUI.userSockets);

	}

	public static void main(String[] args) {
		new Client();
	}

}
