package ch03_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dto.Post;

public class MainTest1 {

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

			// 파싱 처리
			Gson gson = new Gson();
			Post[] posts = new Post[5];
			// 오브젝트를 arrayList로 감싸고 그 타입을 가져오겠다는
			// getType() 메소드 사용해서 배열 안의 오브젝트 구조 만들어준다.
			Type postListType = new TypeToken<ArrayList<Post>>() {
			}.getType();

			ArrayList<Post> arrayList = gson.fromJson(str, postListType);
			for (Post post : arrayList) {
				System.out.println(post);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
