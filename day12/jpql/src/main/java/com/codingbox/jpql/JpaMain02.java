package com.codingbox.jpql;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import oracle.net.aso.m;

public class JpaMain02 {

	public static void main(String[] args) {
		EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Member member = new Member();
		member.setUsername("member1");
		member.setAge(10);
		em.persist(member);
		
		// 파라미터 바인딩
		TypedQuery<Member> query = 
			em.createQuery("select m from Member m where m.username = :username"
							,Member.class);
		query.setParameter("username", "member1");
		Member result = query.getSingleResult();
		System.out.println("result : " + result.getUsername());
		
		// 파라미터 바인딩 - 메세지 체인방법 
		Member result2 =
			em.createQuery("select m from Member m where m.username = :username",Member.class)
			  .setParameter("username", "member1")
			  .getSingleResult();
		System.out.println("result2 : " + result2.getUsername());
		
		
		tx.commit();
		em.close();
		emf.close();
	}

}











