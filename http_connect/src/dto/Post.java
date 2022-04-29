package dto;
// Data Transfer Object

import lombok.ToString;

@ToString
public class Post {
	public int userId;
	public int id;
	public String title;
	public String body;
}
