package com.codingbox.core3.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
	private String username;
	private int age;
	
	public User(String username, int age) {
		super();
		this.username = username;
		this.age = age;
	}	
}




