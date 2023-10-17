package com.codingbox.jpql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Member {

	@Id @GeneratedValue
	private Long id;
	private String username;
	private int age;
	
	// 연관관계
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;
	
	// 연관관계 메서드
	public void changeTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);
	}
	
}












