package com.codingbox.querydsl.entity;

import lombok.Data;

@Data
public class MemberDTO {
	private String username;
	private int age;
	
	public MemberDTO() {}

	public MemberDTO(String username, int age) {
		super();
		this.username = username;
		this.age = age;
	}
	
	
}



