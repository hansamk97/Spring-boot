package com.codingbox.core2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// 웰컴파일 -> static/index.html
	// localhost:9090
	@GetMapping("/")
	public String home() {
		return "home";
	}
}












