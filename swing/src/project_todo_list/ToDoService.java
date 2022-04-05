package project_todo_list;

public interface ToDoService {

	// 요소 추가
	void addList(ToDo toDo);

	// 삭제
	void deleteList(int id);

	// 일자별 리스트 출력
	void selectListBydDate(String month, String day);

	// 월별 리스트 출력
	void selectListByMonth(String month);

	// 저장 데이터 전체 출력
	void showAllList();

}
