package com.codingbox.springprj.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingbox.springprj.domain.Item;
import com.codingbox.springprj.domain.Member;
import com.codingbox.springprj.domain.Order;
import com.codingbox.springprj.repository.OrderSearch;
import com.codingbox.springprj.service.ItemService;
import com.codingbox.springprj.service.MemberService;
import com.codingbox.springprj.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService; 
	private final MemberService memberService;
	private final ItemService itemService;

	@GetMapping("/order")
	public String createForm(Model model) {
		// members list
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		
		// items list
		List<Item> items = itemService.findItems();
		model.addAttribute("items", items);
		
		return "order/orderForm";
	}
	
	@PostMapping("/order")
	public String order(@RequestParam("memberId")Long memberId,
						@RequestParam("itemId")Long itemId,
						@RequestParam("count")int count) {
		
		orderService.order(memberId, itemId, count);
		
		return "redirect:/orders";
	}
	
	// 상세페이지 조회
	@GetMapping("/orders")
	public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch
			,Model model) {
		List<Order> orders = orderService.findOrders(orderSearch);
		model.addAttribute("orders", orders);
		return "order/orderList";
	}
	
	@PostMapping("/orders/{orderId}/cancel")
	public String cancelOrder( @PathVariable("orderId")Long orderId ) {
		orderService.cancelOrder(orderId);
		return "redirect:/orders";
	}
	
}














