package jpahook.jpashop.domain.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import jpahook.jpashop.domain.embedded.Address;
import jpahook.jpashop.domain.order.Order;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	
	private String name;
	
	@Embedded  // ����Ÿ��
	private Address address;  // city, street, zipcode
	
	@OneToMany(mappedBy = "member")    // order���̺��� member�ʵ�� �������� ����(�ϴ�ٿ��� �� �ʿ� mappedBy���� �� �ʿ� @JoinColumn����)
	private List<Order> orders = new ArrayList<>();
}
