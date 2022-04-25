package ch02;

import java.io.BufferedReader;
import java.io.BufferedWriter;

// 클라이언트가 메시지 전달
public class SendThread implements Runnable{

	BufferedWriter bufferedWriter;
	BufferedReader bufferedReader; // 채팅 내용 받아옴
	
	@Override
	public void run() {
		while(true) {
			try {
				String msg = bufferedReader.readLine(); // 채팅 받아옴
				bufferedWriter.write(msg); // 출력
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

}
