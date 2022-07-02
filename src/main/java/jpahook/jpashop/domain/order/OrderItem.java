package jpahook.jpashop.domain.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jpahook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {

	@Id
	@GeneratedValue
	@Column(name = "order_item_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY) // 다대일 관계에서는 연관된 데이터를 다 끌고 올 수 있어 LAZY로 무조건 설정해야 한다.
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	private int orderPrice; // 주문 가격
	private int count; // 주문 수량

	// ==생성 메서드==//
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);
		item.removeStock(count);
		return orderItem;
	}

	// ==비즈니스 로직==//
	/** 주문 취소 */
	public void cancel() {
		getItem().addStock(count);
	}

	// ==조회 로직==//
	/** 주문상품 전체 가격 조회 */
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}
}
