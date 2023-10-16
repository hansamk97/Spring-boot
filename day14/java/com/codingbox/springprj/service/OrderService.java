package com.codingbox.springprj.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.springprj.domain.Item;
import com.codingbox.springprj.domain.Member;
import com.codingbox.springprj.domain.Order;
import com.codingbox.springprj.domain.OrderItem;
import com.codingbox.springprj.repository.ItemRepository;
import com.codingbox.springprj.repository.MemberRepository;
import com.codingbox.springprj.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		// jpa 영속성 컨텍스트 영역 들어오기
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);
		
		// 주문상품
		OrderItem orderItem = 
				OrderItem.createOrderItem(item,item.getPrice(), count);
				
		
		// 주문생성
		Order order = Order.createOrder(member, orderItem);
		
		
		// 주문저장
		orderRepository.save(order);
		return order.getId();
		
	}

	// 검색
	// List<>, findOrders()
	
	
}




















