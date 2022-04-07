package tenco.com.test_02;

public class WrapperClass2 {

	public static void main(String[] args) {

		String str = "100dfadfadsfasdf";
		String str2 = "100.5";
		String str3 = "true";

		// 런타임 시점 에러 - 예외처리
		// 내가 예상하지 못한 에러가 발생할 수 있다
		try {
			int i = Integer.parseInt(str);
			System.out.println("문자열 int 값 변환 : " + i);
		} catch (Exception e) {
			System.out.println("int 값으로 변환할 수 없습니다.");
		}

		try {
			double d = Double.parseDouble(str2);
			System.out.println("문자열 double 값 변환 : " + d);

		} catch (Exception e) {
			System.out.println("double 값으로 변환할 수 없습니다.");
		}

		try {
			boolean bool = Boolean.parseBoolean(str3);
			System.out.println("문자열 boolean 값 변환 : " + bool);

		} catch (Exception e) {
			System.out.println("boolean 값으로 변환할 수 없습니다.");
		}

	}
}
