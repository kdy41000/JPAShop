package jpahook.jpashop.domain.embedded;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable // ����Ÿ�� Ȱ��ȭ
@Getter
public class Address {

	private String city;
	private String street;
	private String zipcode;
	
	protected Address() {
		
	}

	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
}
