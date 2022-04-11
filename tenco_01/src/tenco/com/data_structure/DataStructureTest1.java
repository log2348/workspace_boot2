package tenco.com.data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataStructureTest1 {

	public static void main(String[] args) {
		/*
		 * List
		 * 중간에 데이터를 추가, 삭제 하는 게 용이하다.
		 * 순서가 있고(인덱스) 중복이 가능하다.
		 * 
		 */		
		List list0; // 인터페이스	

		// 선언 방법
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		
		// 선언과 동시에 초기화
		ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 3));
		
		// 값 추가 방법
		list3.add(4);
		list3.add(null);
		System.out.println(list3);
		
		list3.add(1, 10); // 나머지 요소들은 자동으로 뒤로 밀림
		System.out.println(list3);
		
		// 삭제
		list3.remove(3);
		System.out.println(list3);
		//전체 삭제
		// list3.clear();
		
		// 출력 방법
		System.out.println(list3.get(3));
		
		// 추가적인 메소드 확인
		System.out.println(list3.size());
		System.out.println(list3.isEmpty());
		
		// for
		for (Integer i : list3) {
			System.out.println(i);
		}
		
		// while
		// 요소 순회(반복자) 컬렉션 프레임워크에 저장된 요소들을 하나씩 차례로 참조한다.
		Iterator<Integer> iterator = list3.iterator();
		while(iterator.hasNext()) {
			// hasNext() - 다음 요소가 있으면 true, 없으면 false 반환
			System.out.println("while : " + iterator.next());
		}
		
		// 검색 기능 - 값을 포함하고 있으면 true, 없으면 false
		System.out.println(list3.contains(500));
		// indexOf - 값이 있으면 인덱스 번호를 반환, 없으면 -1 반환
		System.out.println(list3.indexOf(0));
		System.out.println(list3.indexOf(1));
		System.out.println(list3.indexOf(2));
		System.out.println(list3.indexOf(4));
		System.out.println(list3.indexOf(100));
		
	}

}
