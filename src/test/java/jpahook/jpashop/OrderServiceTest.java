package jpahook.jpashop;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpahook.jpashop.domain.embedded.Address;
import jpahook.jpashop.domain.item.Book;
import jpahook.jpashop.domain.member.Member;
import jpahook.jpashop.domain.order.Order;
import jpahook.jpashop.domain.order.OrderStatus;
import jpahook.jpashop.exception.NotEnoughStockException;
import jpahook.jpashop.repository.OrderRepository;
import jpahook.jpashop.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired
	EntityManager em;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	private Member createMember(String name) {
		Member member = new Member();
		member.setName(name);
		member.setAddress(new Address("����", "���", "123-123"));
		em.persist(member);
		return member;
	}
	
	private Book createBook(String name, int price, int quantity) {
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(quantity);
		em.persist(book);
		return book;
	}
	
	@Test
	public void ��ǰ�ֹ�() throws Exception {
		//given
		Member member = createMember("ȸ��1");
		
		Book book = createBook("�ð� JPA", 10000, 10);
		
		//when
		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertEquals("��ǰ �ֹ��� ���´� ORDER", OrderStatus.ORDER, getOrder.getStatus()); // static import
		assertEquals("�ֹ��� ��ǰ ���� ���� ��Ȯ�ؾ� �Ѵ�.", 1, getOrder.getOrderItems().size()); // static import
		assertEquals("�ֹ� ������ ���� * �����̴�.", 10000 * orderCount, getOrder.getTotalPrice()); // static import
		assertEquals("�ֹ� ������ŭ ��� �پ�� �Ѵ�.", 8, book.getStockQuantity()); // static import
	}
	
	@Test
	public void ��ǰ�ֹ�_�������ʰ�() throws Exception {
		//given
		Member member = createMember("ȸ��1");
		
		Book book = createBook("�ð� JPA", 10000, 10);
		
		//when
		int orderCount = 11;
		orderService.order(member.getId(), book.getId(), orderCount);
		
		//then
		try {
			fail("��� ���� ���� ���ܰ� �߻��ؾ��Ѵ�.");
		} catch (NotEnoughStockException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void �ֹ����() throws Exception {
		//given
		Member member = createMember("ȸ��1");
		
		Book book = createBook("�ð� JPA", 10000, 10);
		
		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//when
		orderService.cancelOrder(orderId);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertEquals("�ֹ� ��ҽ� ���´� CANCEL �̴�.", OrderStatus.CANCEL, getOrder.getStatus());
		assertEquals("�ֹ��� ��ҵ� ��ǰ�� �׸�ŭ ��� �����ؾ� �Ѵ�.", 10, book.getStockQuantity());
	}
	
	
}
