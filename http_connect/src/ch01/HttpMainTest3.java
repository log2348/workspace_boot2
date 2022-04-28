package ch01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import dto.Post;

public class HttpMainTest3 {

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

			// 외부 라이브러리 이용해서 쉽게 파싱할 수 있다.
			Gson gson = new Gson();
			Post post = gson.fromJson(str, Post.class);
			
			System.out.println(post.id);
			System.out.println(post.userId);
			System.out.println(post.title);
			System.out.println(post.body);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
