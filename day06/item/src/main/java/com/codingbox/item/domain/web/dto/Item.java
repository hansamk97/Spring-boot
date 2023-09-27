package com.codingbox.item.domain.web.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
	private Long id;
	private String itemName;
	private Integer price;		// null일 수 있다.
	private Integer	quantity;	// null일 수 있다.
	
	// 기능추가
	private Boolean open;			// 판매 여부
	private List<String> regions;	// 등록 지역
	private ItemType itemType;		// 상품 종류
	private String deliveryCode;	// 배송방식
	
	public Item() {
		super();
	}
	
	public Item(String itemName, Integer price, Integer quantity) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	
}















