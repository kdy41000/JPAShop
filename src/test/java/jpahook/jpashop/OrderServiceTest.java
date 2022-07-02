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
		member.setAddress(new Address("서울", "경기", "123-123"));
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
	public void 상품주문() throws Exception {
		//given
		Member member = createMember("회원1");
		
		Book book = createBook("시골 JPA", 10000, 10);
		
		//when
		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus()); // static import
		assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size()); // static import
		assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, getOrder.getTotalPrice()); // static import
		assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity()); // static import
	}
	
	@Test
	public void 상품주문_재고수량초과() throws Exception {
		//given
		Member member = createMember("회원1");
		
		Book book = createBook("시골 JPA", 10000, 10);
		
		//when
		int orderCount = 11;
		orderService.order(member.getId(), book.getId(), orderCount);
		
		//then
		try {
			fail("재고 수량 부족 예외가 발생해야한다.");
		} catch (NotEnoughStockException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void 주문취소() throws Exception {
		//given
		Member member = createMember("회원1");
		
		Book book = createBook("시골 JPA", 10000, 10);
		
		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//when
		orderService.cancelOrder(orderId);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, getOrder.getStatus());
		assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, book.getStockQuantity());
	}
	
	
}
