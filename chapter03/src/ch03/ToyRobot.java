package ch03;

public class ToyRobot implements RemoteController {
	// homeappliance 상속받을 필요x

	String name;

	public ToyRobot() {
		this.name = "건담로봇";
	}

	@Override
	public void turnOn() {
		System.out.println("로봇 ON");

	}

	@Override
	public void turnOff() {
		System.out.println("로봇 OFF");

	}

}
