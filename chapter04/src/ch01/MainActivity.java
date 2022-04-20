package ch01;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

// 응답자(콜백 받는 객체) : ICallBackBtnAction 인터페이스를 구현해서 정의하면 된다.
public class MainActivity extends JFrame implements ICallBackBtnAction {

	int count;
	JLabel label;

	public MainActivity() {
		count = 0;
		label = new JLabel("count : " + count);
		setSize(300, 300);
		setLayout(new FlowLayout());
		setVisible(true);
		add(label);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new SubActivity(this);
	}

	@Override
	public void clickedAddBtn() {
		System.out.println("+ 버튼의 콜백 받았습니다.");
		count++;
		label.setText("count : " + count);

	}

	// - 버튼의 동작을 받는 콜백 메서드 정의
	@Override
	public void clickedSubBtn() {
		System.out.println("- 버튼의 콜백 받았습니다.");
		count--;
		label.setText("count : " + count);

	}

	// 값을 전달받는 콜백 메서드 정의
	@Override
	public void passResultBtn(int result) {
		System.out.println("값 전달 받았습니다.");
		label.setText("전달 받은 값 : " + result);

	}

}
