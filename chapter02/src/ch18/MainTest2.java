package ch18;

public class MainTest2 {

	public static void main(String[] args) {
		
		ToyMachine toyMachine = new ToyMachine();
		
		System.out.println("[ 인형 뽑기 게임 ]");
		System.out.println("숫자를 맞추면 인형이 반환됩니다.");
		toyMachine.randomValue();

	}

}
