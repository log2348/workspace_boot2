package ch16;

public class MainTest2 {

	public static void main(String[] args) {
		
		// 배열의 선언과 동시에 초기화(값을 넣다)
		int[] numbers1 = new int[] {10, 20, 30};
		
		int[] numbers2 = {10, 20, 30, 40}; // new int[] 생략
		
		int[] numbers3;
		numbers3 = new int[] {10, 20, 30, 40, 50};
		
		// 배열은 for문과 거의 함께 많이 사용됩니다.
		for(int i = 0; i < numbers1.length; i++) {
			int temp = numbers1[i];
			System.out.println(temp);
		}
		
		System.out.println("==================");
		for (int i = 0; i < numbers2.length; i++) {
			if(numbers2[i] == 3) {
				break;
			}
			System.out.println(numbers2[i]);
		}
		
		System.out.println("==================");
		// 확장 for문 (처음부터 끝까지 찍어야할때(단순))
		for (int i : numbers3) {
			System.out.println("출력값 : " + i);
		}
		
		}

}
