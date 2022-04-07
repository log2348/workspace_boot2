package ch11;

import java.util.Scanner;

public class StarcraftTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int select;
		int attackCount;
		boolean flag = true;

		Zealot zealot = new Zealot("질럿");
		Zergling zergling = new Zergling("저글링");
		Marine marine = new Marine("마린");

		while (flag) {
			System.out.println("[ 유닛 선택 ]");
			System.out.println("1. 질럿");
			System.out.println("2. 저글링");
			System.out.println("3. 마린");
			System.out.println("0. 프로그램 종료");
			System.out.println();

			System.out.print("명령을 선택해주세요: ");
			select = scanner.nextInt();

			System.out.println();

			if (select == 0) {
				System.out.println("프로그램을 종료합니다..");
				flag = false;
				break;

			} else if (select == 1) {

				while (flag) {
					System.out.println("==== 질럿 생성 완료 ====");
					System.out.println("1. 유닛 상세정보");
					System.out.println("2. 공격");
					System.out.println("3. 강화");
					System.out.println("9. 뒤로가기");
					System.out.println("0. 프로그램 종료");
					System.out.println();
					System.out.print("명령을 선택해주세요 : ");
					select = scanner.nextInt();

					if (select == 0) {
						System.out.println("프로그램을 종료합니다..");
						flag = false;
						break;

					} else if (select == 9) {
						System.out.println("이전 페이지로 이동합니다..");
						break;

					} else if (select == 1) {
						zealot.showInfo();

					} else if (select == 2) {
						System.out.println("[ 공격할 유닛 선택 ]");
						System.out.println("1. 저글링");
						System.out.println("2. 마린");
						System.out.println();
						System.out.print("명령을 선택해주세요 : ");
						select = scanner.nextInt();

						System.out.println();

						if (select == 1) {
							System.out.print("공격횟수 : ");
							attackCount = scanner.nextInt();

							for (int i = 0; i < attackCount; i++) {
								zealot.attackZergling(zergling);

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
						} else if (select == 2) {
							System.out.print("공격횟수 : ");
							attackCount = scanner.nextInt();

							for (int i = 0; i < attackCount; i++) {
								zealot.attackMarine(marine);

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
						} else {
							System.out.println("잘못된 입력입니다.");
						}
					} else if (select == 3) {
						zealot.upgradePower();
					} else {
						System.out.println("잘못된 입력입니다.");
					}

				}

			} else if (select == 2) {

				while (flag) {
					System.out.println("==== 저글링 생성 완료 ====");
					System.out.println("1. 유닛 상세정보");
					System.out.println("2. 공격");
					System.out.println("3. 강화");
					System.out.println("9. 뒤로가기");
					System.out.println("0. 프로그램 종료");
					System.out.println();
					System.out.print("명령을 선택해주세요 : ");
					select = scanner.nextInt();

					if (select == 0) {
						System.out.println("프로그램을 종료합니다..");
						flag = false;
						break;

					} else if (select == 9) {
						System.out.println("이전 페이지로 이동합니다..");
						break;

					} else if (select == 1) {
						zergling.showInfo();

					} else if (select == 2) {
						System.out.println("[ 공격할 유닛 선택 ]");
						System.out.println("1. 질럿");
						System.out.println("2. 마린");
						System.out.println();
						System.out.print("명령을 선택해주세요 : ");
						select = scanner.nextInt();

						if (select == 1) {
							System.out.print("공격횟수 : ");
							attackCount = scanner.nextInt();

							for (int i = 0; i < attackCount; i++) {
								zergling.attackZealot(zealot);

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
						} else if (select == 2) {
							System.out.print("공격횟수 : ");
							attackCount = scanner.nextInt();

							for (int i = 0; i < attackCount; i++) {
								zergling.attackMarine(marine);

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
						} else {
							System.out.println("잘못된 입력입니다.");
						}
					} else if (select == 3) {
						zergling.upgradePower();

					} else {
						System.out.println("잘못된 입력입니다.");
					}
				}

			} else if (select == 3) {

				while (flag) {
					System.out.println("==== 마린 생성 완료 ====");
					System.out.println("1. 유닛 상세정보");
					System.out.println("2. 공격");
					System.out.println("3. 강화");
					System.out.println("9. 뒤로가기");
					System.out.println("0. 프로그램 종료");
					System.out.println();
					System.out.print("명령을 선택해주세요 : ");
					select = scanner.nextInt();

					if (select == 0) {
						System.out.println("프로그램을 종료합니다..");
						flag = false;
						break;

					} else if (select == 9) {
						System.out.println("이전 페이지로 이동합니다..");
						break;

					} else if (select == 1) {
						marine.showInfo();

					} else if (select == 2) {
						System.out.println("[ 공격할 유닛 선택 ]");
						System.out.println("1. 질럿");
						System.out.println("2. 저글링");
						System.out.println();
						System.out.print("명령을 선택해주세요 : ");
						select = scanner.nextInt();

						if (select == 1) {
							System.out.print("공격횟수 : ");
							attackCount = scanner.nextInt();

							for (int i = 0; i < attackCount; i++) {
								marine.attackZealot(zealot);

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
						} else if (select == 2) {
							System.out.print("공격횟수 : ");
							attackCount = scanner.nextInt();

							for (int i = 0; i < attackCount; i++) {
								marine.attackZergling(zergling);

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
						} else {
							System.out.println("잘못된 입력입니다.");
						}
					} else if (select == 3) {
						marine.upgradePower();

					} else {
						System.out.println("잘못된 입력입니다.");
					}
				}

			} else {
				System.out.println("잘못된 입력입니다.");
			}
			System.out.println();
		}
		System.out.println("프로그램이 종료되었습니다.");

	}

}
