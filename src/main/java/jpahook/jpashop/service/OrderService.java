package jpahook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpahook.jpashop.domain.delivery.Delivery;
import jpahook.jpashop.domain.item.Item;
import jpahook.jpashop.domain.member.Member;
import jpahook.jpashop.domain.order.Order;
import jpahook.jpashop.domain.order.OrderItem;
import jpahook.jpashop.repository.ItemRepository;
import jpahook.jpashop.repository.MemberRepository;
import jpahook.jpashop.repository.OrderRepository;
import jpahook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;

	/**
	 * �ֹ�
	 */
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		// ��ƼƼ ��ȸ
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);

		// ������� ��ȸ
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());

		// �ֹ���ǰ ����
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

		// �ֹ� ����
		Order order = Order.createOrder(member, delivery, orderItem);

		// �ֹ� ����
		orderRepository.save(order);

		return order.getId();
	}

	/** 
	 * �ֹ� ��� 
	 */
	@Transactional
	public void cancelOrder(Long orderId) {
		// �ֹ� ��ƼƼ ��ȸ
		Order order = orderRepository.findOne(orderId);
		// �ֹ� ���
		order.cancel();
	}
	
	/**
	 * �˻�
	 */
	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAllByString(orderSearch);
	}

}
