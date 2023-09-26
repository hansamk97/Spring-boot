package com.codingbox.item.domain.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
	private Long id;
	private String itemName;
	private Integer price;		// null일 수 있다.
	private Integer	quantity;	// null일 수 있다.
	
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















