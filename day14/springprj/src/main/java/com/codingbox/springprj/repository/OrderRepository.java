package com.codingbox.springprj.repository;

import org.springframework.stereotype.Repository;

import com.codingbox.springprj.domain.Order;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}

	// findAll(), List<>
}














