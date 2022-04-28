package ch02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {

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
							System.out.println("클라이언트가 보냄 : " +msg);
							setProtocol(msg);
						} catch (Exception e) {
							try {
								clientGUI.getOutputMessage().append(userName + " : 사용자 접속 끊어짐\n");
								bufferedReader.close();
								bufferedWriter.close();
								socket.close();

								break;
							} catch (IOException e1) {
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
			stringTokenizer = new StringTokenizer(message, "@");
			
			String userName = stringTokenizer.nextToken();
			String content = stringTokenizer.nextToken();		
			JOptionPane.showMessageDialog(null, content, "[ " + userName + " ] 님의 쪽지", JOptionPane.ERROR_MESSAGE);
			
			break;
//		case "CreateRoom":
//			//clientRoomTitle = message;
//			clientGUI.rooms.add(message);
//			clientGUI.getTotalRoomList().setListData(clientGUI.rooms);
//			break;
		case "CreateRoomFail":
			JOptionPane.showMessageDialog(null, "이미 존재하는 방입니다.", "알림", JOptionPane.ERROR_MESSAGE);
			break;
		case "NewRoom":
			clientGUI.rooms.add(message);
			clientGUI.getTotalRoomList().setListData(clientGUI.rooms);
			break;
		case "Chatting":
			String msg = stringTokenizer.nextToken();
			clientGUI.getOutputMessage().append(message + " : " + msg + "\n");
			break;
		case "EnterRoom":
			clientRoomTitle = message;
			clientGUI.getOutputMessage().setText("[ " + clientRoomTitle + " ] 방에 입장하셨습니다.\n");
			break;
		case "NewUser":
			clientGUI.userSockets.add(message);
			clientGUI.getTotalUserList().setListData(clientGUI.userSockets);
			break;
		case "ExitRoom":
			clientGUI.getOutputMessage().append("[ " + clientRoomTitle + "] 에서 퇴장하셨습니다.");
			clientRoomTitle = null;
			break;
		case "OldUser":
			System.out.println("OldUser 들어오나요?? " + message);

			clientGUI.userSockets.add(message);
			clientGUI.getTotalUserList().setListData(clientGUI.userSockets);
			
			break;
		case "OldRoom":
			clientGUI.rooms.add(message);
			System.out.println("클라이언트 측 OldRoom 들어오나요?");
			clientGUI.getTotalRoomList().setListData(clientGUI.rooms);
			break;
		}

	}
	
	public static void main(String[] args) {
		new Client();
	}

}
