package ch02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor
public class MemberDto {

	private String memberId;
	private String memberName;
	private String memberAddress;

}
