package ch03;

import com.google.gson.Gson;

public class MainTest4 {

	public static void main(String[] args) {
		Gson gson = new Gson();
		Student[] students = new Student[3];

		Student student1 = new Student("홍길동", 20, "부산");
		Student student2 = new Student("이순신", 20, "서울");
		Student student3 = new Student("세종대왕", 20, "세종시");

		students[0] = student1;
		students[1] = student2;
		students[2] = student3;

		String studentArr = gson.toJson(students); // object 타입을 문자열로
		System.out.println(studentArr);

		// 파싱하는 방법 - 1
		/*
		 Student objS1 = gson.fromJson(studentArr, Student.class); // reflection
		 System.out.println(objS1.address); System.out.println(objS1.name);
		 System.out.println(objS1.age); System.out.println();
		 */

				
		/*
		 * 파싱(문자열의 오브젝트화)하는 방법
		 * 
		 * 1. object
		 * 2. 배열
		 * 3. ArrayList
		 */
	}
	

}
