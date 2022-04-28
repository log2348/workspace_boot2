package ch01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import dto.Post;

public class HttpMainTest2 {

	public static void main(String[] args) {

		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/posts/20");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // 다운캐스팅
			connection.setRequestMethod("GET"); // REST API
			// connection.setRequestProperty("Content-type", "application/json");
			connection.connect();

			int statusCode = connection.getResponseCode(); // 통신이 잘 되었는지
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); // 데코레이터 방식

			StringBuffer buffer = new StringBuffer();
			String line = null;

			if (statusCode == 200) {
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
			} else if (statusCode == 404) { // 방어적 코드
				System.out.println("네트워크 연결이 불안정합니다.");
			}

			String str = buffer.toString();
			System.out.println(str);
			System.out.println("------------------------");

			Post post = new Post();
			// 문자열을 파싱해서 post 객체에 값을 담고 출력해보기

			// 일일이 파싱하기 힘듦(NumberFormatException 발생)
			String userId = str.substring(11, 12);
			int id = Integer.parseInt(str.substring(25, 26));
			String title = str.substring(40, 70);
			String body = str.substring(52);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
