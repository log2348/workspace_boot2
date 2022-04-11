

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoArrrayList implements ToDoService {

	private ArrayList<ToDo> toDoList;
	private Scanner scanner;

	public ToDoArrrayList() {
		toDoList = new ArrayList<ToDo>();

		ToDo toDo1 = new ToDo("치과예약", "4", "1");
		ToDo toDo2 = new ToDo("벚꽃구경", "4", "1");
		ToDo toDo3 = new ToDo("저녁약속", "4", "1");
		ToDo toDo4 = new ToDo("운동", "4", "2");
		ToDo toDo5 = new ToDo("스터디", "4", "3");

		toDoList.add(toDo1);
		toDoList.add(toDo2);
		toDoList.add(toDo3);
		toDoList.add(toDo4);
		toDoList.add(toDo5);

	}

	@Override
	public void addList(ToDo toDo) {
		System.out.println("추가되었습니다.");
		toDoList.add(toDo);
	}

	@Override
	public void deleteList(int id) {

		boolean deleteCheck = false;

		for (int i = 0; i < toDoList.size(); i++) {
			if (toDoList.get(i) != null) {
				for (int j = 0; j < toDoList.size(); j++) {
					if (toDoList.get(j).getId() == id) {
						toDoList.remove(j);
//						if(toDoList.get(j) != null) {
//							toDoList.get(j).setSerialNumber(j + 1);				
//						}

						deleteCheck = true;
					}
				}
			} else {
				System.out.println("저장된 데이터가 없습니다.");
			}
		}

		if (deleteCheck) {
			System.out.println("삭제가 완료되었습니다.");
		} else {
			System.out.println("삭제할 데이터가 존재하지 않습니다.");
		}

	}

	@Override
	public void showAllList() {
		System.out.println("==== 전체 데이터 조회 ====");

		if (toDoList.size() == 0) {
			System.out.println("저장된 데이터가 없습니다.");
		} else {
			for (ToDo toDo : toDoList) {
				System.out.println(toDo);
			}
		}

	}

	@Override
	public void selectListBydDate(String month, String day) {
		System.out.println("날짜별 리스트를 출력합니다.");

		boolean check = false;

		for (int i = 0; i < toDoList.size(); i++) {
			if (toDoList.get(i) != null) {
				if (toDoList.get(i).getDay().equals(day) && toDoList.get(i).getMonth().equals(month)) {
					System.out.println(toDoList.get(i).getMonth() + "월 " + toDoList.get(i).getDay() + "일 : "
							+ toDoList.get(i).getToDo());
					check = true;
				}
			}

		}

		if (check) {
			return;
		} else {
			System.out.println("해당 날짜에 저장된 데이터가 없습니다.");
		}

	}

	@Override
	public void selectListByMonth(String month) {
		System.out.println("월별 리스트를 출력합니다.");

		boolean check = false;

		for (int i = 0; i < toDoList.size(); i++) {
			if (toDoList.get(i) != null) {
				if (toDoList.get(i).getMonth().equals(month)) {
					System.out.println(toDoList.get(i).getMonth() + "월 " + toDoList.get(i).getDay() + "일 : "
							+ toDoList.get(i).getToDo());
					check = true;

				}
				continue;
			}

		}

		if (check) {
			return;
		} else {
			System.out.println("해당하는 월에 저장된 데이터가 없습니다.");
		}

	}

}
