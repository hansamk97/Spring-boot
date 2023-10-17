package com.codingbox.jpql;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import oracle.net.aso.m;

public class JpaMain07 {

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
		
		em.flush();
		em.clear();
		
		// 서브쿼리
		String jpql = "select m from Member m where m.age "
					+ " > (select avg(m2.age) from Member m2)";
		List<Member> resultList = em.createQuery(jpql, Member.class)
									.getResultList();
		
		
		
		
		
		tx.commit();
		em.close();
		emf.close();
	}

}











