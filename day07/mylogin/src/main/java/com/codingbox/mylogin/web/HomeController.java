package com.codingbox.mylogin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.codingbox.mylogin.domain.member.Member;
import com.codingbox.mylogin.domain.member.MemberRepository;
import com.codingbox.mylogin.web.session.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final MemberRepository memberRepository;

//	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	/*
	 * memberId 		-> 쿠키의 key값으로 get
	 * required = false -> 로그인 안한 사용자도 접속 가능
	 */
	//@GetMapping("/")
	public String homeLogin(@CookieValue(name="memberId",required = false)Long memberId,
			Model model) {
		
		// 로그인한 사용자가 아니라면 home으로 보낸다.
		if(memberId == null ) {
			return "home";
		}
		
		// db 조회 한 후, 사용자가 없으면 null처리, home화면으로 return
		Member loginMember = memberRepository.findById(memberId);
		if( loginMember == null ) {
			return "home";
		}
		
		// 로그인 성공한 사람 : loginHome
		model.addAttribute("member", loginMember);
		return "loginHome";
		
	}
	
	//@GetMapping("/")
	public String homeLoginV2(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false);
		if( session == null ) {
			return "home";
		}
		
		Member loginMember 
			= (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
		
		// 세션에 회원 데이터가 없으면 home
		if( loginMember == null ) {
			return "home";
		}
		
		// db 조회
		// return "home"
		
		// 로그인 성공한 사람 : loginHome
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
	
	// @SessionAttribute : session객체를 뒤져서 name 속성값 기준으로 찾는다.
	// 	required = false : session값이 없을 수도 있다.
	@GetMapping("/")
	public String homeLoginV3(
		@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false)Member loginMember,
		Model model) {
		
		// 세션에 회원 데이터가 없으면 home
		if( loginMember == null ) {
			return "home";
		}
		
		// 로그인 성공한 사람 : loginHome
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
	
}
















