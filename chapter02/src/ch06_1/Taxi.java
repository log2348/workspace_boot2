package ch06_1;

import java.util.Scanner;

public class Taxi {
	
	private int taxiNumber;
	private int totalMoney;
	private String company;
	private Scanner scanner;
	
	public Taxi() {
		scanner = new Scanner(System.in);
		
		System.out.print("택시 번호 : ");
		taxiNumber = scanner.nextInt();
		
		scanner.nextLine();
		
		System.out.print("회사명 : ");
		company = scanner.nextLine();		
		
	}
	
	public Taxi(String company) {
		this.company = company;
	}
	
	public void take() {
		this.totalMoney += 10000;
		
	}
	
	public void showTaxiInfo() {
		System.out.println(company + " 기업의 총 수입은 " + totalMoney + "원입니다.");
	}

}
