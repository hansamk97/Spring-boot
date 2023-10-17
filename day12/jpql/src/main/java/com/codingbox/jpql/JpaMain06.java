package com.codingbox.jpql;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import oracle.net.aso.m;

public class JpaMain06 {

	public static void main(String[] args) {
		EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Team team = new Team();
		team.setName("teamA");
		em.persist(team);
		
		Member member = new Member();
		member.setUsername("member1");
		member.setAge(10);
		member.changeTeam(team);
		em.persist(member);
		
		// 조인
		String jpql = "select m from Member m inner join m.team t";
		List<Member> resultList = em.createQuery(jpql, Member.class)
									.getResultList();
		
		String jpql2 = "select m from Member m inner join m.team t where t.name = :name";
		List<Member> resultList2 = em.createQuery(jpql, Member.class)
									.getResultList();
		
		System.out.println("----------------------");
		// left outer join
		String jpql3 = "select t from Member m left outer join m.team t";
		List<Member> resultList3 = em.createQuery(jpql3, Member.class)
									 .getResultList();
		
		
		
		
		
		tx.commit();
		em.close();
		emf.close();
	}

}











