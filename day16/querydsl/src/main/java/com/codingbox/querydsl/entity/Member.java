package com.codingbox.querydsl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of= {"id","username","age"})
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	private String username;
	
	private int age;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	//lombok처리
	//protected Member() {}
	
	public Member(String username, int age) {
		this(username, age, null);
	}
	
	public Member(String username) {
		this(username, 0);
	}

	public Member(String username, int age, Team team) {
		super();
		this.username = username;
		this.age = age;
		//this.team = team;
		if(team != null) {
			changeTeam(team);
		}
	}
	
	public void changeTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);
	}

	//lombok처리
//	@Override
//	public String toString() {
//		return "Member [id=" + id + 
//				", username=" + username + 
//				", age=" + age + "]";
//	}
	
}


















