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
	
	// �����ε�(LAZY)
	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) // ��÷ε�(EAGER)�� JPQL�� ������ �� N+1 ������ ���� �߻��Ѵ�.(�����ε����� ����ϰ� �Բ� ��ȸ �� fetch join���� ��� �� ��)
	private Order order;
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status; //ENUM [READY(�غ�), COMP(���)]

}
