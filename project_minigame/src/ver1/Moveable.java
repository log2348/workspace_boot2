package ver1;

public interface Moveable {
	void left();
	void right();
	
	void kitchenUp();//키친의 up은 걸어올라가는거.
	void kitchenDown();//키친의 down은 걸어내려가는거고

	void deliveryUp();//배달의 up은 점프임.	
	void deliveryDown(); //배달의 down은 걍 떨어지는거
}
