package ch01_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import dto.Post;

public class HttpMainTest2 {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/posts/20");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // URLConnection ->
																						// HttpURLConnection 다운캐스팅
			connection.setRequestMethod("GET");
			connection.connect();

			int statusCode = connection.getResponseCode();
			System.out.println("statusCode : " + statusCode);

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line = null;

			if (statusCode == 200) {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} else if (statusCode == 404) {
				System.out.println("네트워크 연결이 불안정합니다.");
			}

			String str = sb.toString();
			System.out.println(str);

			// 문자열을 파싱해서 post 객체에 담고 값을 출력하기
			// Post post = new Post();

			// 일일이 파싱하기 힘듦
			String userId = str.substring(11, 12);

			// Gson 라이브러리 이용해서 쉽게 파싱할 수 있다.
			Gson gson = new Gson();
			// fromJson - Json을 Object로
			// toJson - Object를 Json으로
			Post post = gson.fromJson(str, Post.class);

			System.out.println("userId : " + post.userId);
			System.out.println("id : " + post.id);
			System.out.println("title : " + post.title);
			System.out.println("body : " + post.body);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
