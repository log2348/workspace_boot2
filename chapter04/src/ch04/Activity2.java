package ch04;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// 콜리 : 호출자
public class Activity2 extends BaseActivity {

	CallbackCheckPosition callbackCheckPosition;

	// 옵저버 패턴(계속 주시)
	public void setCallbackCheckPosition(CallbackCheckPosition callbackCheckPosition) {
		this.callbackCheckPosition = callbackCheckPosition;
	}

	public Activity2(String name) {
		super(name);

		addEventListener();
	}

	private void addEventListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				callbackCheckPosition.checkPosition(x, y);
			}
		});
	}

}
