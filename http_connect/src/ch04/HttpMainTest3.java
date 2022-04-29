package ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dto.Post;

public class HttpMainTest3 {

	public static void main(String[] args) {
		
		try {
			// 준비물 1 (http 통신을 하기 위한)
			URL url = new URL("https://jsonplaceholder.typicode.com/posts");
			// 준비물 2
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 부가적인 정보를 추가해서 보내기 !!!
			connection.setRequestMethod("GET");
			connection.connect();

			int statusCode = connection.getResponseCode();
			//System.out.println("statusCode : " + statusCode);
			
			// http 통신할 때 스트림 달아야 한다.
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line = null;
			
			if(statusCode == 200) {
				while((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			}
			
			String str = sb.toString();
			System.out.println(str);
			
			// 파싱 처리
			Gson gson = new Gson();
			Post[] posts = new Post[5];
			Type postListType = new TypeToken<ArrayList<Post>>() {}.getType();
			
			ArrayList<Post> arrayList = gson.fromJson(str, postListType);
			for (Post post : arrayList) {
				System.out.println(post);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
