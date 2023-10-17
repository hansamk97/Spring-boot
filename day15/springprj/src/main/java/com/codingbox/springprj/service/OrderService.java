package com.codingbox.springprj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.springprj.domain.Item;
import com.codingbox.springprj.domain.Member;
import com.codingbox.springprj.domain.Order;
import com.codingbox.springprj.domain.OrderItem;
import com.codingbox.springprj.repository.ItemRepository;
import com.codingbox.springprj.repository.MemberRepository;
import com.codingbox.springprj.repository.OrderRepository;
import com.codingbox.springprj.repository.OrderSearch;

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
		// jpa영속성 컨텍스트 영역 들어오기
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);				
		
		// 주문 상품
		OrderItem orderItem 
			= OrderItem.createOrderItem(item, item.getPrice(), count);
		
		// 주문 생성
		Order order = Order.createOrder(member, orderItem);
		
		// 주문 저장
		orderRepository.save(order);
		return order.getId();
	}

	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAll(orderSearch);
	}

	@Transactional
	public void cancelOrder(Long orderId) {
		// 영속성 컨텍스트 
		Order order = orderRepository.findOne(orderId);
		
		// 주문 취소
		/*
		 * 주문 취소시 수량 update, 상태값 변경에 처리를 각각 해줘야 하나
		 * 데이터들만 바꿔주면 jpa는 해당 값들을 변경체를 하고(더디체킹, 변경내역 감지)
		 * 변경내역 감지가 변경된 내용들을 다 찾아서 데이터베이스에 업데이트 쿼리가 전송
		 * 여기서 Order의 상태변경 update, Item의 stockQuantity가 변경된다.
		 */
		order.cancel();
	}
	
}













