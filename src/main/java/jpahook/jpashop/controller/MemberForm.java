package jpahook.jpashop.controller;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

	@NotEmpty(message = "회원 이름은 필수 입니다.")  // @Valid가 붙으면 @NotEmpty가 붙은 필드를 유효성 검사한다.(유효성 실패시 해당 message를 해당 필드객체에 담는다.)
	private String name;
	
	private String city;
	private String street;
	private String zipcode;
	
}
