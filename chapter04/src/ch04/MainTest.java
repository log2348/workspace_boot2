package ch04;

public class MainTest {

	public static void main(String[] args) {
		
		Activity1 activity1 = new Activity1("화면 1");
		
		Activity2 activity2 = new Activity2("화면 2");
		
		// 메서드를 통해서 콜백 연결하기(생성자 통한 연결)
		activity2.setCallbackCheckPosition(activity1.callback);

	}

}
