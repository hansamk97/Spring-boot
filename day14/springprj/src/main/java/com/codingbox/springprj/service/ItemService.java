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

	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	public List<Item> findItems() {
		return itemRepository.findAll();
	}

	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}

	@Transactional
	public Item updateItem(Long itemId, Item item) {
		// itemId를 기반으로 실제 영속상태를 확실시 
		// -> 변경감지를 통해서 해당 변경 내용을 set만 해주면 된다.
		Item findItem = itemRepository.findOne(itemId);
		findItem.setPrice(item.getPrice());
		findItem.setName(item.getName());
		findItem.setStockQuantity(item.getStockQuantity());
		
		/*
		 * 영속성 컨텍스트 영역으로 들어온 뒤,
		 * 영속성 컨텍스트에 변화가 일어나게 되고, 변화가 일어나는 것을 JPA가 감지한다.
		 * 영속성 컨텍스트에서 flush를 호출
		 * flush는 변화가 생긴 부분을 감지 후 -> update 요청
		 * => 변경 감지에 의한 데이터를 update하는 방법 
		 */
		
		return findItem; 
	}
	
	@Transactional
	public void updateItem(Long itemId, String name, int price, int stockQuantity) {
		Item findItem = itemRepository.findOne(itemId);
		findItem.setName(name);
		findItem.setPrice(price);
		findItem.setStockQuantity(stockQuantity);
		// update되는 데이터가 많다면, 서비스 계층에서 업데이트만을 위한 DTO를 따로 만들어도 된다. 
	}
	
	
}













