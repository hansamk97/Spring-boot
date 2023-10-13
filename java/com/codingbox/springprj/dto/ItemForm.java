package com.codingbox.springprj.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm {

	@Id
	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
}
