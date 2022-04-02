package ch03;

import java.util.Scanner;

public class ToDoMain {

	public static void main(String[] args) {

		ToDoClient toDoClient = new ToDoClient();

		ToDoService toDoArrayList = new ToDoArrrayList();

		Scanner scanner = new Scanner(System.in);

		String selectMenu = "";

		do {
			System.out.println("\n[ TODO 리스트 프로그램 ]");
			System.out.println("1. 목록 추가");
			System.out.println("2. 목록 조회");
			System.out.println("3. 목록 삭제");
			System.out.println("4. 전체 목록 조회");
			System.out.println("0. 프로그램 종료\n");
			
			System.out.print("명령어를 입력하세요 : ");
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
				
				ToDo toDo = toDoClient.createArrayList(content, month, day);
				toDoArrayList.addList(toDo);

			} else if (selectMenu.equals("2")) {
				System.out.println("1. 일자별 조회");
				System.out.println("2. 월별 조회\n");
				System.out.print("명령어를 입력하세요 : ");
				selectMenu = toDoClient.removeBlankString(scanner.nextLine());
				
				if(selectMenu.equals("1")) {
					System.out.println("날짜를 입력하세요.");
					System.out.print("월 : ");
					String month = toDoClient.removeBlankString(scanner.nextLine());
					
					System.out.print("일 : ");
					String day = toDoClient.removeBlankString(scanner.nextLine());
					
					toDoArrayList.selectListBydDate(month, day);
					
				} else if(selectMenu.equals("2")) {
					System.out.print("조회할 월을 입력하세요 : ");
					String month = toDoClient.removeBlankString(scanner.nextLine());
					
					toDoArrayList.selectListByMonth(month);
				} else {
					System.out.println("잘못된 입력입니다.");
				}
				
			} else if (selectMenu.equals("3")) {
				toDoArrayList.showAllList();
				
				System.out.print("삭제하려는 번호를 입력하세요 : ");
				int id = scanner.nextInt();
				scanner.nextLine();
				
				toDoArrayList.deleteList(id);	
				
				
			} else if (selectMenu.equals("4")) {
				toDoArrayList.showAllList();
				
			} else {
				System.out.println("잘못된 입력입니다.\n");
			}

		} while (!(selectMenu.equals("0")));

	}

}
