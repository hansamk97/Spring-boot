package com.codingbox.springprj.controller;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderForm {

	@Id
	private Long id;
	
	private String member;
	private String item;
}
