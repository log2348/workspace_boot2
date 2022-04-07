package test3;

public class B extends A{
	
	@Override
	public void test1() {
		System.out.println("B클래스에 있는 메서드가 동작합니다.");
	}
	
	public void testPrint() {
		System.out.println("B클래스만의 동작입니다.");
	}

}
