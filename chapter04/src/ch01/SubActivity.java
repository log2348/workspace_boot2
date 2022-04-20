package ch01;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

// 호출자(콜리) : 멤버 변수로 징검다리 역할을 하는 인터페이스를 멤버 변수로 먼저 선언한다.
public class SubActivity extends JFrame implements ActionListener{

	// 더하기 버튼
	JButton button1;
	// 마이너스 버튼
	JButton button2;
	// 값을 전달하는 버튼
	JButton button3;
	
	int result = 999;
	ICallBackBtnAction iCallBackBtnAction;
	
	// 콜리(Callee)는 콜백 받는 객체의 주소값을 알고 있어야 메서드가 호출 되었다고 알릴 수 있다.
	public SubActivity(ICallBackBtnAction iCallBackBtnAction){
		
		// NullPointerException 해결
		this.iCallBackBtnAction = iCallBackBtnAction;
		
		setSize(300, 300);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		button1 = new JButton("더하기 버튼 +");
		button2 = new JButton("빼기 버튼 -");
		button3 = new JButton("값 전달");
		add(button1);
		add(button2);
		add(button3);
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetBtn = (JButton) e.getSource();
		
		if(targetBtn == button1) {
			System.out.println("더하기 버튼 클릭 !!");
			// new X --> NullPointerException
			iCallBackBtnAction.clickedAddBtn();
		} else if(targetBtn == button2) {
			System.out.println("빼기 버튼 클릭 !!");
			iCallBackBtnAction.clickedSubBtn();
		} else if (targetBtn == button3) {
			System.out.println("값 전달하기");
			iCallBackBtnAction.passResultBtn(result);
		}
		
	}
	
}
