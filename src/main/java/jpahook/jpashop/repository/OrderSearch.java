package jpahook.jpashop.repository;

import jpahook.jpashop.domain.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

	private String memberName;  // ȸ���̸�
	private OrderStatus orderStatus; // �ֹ�����(ORDER, CANCEL)
}
