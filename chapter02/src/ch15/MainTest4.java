package ch15;

public class MainTest4 {

	public static void main(String[] args) {
		//문자 배열을 만들어 A-Z까지 배열에 저장하고 이를 다시 출력하기
		
		char[] alphabets = new char[26];
		char ch =  'A';
		
		for(int i = 0; i < alphabets.length; i++) {
			alphabets[i] = ch++;
		}
		
		for(int i = 0; i < alphabets.length; i++) {
			System.out.println(alphabets[i] + ", " + (int)alphabets[i]);
		}

	}

}
