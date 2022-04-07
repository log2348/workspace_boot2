package test01_2;

public class Water extends Material{
	
	@Override
	public String toString() {
		return "재료는 물입니다.";
	}
	
	@Override
	public void doPrint() {
		System.out.println("물로 출력합니다.");
		
	}
}
