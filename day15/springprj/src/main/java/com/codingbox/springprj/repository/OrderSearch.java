package com.codingbox.springprj.repository;

import com.codingbox.springprj.domain.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
	// 회원 이름
	private String memberName;
	// 주문상태[ORDER, CANCLE]
	private OrderStatus orderStatus;
}












