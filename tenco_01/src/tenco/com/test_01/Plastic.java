package tenco.com.test_01;

public class Plastic extends Material{
	
	@Override
	public String toString() {
		return "재료는 Plastic입니다.";
	}
	
	@Override
	public void doPrint() {
		System.out.println("플라스틱으로 출력합니다.");		
	}

}
