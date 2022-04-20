package ch04;

import java.awt.Color;

public class Activity1 extends BaseActivity {

	// 변수 선언과 초기화(콜백 메서드 방식 중 하나)
	CallbackCheckPosition callback = new CallbackCheckPosition() {

		@Override
		public void checkPosition(int x, int y) {
			System.out.println(name + " 가 콜백을 받았습니다. " + x);
			System.out.println(name + " 가 콜백을 받았습니다. " + y);

		}
	};

	public Activity1(String name) {
		super(name);

	}

	@Override // <-- 주석(컴파일 시점에 무시됨), but 어노테이션은 무시x (컴파일 시점에 오류 알려줌)
	protected void initData() {
		super.initData();
	}
	
	@Override
	protected void setInitLayout() {
		super.setInitLayout(); // 부모의 기능
		panel.setBackground(Color.blue); // 추가
	}

}
