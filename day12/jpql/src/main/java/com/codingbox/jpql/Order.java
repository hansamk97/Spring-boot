package com.codingbox.jpql;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "Orders")
public class Order {

	@Id @GeneratedValue
	private Long id;
	private int orderAmount;

	@Embedded
	private Address address;
	
}












