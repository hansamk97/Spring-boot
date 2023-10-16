package com.codingbox.springprj.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.springprj.domain.Item;
import com.codingbox.springprj.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;
	
	// saveItem(), void
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	// return arrayList
	// findItems()
	public List<Item> findItems() {
		return itemRepository.findAll();
	}

	
	// item 하나 조회
	// 메서드 : findOne
	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}

	@Transactional
	public Item updateItem(Long itemId, Item item) {
		// itemId를 기반으로 실제 영속상태를 확실시 해야한다.
		// 	-> 그 후 변경감지(Dirty Checking)를 통해서 해당 변경 내용을 set만 해주면 된다.
		Item findItem = itemRepository.findOne(itemId);	// 이렇게 select구문을 날려주면 확실하게 영속상태로 넘어간다.
		findItem.setPrice(item.getPrice());
		findItem.setName(item.getName());
		findItem.setStockQuantity(item.getStockQuantity());
		/*
		 * 영속성 컨텍스트 영역으로 들어온 뒤, 영속성 컨텍스트에 변화가 일어나게 되고,
		 * 변화가 일어나는 것을 JPA가 감지한다.
		 * flush는 변화가 생긴 부분을 감지 후, update 요청
		 *  => 이 일련의 과정이 변경감지에 의한 데이터를 update하는 방법이다.
		 */
		
		return findItem;
	}
	@Transactional
	public void updateItem(Long itemId, String name, int price, int stockQuantity) {
		Item findItem = itemRepository.findOne(itemId);
		findItem.setName(name);
		findItem.setPrice(price);
		findItem.setStockQuantity(stockQuantity);
		// update되는 데이터가 많다면 서비스 계층에서 업데이트만을 위한 DTO를 따로 만들어도 된다.
	}
	
}










