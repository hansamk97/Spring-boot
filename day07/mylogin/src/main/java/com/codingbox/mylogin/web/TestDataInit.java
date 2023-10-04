package com.codingbox.mylogin.web;

import org.springframework.stereotype.Component;

import com.codingbox.mylogin.domain.member.Member;
import com.codingbox.mylogin.domain.member.MemberRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDataInit {
	
	private final MemberRepository memberRepository;

	@PostConstruct
	public void init() {
		Member member = new Member();
		member.setLoginId("test");
		member.setPassword("test1234");
		member.setName("테스트user");
		memberRepository.save(member);
	}
}











