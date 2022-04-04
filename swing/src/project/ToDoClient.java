package project;

public class ToDoClient {

	// TODO ArrayList 생성
	public ToDo createArrayList(String toDo, String month, String day) {
		return new ToDo(toDo, month, day);
	}

	public static String removeBlankString(String str) {

		String result1 = str.trim();
		String result2 = result1.replace(" ", "");
		return result2;

	}

}
