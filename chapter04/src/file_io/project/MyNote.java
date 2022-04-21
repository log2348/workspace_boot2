package file_io.project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// 응답자
public class MyNote implements WriteNote{

	public MyNote() {
		new SubNote(this);
	}

	@Override
	public void saveNote(String text) {
		System.out.println("파일을 저장합니다.");

		try(BufferedWriter bw = new BufferedWriter(new FileWriter("note.txt", true))) {
			bw.write(text);
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
