package com.codingbox.springprj.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingbox.springprj.domain.Item;
import com.codingbox.springprj.domain.Member;
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
	
	// url : order
	// createForm()
	// return order/orderForm
	@GetMapping("/order")
	public String createForm(Model model) {
		// members의 list
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		
		// items의 list
		List<Item> items = itemService.findItems();
		model.addAttribute("items", items);
		
		
		return "order/orderForm";
	}
	
	@PostMapping("/orders")
	public String order(@RequestParam("memberId") Long memberId,
						@RequestParam("itemId") Long itemId,
						@RequestParam("count") int count) {
		
		orderService.order(memberId, itemId, count);
	
	
		// 추후 상세페이지로 수정
		return "redirect:/order";
	}
	
	// url : orders
	// orderList()
	// return : order/orderList
	
	
}





















