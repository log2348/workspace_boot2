package ch01;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyServer extends JFrame implements ActionListener {

	private JButton serverStartBtn;
	private JButton saveBtn;
	private JButton sendBtn;
	private JLabel portLabel;
	private JTextArea outputMessage;
	private JTextField inputMessage;
	private JTextField txtport;

	private ServerSocket serverSocket;
	private Socket socket;
	private int port;
	private String protocol;

	private BufferedReader bufferedReader; // 클라이언트로부터 온 데이터 읽어오기
	private BufferedWriter bufferedWriter; // 클라이언트에게 메시지 보내기
	private BufferedReader keyboardBufferedReader; // 키보드에서 데이터 읽어옴

	private ChattingRoom chattingRoom;
	private User user;

	public MyServer() {
		initData();
		initLayout();
		addListener();
		txtport.requestFocus();
	}

	private void initData() {
		setTitle("My Talk [ Server ]");
		setSize(500, 500);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		portLabel = new JLabel("포트 번호 : ");
		saveBtn = new JButton("파일 저장");
		sendBtn = new JButton("전송");
		serverStartBtn = new JButton("서버 시작");
		inputMessage = new JTextField();
		outputMessage = new JTextArea();
		txtport = new JTextField();

	}

	private void initLayout() {
		setLayout(null);

		add(portLabel);
		add(serverStartBtn);
		add(txtport);
		add(inputMessage);
		add(outputMessage);
		add(serverStartBtn);
		add(sendBtn);
		add(saveBtn);

		portLabel.setBounds(40, 5, 70, 30);
		txtport.setBounds(110, 8, 100, 25);

//		saveBtn.setBounds(200, 400, 100, 40);
		sendBtn.setBounds(400, 400, 70, 30);
		saveBtn.setBounds(400, 300, 100, 30);
		serverStartBtn.setBounds(300, 7, 100, 25);
		outputMessage.setBounds(30, 40, 420, 350);
		inputMessage.setBounds(30, 400, 370, 30);
	}

	private void startNetWork(int port) {

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("서버 소켓 생성");
			connect();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	private void connect() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					outputMessage.append("접속 대기 중\n");
					socket = serverSocket.accept();				
					user = new User(socket);
				} catch (IOException e) {
					e.printStackTrace();
				}

				User user = new User(socket);

				System.out.println("클라이언트 연결됨");
				
			}
		}).start();
	}

	// 클라이언트에게 메시지 보내는 쓰레드
	private class writeThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					String msg = keyboardBufferedReader.readLine();

					// 클라이언트로 메시지 보내기
					bufferedWriter.write(msg + "\n");
					bufferedWriter.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	// 메시지 계속 읽어오는 쓰레드
	private class readThread extends Thread {
		@Override
		public void run() {

			while (true) {
				try {
					String msg = bufferedReader.readLine();
					System.out.println("4. 클라이언트로부터 받은 메세지 : " + msg);
					bufferedWriter.write(msg + "\n");
					bufferedWriter.flush();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	public void saveFile() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("message.txt", true))) {
			String msg = bufferedReader.readLine();
			bw.write(msg + "\n");
			bw.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void addListener() {
		sendBtn.addActionListener(this);
		serverStartBtn.addActionListener(this);
		saveBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedBtn = (JButton) e.getSource();

		if (selectedBtn == sendBtn) {
			System.out.println("전송 버튼 클릭");
			outputMessage.append(inputMessage.getText() + "\n");
			inputMessage.setText(null);
		} else if (selectedBtn == serverStartBtn) {
			System.out.println("서버 시작");
			port = Integer.parseInt(txtport.getText().trim());
			startNetWork(port);
		} else if (selectedBtn == saveBtn) {
			saveFile();
		}

	}

	public static void main(String[] args) {
		new MyServer();
	}

}
