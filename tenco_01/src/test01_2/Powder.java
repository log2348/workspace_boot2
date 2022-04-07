package test01_2;

public class Powder extends Material{

	@Override
	public String toString() {
		return "재료는 Powder입니다.";
	}
	
	@Override
	public void doPrint() {
		System.out.println("파우더로 출력합니다.");
		
	}
}
