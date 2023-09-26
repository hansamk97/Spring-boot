package com.codingbox.item.domain.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingbox.item.domain.web.dto.Item;
import com.codingbox.item.domain.web.repository.ItemRepository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
// @RequiredArgsConstructor 
// : final 이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다. 
public class BasicItemController {
	
	private final ItemRepository itemRepository;
	
//	@Autowired
	// 이렇게 생성자가 딱 1개만 있으면 스프링이 해동 생성자에게
	// @Autowired로 의존관계 주입해준다.
//	public BasicItemController(ItemRepository itemRepository) {
//		this.itemRepository = itemRepository;
//	}

	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}
	
	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}
	
	@GetMapping("/add")
	public String addForm() {
		return "basic/addForm";
	}
	
	//@PostMapping("/add")
	public String saveItemV1(Model model,
			@RequestParam String itemName,
			@RequestParam int price,
			@RequestParam Integer quantity) {
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setQuantity(quantity);
		
		itemRepository.save(item);
		
		model.addAttribute("item", item);
		return "basic/item";
	}
	
	//@PostMapping("/add")
	public String saveItemV2(Model model,
			@ModelAttribute("item") Item item) {
		// @ModelAttribute 가 해주는 역할
//		Item item = new Item();
//		item.setItemName(itemName);
//		item.setPrice(price);
//		item.setQuantity(quantity);
		
		itemRepository.save(item);
		
		model.addAttribute("item", item);
		return "basic/item";
	}
	
	/*
	 * @ModelAttribute name 생략가능
	 * 즉, model.addAttribute("item", item); 생략가능
	 * 생략시 model에 저장되는 name은 클래스명 첫 글자만 소문자로 등록 Item -> item
	 * ---------------------------------------------------------
	 * @ModelAttribute("hello") Item item
	 * 	-> model.addAttribute("hello", item);
	 */
	//@PostMapping("/add")
	public String saveItemV3(@ModelAttribute Item item) {
		itemRepository.save(item);
		
		//model.addAttribute("item", item);
		return "basic/item";
	}
	
	/*
	 * @ModelAttribute 자체 생략 가능
	 * 권장하지 않음
	 */
	//@PostMapping("/add")
	public String saveItemV4(Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
	//@PostMapping("/add")
	public String saveItemV5(Item item) {
		itemRepository.save(item);
		return "redirect:/basic/items/" + item.getId();
	}
	
	@PostMapping("/add")
	public String saveItemV6(Item item, 
			RedirectAttributes redirectAttributes) {
		Item savedItem = itemRepository.save(item);
		//redirectAttributes.addAttribute("itemId", savedItem);
		redirectAttributes.addAttribute("status", true);
		return "redirect:/basic/items/" + item.getId();
	}
	
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/editForm";
	}
	
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, 
			@ModelAttribute Item item) {
		itemRepository.update(itemId, item);
		return "redirect:/basic/items/{itemId}";
	}
	
	
	
	
	
	
	
	
	// 테스트 데이터 추가
	@PostConstruct
	public void init() {
		//System.out.println("초기화 메서드 실행");
		itemRepository.save(new Item("testA", 100000, 10));
		itemRepository.save(new Item("testB", 200000, 20));
	}
	
	// 종료 메서드
	@PreDestroy
	public void destory() {
		//System.out.println("종료 메서드 호출");
	}
	
	
}










