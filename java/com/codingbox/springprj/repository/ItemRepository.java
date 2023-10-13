package com.codingbox.springprj.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.codingbox.springprj.domain.Item;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;
	
	// 저장
	// void, save()
	public void save(Item item) {
		em.persist(item);
	}

	// return arrayList
	// jpql
	// findAll()
	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
				 .getResultList(); 
	}

	
	

}


















