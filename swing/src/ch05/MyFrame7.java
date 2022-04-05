package ch05;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MyFrame7 extends JFrame implements KeyListener {

	private JTextArea textArea;

	public MyFrame7() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("키 이벤트 연습");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textArea = new JTextArea();
	}

	private void setInitLayout() {
		setVisible(true);
		add(textArea);
	}

	private void addEventListener() {
		textArea.addKeyListener(this);
	}

	// 문자 키에만 반응합니다
	@Override
	public void keyTyped(KeyEvent e) {

	}

	// 키보드를 눌렀을 때 반응
	@Override
	public void keyPressed(KeyEvent e) {

		char c = e.getKeyChar();
		int keyCode = e.getKeyCode();
		
		/*
		 * textArea 키코드 그리고 c값을 보이게 코딩해주세요
		 */
		textArea.append("\n" + "c : " + c + " : " + "keyCode : " + keyCode);

		System.out.println("c : " + c);
		System.out.println("keyCode : " + keyCode);
		

		// 콘솔창에 왼쪽, 오른쪽, 아래 위 구분해서 찍어주세요
		if (keyCode == KeyEvent.VK_UP) {
			System.out.println("위");
		} else if (keyCode == KeyEvent.VK_DOWN) { 
			System.out.println("아래");
		} else if (keyCode == KeyEvent.VK_RIGHT) { 
			System.out.println("오른쪽");
		} else if (keyCode == KeyEvent.VK_LEFT) { 
			System.out.println("왼쪽");
		}

	}

	// 키보드를 눌렀다가 뗐을 때 반응
	@Override
	public void keyReleased(KeyEvent e) {

	}

	public static void main(String[] args) {
		new MyFrame7();
	}

}
