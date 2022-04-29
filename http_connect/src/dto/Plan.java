package dto;
// Data Transfer Object

import java.util.ArrayList;
import java.util.List;

class Todo {
	int id;
	String title;
	boolean complete;

}

public class Plan {

	private List<Todo> todoList = new ArrayList<>();
	private String server_name;
	
	public List<Todo> getTodoList() {
		return this.todoList;
	}
	
	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}

}
