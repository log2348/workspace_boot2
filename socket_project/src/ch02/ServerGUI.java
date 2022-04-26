package ch02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerGUI extends JFrame implements ActionListener{
	
	private JButton startServerBtn;
	private JButton saveBtn;
	private JButton sendBtn;

	private JLabel portLabel;
	private JTextArea outputMessage;
	private JTextField inputMessage;
	private JTextField txtport;
	
	private Server server;
	
	public ServerGUI(Server server) {
		this.server = server;
		initObject();
		setInitLayout();
		addListener();
	}
	
	private void initObject() {
		setTitle("My Small Talk [ Server ]");
		setSize(500, 500);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		portLabel = new JLabel("포트 번호 : ");
		saveBtn = new JButton("파일 저장");
		// sendBtn = new JButton("전송");
		startServerBtn = new JButton("서버 시작");
		inputMessage = new JTextField();
		outputMessage = new JTextArea();
		txtport = new JTextField();
	}
	
	private void setInitLayout() {
		setLayout(null);

		add(portLabel);
		add(startServerBtn);
		add(txtport);
		add(inputMessage);
		add(outputMessage);
		add(startServerBtn);
		
		outputMessage.setEditable(false);
		// add(sendBtn);

		portLabel.setBounds(40, 5, 70, 30);
		txtport.setBounds(110, 8, 100, 25);

		//scrollPane.add(outputMessage);

		saveBtn.setBounds(210, 400, 70, 30);
		startServerBtn.setBounds(300, 7, 100, 25);
		outputMessage.setBounds(30, 40, 420, 350);
	}
	
	private void addListener() {
		startServerBtn.addActionListener(this);
		saveBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedBtn = (JButton) e.getSource();
		if (selectedBtn == startServerBtn) {
			System.out.println("서버 실행");
			server.setPort(Integer.parseInt(txtport.getText()));
			server.startServer(server.getPort());
		} else if (selectedBtn == saveBtn) {
			JOptionPane.showMessageDialog(null, "파일 저장 완료", "알림", JOptionPane.CLOSED_OPTION);
			// saveFile();
		}

	}

}
