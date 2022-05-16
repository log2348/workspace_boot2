package ch02;

public class MainTest2 {

	public static void main(String[] args) {

		IMaxNumber iMaxLamda = (x, y) -> x > y ? x : y;
		System.out.println(iMaxLamda.returnMax(100, 1000));
		
	}

}
