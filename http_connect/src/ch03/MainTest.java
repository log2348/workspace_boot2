package ch03;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MainTest {

	public static void main(String[] args) {
		// 문제 1
		JsonArray array = new JsonArray();

		JsonObject o1 = new JsonObject();
		JsonObject o2 = new JsonObject();
		JsonObject o3 = new JsonObject();

		o1.addProperty("name", "홍길동");
		o1.addProperty("age", 20);
		o1.addProperty("address", "부산");

		o2.addProperty("name", "이순신");
		o2.addProperty("age", 33);
		o2.addProperty("address", "서울");

		o3.addProperty("name", "세종대왕");
		o3.addProperty("age", 59);
		o3.addProperty("address", "세종시");

		array.add(o1);
		array.add(o2);
		array.add(o3);

		System.out.println(array);


		// 문제 2
		JsonObject object = new JsonObject();
		JsonArray todoArray = new JsonArray();
		object.add("todoList", todoArray);
		object.addProperty("server_name", "server_1");

		JsonObject todo1 = new JsonObject();
		JsonObject todo2 = new JsonObject();
		todoArray.add(todo1);
		todoArray.add(todo2);

		todo1.addProperty("id", 1);
		todo1.addProperty("title", "청소");
		todo1.addProperty("complete", false);

		todo2.addProperty("id", 1);
		todo2.addProperty("title", "영어공부");
		todo2.addProperty("complete", true);

		System.out.println(object);
		
	}

}
