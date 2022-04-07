package ch11;

import java.util.Scanner;

public class Starcraft {

	public static void main(String[] args) {

		Zealot zealot1 = new Zealot("질럿1");
		Marine marine1 = new Marine("마린1");
		Zergling zergling1 = new Zergling("저글링1");

		Scanner scanner = new Scanner(System.in);

		// 1. 유닛 선택 (유닛을 선택하세요)
		System.out.println("유닛을 선택하세요.");
		System.out.println("1. 질럿\t2. 마린\t3. 저글링");
		int unitSelect = scanner.nextInt();

		// 질럿 선택시
		if (unitSelect == 1) {
			zealot1.showInfo();

			// 2. 공격할 유닛 선택
			System.out.println("어떤 유닛을 공격하시겠습니까?");
			System.out.println("1. 마린\t2. 저글링");
			int attackSelect = scanner.nextInt();

			if (attackSelect == 1) {

				// 3. 공격횟수 선택
				System.out.println("몇 번 공격하시겠습니까?");
				int attackCount = scanner.nextInt();

				for (int i = 0; i < attackCount; i++) {
					String temp = ">";

					for (int count = 0; count < 3; count++) {
						System.out.print(temp);

						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					zealot1.attackMarine(marine1);
				}
				marine1.showInfo();
				zealot1.showInfo();
			} else if (attackSelect == 2) {

				// 3. 공격횟수 선택
				System.out.println("몇 번 공격하시겠습니까?");
				int attackCount = scanner.nextInt();

				for (int i = 0; i < attackCount; i++) {
					String temp = ">";

					for (int count = 0; count < 3; count++) {
						System.out.print(temp);

						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					zealot1.attackZergling(zergling1);
				}
				zergling1.showInfo();
				zealot1.showInfo();

			} else {
				System.out.println("잘못된 입력입니다");
			}

		} else if (unitSelect == 2) {
			marine1.showInfo();

			// 2. 공격할 유닛 선택
			System.out.println("어떤 유닛을 공격하시겠습니까?");
			System.out.println("1. 질럿\t2. 저글링");
			int unitSelect2 = scanner.nextInt();

			if (unitSelect2 == 1) {

				// 3. 공격횟수 선택
				System.out.println("몇 번 공격하시겠습니까?");
				int attackCount = scanner.nextInt();

				for (int i = 0; i < attackCount; i++) {
					String temp = ">";

					for (int count = 0; count < 3; count++) {
						System.out.print(temp);

						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					marine1.attackZealot(zealot1);

				}
				zealot1.showInfo();
				marine1.showInfo();

			} else if (unitSelect2 == 2) {

				// 3. 공격횟수 선택
				System.out.println("몇 번 공격하시겠습니까?");
				int attackCount = scanner.nextInt();

				for (int i = 0; i < attackCount; i++) {
					String temp = ">";

					for (int count = 0; count < 3; count++) {
						System.out.print(temp);

						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					marine1.attackZergling(zergling1);
				}
				zergling1.showInfo();
				marine1.showInfo();

			} else {
				System.out.println("잘못된 입력입니다");
			}
		} else if (unitSelect == 3) {
			zergling1.showInfo();

			// 2. 공격할 유닛 선택
			System.out.println("어떤 유닛을 공격하시겠습니까?");
			System.out.println("1. 질럿\t2. 마린");
			int unitSelect2 = scanner.nextInt();
			if (unitSelect2 == 1) {

				// 3. 공격횟수 선택
				System.out.println("몇 번 공격하시겠습니까?");
				int attackCount = scanner.nextInt();

				for (int i = 0; i < attackCount; i++) {
					String temp = ">";

					for (int count = 0; count < 3; count++) {
						System.out.print(temp);

						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					zergling1.attackZealot(zealot1);

				}
				zealot1.showInfo();
				zergling1.showInfo();

			} else if (unitSelect2 == 2) {

				// 3. 공격횟수 선택
				System.out.println("몇 번 공격하시겠습니까?");
				int attackCount = scanner.nextInt();

				for (int i = 0; i < attackCount; i++) {
					String temp = ">";

					for (int count = 0; count < 3; count++) {
						System.out.print(temp);

						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					zergling1.attackMarine(marine1);

				}
				marine1.showInfo();
				zergling1.showInfo();

			} else {
				System.out.println("잘못된 입력입니다.");
			}

		} else {
			System.out.println("잘못된 입력입니다.");
		}

	}

}
