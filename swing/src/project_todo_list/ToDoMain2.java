package project_todo_list;

import java.util.Scanner;

public class ToDoMain2 {

	public static void main(String[] args) {

		ToDoClient toDoClient = new ToDoClient();

		ToDoService toDoArrayList = new ToDoArrrayList();

		Scanner scanner = new Scanner(System.in);

		String selectMenu = "";

		boolean flag = true;

		while (flag) {
			System.out.println("[ TODO 리스트 프로그램 ]");
			System.out.println("1. 목록 추가");
			System.out.println("2. 목록 조회");
			System.out.println("3. 목록 삭제");
			System.out.println("4. 전체 목록 조회");
			System.out.println("0. 프로그램 종료");

			selectMenu = toDoClient.removeBlankString(scanner.nextLine());

			if (selectMenu.equals("0")) {
				System.out.println("프로그램을 종료합니다.");
				scanner.close();

			} else if (selectMenu.equals("1")) {
				System.out.println("날짜 입력");
				System.out.print("월 : ");
				String month = toDoClient.removeBlankString(scanner.nextLine());

				System.out.print("일 : ");
				String day = toDoClient.removeBlankString(scanner.nextLine());

				System.out.print("내용을 입력하세요 : ");
				String content = toDoClient.removeBlankString(scanner.nextLine());

				ToDo toDo = new ToDo(content, month, day);

				toDo = toDoClient.createArrayList(content, month, day);
				toDoArrayList.addList(toDo);

			} else if (selectMenu.equals("2")) {
				System.out.println("1. 일자별 조회");
				System.out.println("2. 월별 조회");
				selectMenu = toDoClient.removeBlankString(scanner.nextLine());

				if (selectMenu.equals("1")) {
					System.out.println("날짜를 입력하세요.");
					System.out.print("월 : ");
					String month = toDoClient.removeBlankString(scanner.nextLine());

					System.out.print("일 : ");
					String day = toDoClient.removeBlankString(scanner.nextLine());

					toDoArrayList.selectListBydDate(month, day);

				} else if (selectMenu.equals("2")) {
					System.out.println("조회할 월을 입력하세요.");
					String month = toDoClient.removeBlankString(scanner.nextLine());

					toDoArrayList.selectListByMonth(month);
				}

			} else if (selectMenu.equals("3")) {
				toDoArrayList.showAllList();

				System.out.println("삭제하려는 번호를 입력하세요.");
				int id = scanner.nextInt();
				scanner.nextLine();

				toDoArrayList.deleteList(id);

			} else if (selectMenu.equals("4")) {
				toDoArrayList.showAllList();
				System.out.println();

			} else {
				System.out.println("잘못된 입력입니다.\n");
			}
		}

	}

}
