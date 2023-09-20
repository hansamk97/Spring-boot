package com.codingbox.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



/*
 * @Controller : Controller 역할을 하는 class에 붙여준다.
 * 			Controller에서 mapping url을 찾는다.
 * 			현재 : localhost:9090/hello
 */
@Controller
public class HelloController {
	
	/*
	 * @GetMapping : get방식의 요청
	 */
	@GetMapping("/hello")
	public String hello(Model model) {
		System.out.println("hello 도착");
		model.addAttribute("data", "hello!!!");
		return "hello";	// hello.html 화면을 찾아서 return
		/*
		 * 컨트롤러에서 리턴 값으로 문자를 반환하면 
		 * 뷰 리졸버(viewRewolver)가 화면을 찾아서 처리한다.
		 *  - 스프링 부트 템플릿엔진 기본 viewName 매핑
		 *  - resources:templates/+{viewName}+.html
		 */
	}
	
	/*
	 * @RequestParam("name")
	 *  -> request.getParameter("name")
	 * **옵션** 
	 *  required : 파라미터 값 필수 여부, true-필수(default), fasle-필수 아님
	 *  defaultValue : 파라미터 값이 없을 경우 기본으로 들어갈 값
	 */
	// http://localhost:9090/hello-mvc?name=Spring
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam(value="name",required = false, defaultValue = "required test") String username,
			Model model) {
		model.addAttribute("modelvalue", username);
		return "hello-template";
	}
	
	
}






