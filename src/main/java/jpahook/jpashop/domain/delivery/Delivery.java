package jpahook.jpashop.domain.delivery;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import jpahook.jpashop.domain.embedded.Address;
import jpahook.jpashop.domain.order.Order;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {

	@Id
	@GeneratedValue
	@Column(name = "delivery_id")
	private Long id;
	
	// 지연로딩(LAZY)
	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) // 즉시로딩(EAGER)은 JPQL을 실행할 때 N+1 문제가 자주 발생한다.(지연로딩으로 사용하고 함께 조회 시 fetch join으로 사용 할 것)
	private Order order;
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]

}
