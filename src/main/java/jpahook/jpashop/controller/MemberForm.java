package jpahook.jpashop.controller;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

	@NotEmpty(message = "ȸ�� �̸��� �ʼ� �Դϴ�.")  // @Valid�� ������ @NotEmpty�� ���� �ʵ带 ��ȿ�� �˻��Ѵ�.(��ȿ�� ���н� �ش� message�� �ش� �ʵ尴ü�� ��´�.)
	private String name;
	
	private String city;
	private String street;
	private String zipcode;
	
}
