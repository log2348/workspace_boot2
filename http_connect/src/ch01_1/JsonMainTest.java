package ch01_1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dto.Student;
import dto.Test;

public class JsonMainTest {

	public static void main(String[] args) {
		// 자바 문법으로 JSON 형식을 만드는 방법
		JsonObject jsonObject = new JsonObject();
		
		jsonObject.addProperty("name", "주우재");
		jsonObject.addProperty("age", 25);
		jsonObject.addProperty("major", "기계공학");
		jsonObject.addProperty("phoneNumber", "010-0000-0000");
		
		System.out.println(jsonObject);
		System.out.println();
		// 키값으로 Value 뽑아내기
		System.out.println(jsonObject.get("name"));
		System.out.println(jsonObject.get("age"));
		System.out.println(jsonObject.get("major"));
		System.out.println(jsonObject.get("phoneNumber"));
		
		// 깊은 복사와 얕은 복사
		JsonObject s = jsonObject;
		System.out.println();
		s.addProperty("grade", "B+");
		System.out.println("");
		System.out.println(jsonObject);
		
		// 깊은 복사
		JsonObject d = jsonObject.deepCopy();
		d.addProperty("test", "1234");
		System.out.println();
		System.out.println(jsonObject);
		
		JsonArray arr = new JsonArray();
		arr.add(s);
		arr.add(d);
		
		System.out.println(arr); // 안에 Object 2개
		
		// 모델링
		Gson gson = new Gson();
		Student student1 = gson.fromJson(arr.get(0), Student.class);
		System.out.println(student1);
		
		JsonObject j1 = new JsonObject();
		JsonArray a1 = new JsonArray();
		
		JsonObject s1 = new JsonObject();
		s1.addProperty("name", "김기범");
		s1.addProperty("age", 20);
		
		JsonObject s2 = new JsonObject();
		s2.addProperty("name", "표지훈");
		s2.addProperty("age", 30);
		
		a1.add(s1);
		a1.add(s2);
		
		j1.add("Array", a1); // 오브젝트 넣을 땐 addProperty 안됨
		System.out.println(j1);
		
	}

}
