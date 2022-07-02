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
	
	@Embedded  // 내장타입
	private Address address;  // city, street, zipcode
	
	@OneToMany(mappedBy = "member")    // order테이블의 member필드와 연관관계 설정(일대다에서 일 쪽에 mappedBy설정 다 쪽에 @JoinColumn설정)
	private List<Order> orders = new ArrayList<>();
}
