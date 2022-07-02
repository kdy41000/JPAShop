package jpahook.jpashop.repository;

import jpahook.jpashop.domain.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

	private String memberName;  // 회원이름
	private OrderStatus orderStatus; // 주문상태(ORDER, CANCEL)
}
