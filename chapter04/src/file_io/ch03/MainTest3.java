package file_io.ch03;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

// D - X까지만 파일에 쓰기
public class MainTest3 {

	public static void main(String[] args) throws FileNotFoundException{
		FileOutputStream fos = new FileOutputStream("output_b.txt", true);
		
		try(fos) {

			byte[] bs = new byte[26];
			byte data = 65;
			for (int i = 0; i < bs.length - 5; i++) {
				bs[i] = data;
				data++;
			}
			fos.write(bs, 3, 23);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(">>>> 출력 처리가 끝 <<<<");

	}

}
