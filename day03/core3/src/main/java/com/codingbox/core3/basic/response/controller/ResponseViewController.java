package com.codingbox.core3.basic.response.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

	/* ModelAndView
	 *  - DispatcherServlet에 의해 처리될 뷰를 직접 지정할 수 있고
	 *  Model부분에 있는 데이터를 전달 할 수 있도록 한다.
	 */
	@RequestMapping("/response-view-v1")
	public ModelAndView responseViewV1() {
		ModelAndView mav 
			= new ModelAndView("response/hello")
				 	.addObject("data", "datavalue");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/response-view-v2")
	public String responseViewV2(Model model) {
		model.addAttribute("data", "hello!!!");
		return "response/hello";
	}
	
	
	
}










