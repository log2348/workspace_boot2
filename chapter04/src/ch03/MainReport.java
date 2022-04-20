package ch03;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// 응답자 (콜백 받는 객체)
public class MainReport implements MakeReport{
	
	private String printDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	@Override
	public void addContent(String content) {
		System.out.println("**** My Report ****");
		System.out.println();
		System.out.println(content);
		System.out.println();
		System.out.println("작성일 : " + printDate());
		
	}


}
