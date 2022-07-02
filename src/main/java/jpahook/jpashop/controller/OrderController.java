package jpahook.jpashop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jpahook.jpashop.domain.item.Item;
import jpahook.jpashop.domain.member.Member;
import jpahook.jpashop.domain.order.Order;
import jpahook.jpashop.repository.OrderSearch;
import jpahook.jpashop.service.ItemService;
import jpahook.jpashop.service.MemberService;
import jpahook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final MemberService memberServce;
	private final ItemService itemService;

	@GetMapping("/order")
	public String createForm(Model model) {
		List<Member> members = memberServce.findMembers();
		List<Item> items = itemService.findItems();

		model.addAttribute("members", members);
		model.addAttribute("items", items);

		return "order/orderForm";
	}

	@GetMapping(value = "/orders")
	public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
		List<Order> orders = orderService.findOrders(orderSearch);

		model.addAttribute("orders", orders);
		return "order/orderList";
	}

	@PostMapping("/order")
	public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId,
			@RequestParam("count") int count) {
		orderService.order(memberId, itemId, count);
		return "redirect:/orders";
	}
	
	@PostMapping("/orders/{orderId}/cancel")
	public String cancelOrder(@PathVariable("orderId") Long orderId) {
		orderService.cancelOrder(orderId);
		return "redirect:/orders";
	}
}
