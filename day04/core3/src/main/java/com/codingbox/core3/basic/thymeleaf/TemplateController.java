package com.codingbox.core3.basic.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Controller로 사용
 * class 레벨에서 /template 로 매핑
 * ---------------------------
 * template(), fragment 로 매핑,
 * String return "template/fragment/fragmentMain" 
 */

@Controller
@RequestMapping("/template")
public class TemplateController {
	
	@GetMapping("/fragment")
	public String template(){
		return "template/fragment/fragmentMain";
	}

	
}




