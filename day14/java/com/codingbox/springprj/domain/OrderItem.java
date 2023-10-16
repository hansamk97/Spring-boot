package com.codingbox.springprj.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {

	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long id;
	
	private int orderPrice;
	private int count;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	// ======== 비즈니스 로직 =========
	public static OrderItem createOrderItem(Item item, int price, int count) {
		// 여기서 price는 할인, 쿠폰 등.. 가격에 변동이 생겼을 때 비즈니스 로직 작성 필요
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(price);;
		orderItem.setCount(count);
		
		// 주문한 만큼 재고 조정
		item.removeStock(count);
		
		return orderItem;
		
	}
	
	
	
}
























