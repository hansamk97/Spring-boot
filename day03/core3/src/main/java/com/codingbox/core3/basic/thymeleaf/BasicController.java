package com.codingbox.core3.basic.thymeleaf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingbox.core3.basic.User;

@Controller
@RequestMapping("/basic")
public class BasicController {

	@GetMapping("text-basic")
	public String textBasic(Model model) {
		model.addAttribute("data", "Hello Spring!");
		//model.addAttribute("data", "<b>Hello Spring!</b>");
		return "basic/text-basic";
	}
	
	@GetMapping("text-unescaped")
	public String textUnescaped(Model model) {
		model.addAttribute("data", "<b>Hello Spring!</b>");
		return "basic/text-unescaped";
	}
	
	@GetMapping("/variable")
	public String variable(Model model) {
		User userA = new User("UserA", 10);
		User userB = new User("UserB", 20);
		
		List<User> list = new ArrayList<>();
		list.add(userA);
		list.add(userB);
		
		Map<String, User> map = new HashMap<String, User>();
		map.put("userA", userA);
		map.put("userB", userB);
		
		model.addAttribute("user", userA);
		model.addAttribute("users", list);
		model.addAttribute("userMap", map);
		
		return "basic/variable";
	}
	
}













