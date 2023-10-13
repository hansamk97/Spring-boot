package com.codingbox.springprj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	//Logger log = LoggerFactory.getLogger(getClass());
	
	// home 화면
	// "/", -> home.html
	@RequestMapping("/")
	public String home() {
		log.info("home Controller");
		return "home";
	}
	
}
