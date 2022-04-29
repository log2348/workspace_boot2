package ch03;

import java.util.ArrayList;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
class Student {
	String name;
	int age;
	String address;

}

public class MainTest2 {

	public static void main(String[] args) {
		Student student1 = new Student("홍길동", 20, "부산");
		Student student2 = new Student("이순신", 20, "서울");
		Student student3 = new Student("세종대왕", 20, "세종시");

		// Object --> 형식이 있는 문자열 (json)
		Gson gson = new Gson();
		String jsonStr = gson.toJson(student1); // object 타입을 문자열로
		System.out.println(jsonStr);
		
		// ArrayList<Object> --> jsonArray(object)
		ArrayList<Student> list = new ArrayList<>();
		list.add(student1);
		list.add(student2);
		list.add(student3);
		System.out.println(list);
		
		String jsonArratStr = gson.toJson(list);
		System.out.println("-----------------------------------");
		System.out.println(jsonArratStr);
	}

}
