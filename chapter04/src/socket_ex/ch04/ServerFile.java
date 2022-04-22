package socket_ex.ch04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class ServerFile extends JFrame {

	ServerSocket serverSocket;
	Socket socket;
	BufferedReader bufferedReader;

	BufferedWriter bufferedWriter; // 클라이언트 쪽으로 데이터를 보내는 녀석
	BufferedReader keyboardBufferedReader;

	public ServerFile() {

		System.out.println("1. >>>>>>> 서버 소켓 시작 <<<<<<<");

		try {
			serverSocket = new ServerSocket(10000); // 포트번호
			System.out.println("2. 서버 소켓 생성 완료");

			socket = serverSocket.accept(); // 클라이언트 연결 대기 중
			System.out.println("3. 클라이언트 연결 완료");

			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 초기화 처리
			keyboardBufferedReader = new BufferedReader(new InputStreamReader(System.in));
			// 클라이언트에게 보낼 스트림 연결 (outputStream)
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// 쓰레드 처리
			WriteThread writeThread = new WriteThread();
			Thread thread = new Thread(writeThread);
			thread.start();

			while (true) {

				try (BufferedWriter bw = new BufferedWriter(new FileWriter("message.txt", true))) {
					String clientMsg = bufferedReader.readLine();
					System.out.println("4. 클라이언트로부터 받은 메세지 : " + clientMsg);
					bw.write(clientMsg + "\n");
					bw.flush();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("예외 발생 : " + e.getMessage());
		} finally {
			try {
				bufferedReader.close();
				bufferedWriter.close();
				keyboardBufferedReader.close();
				serverSocket.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 내부 클래스 생성(쓰레드)
	private class WriteThread implements Runnable {

		@Override
		public void run() {

			while (true) {

				try {
					// 키보드에서 데이터를 읽어줌
					String serverMsg = keyboardBufferedReader.readLine();

					// 클라이언트로 데이터 보내기
					bufferedWriter.write(serverMsg + "\n");
					bufferedWriter.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}

	public static void main(String[] args) {
		new ServerFile();
	}

}
