package ch01;

public class MainTest2 {

	public static void main(String[] args) {
		
		ICalc iCalcAdd = new ICalc() {
			
			@Override
			public double calc(int a, int b, int c) {				
				return a + b + c;
			}
		};
		
		ICalc iCalcMinus = new ICalc() {
			
			@Override
			public double calc(int a, int b, int c) {			
				return a - b - c;
			}
		};
		
		ICalc iCalcMulti = new ICalc() {
			
			@Override
			public double calc(int a, int b, int c) {
				return a * b * c;
			}
		};
		
		System.out.println(iCalcAdd.calc(1, 1, 1));
		System.out.println(iCalcMinus.calc(1, 1, 1));
		System.out.println(iCalcMulti.calc(1, 1, 1));
		
		// 문제 1 -> 람다 표현식으로 만들어서 사용해 주세요
		ICalc iAddLamda = (a, b, c) -> {
			return a + b + c;
		};
		
		ICalc iMinusLamda = (a, b, c) -> {
			return a - b - c;
		};
		
		// 실행문이 한 문장인 경우 중괄호와 return 키워드를 생략할 수 있다.
		ICalc iMultiLamda = (a, b, c) -> a * b * c;
		
		System.out.println(iAddLamda.calc(2, 3, 4));
	}

}
