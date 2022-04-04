package project;

public class ToDo {

	public static int serialNumber = 0;
	private int id;

	private String toDo;
	private String month;
	private String day;

	public ToDo(String toDo, String month, String day) {
		this.id = ++serialNumber;
		this.toDo = toDo;
		this.month = month;
		this.day = day;
	}

	public static int getSerialNumber() {
		return serialNumber;
	}

	public static void setSerialNumber(int serialNumber) {
		ToDo.serialNumber = serialNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToDo() {
		return toDo;
	}

	public void setToDo(String toDo) {
		this.toDo = toDo;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "[ " + id + " ] " + month + "월 " + day + "일 : " + toDo;
	}

}
