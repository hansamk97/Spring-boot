package com.codingbox.jpql;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Member member = new Member();
		member.setUsername("member1");
		em.persist(member);
		/*
		 * 두번째 파라미터로 응답 클래스에 대한 type정보를 줄 수 있다
		 */
		TypedQuery<Member> query = 
			em.createQuery("select m from Member m", Member.class);
		
		// 타입정보가 String.class로 반환타입이 명확할때
		TypedQuery<String> query2 =
			em.createQuery("select m.username from Member m",String.class);
		
		// String, int : 이렇게 반환타입이 명확하지 않을 때
		Query query3 = 
			em.createQuery("select m.username, m.age from Member m");
		
		TypedQuery<Member> query4 = 
				em.createQuery("select m from Member m", Member.class);
		List<Member> resultList = query4.getResultList();
		
		for( Member m : resultList ) {
			System.out.println("m = " + m);
		}
		
		TypedQuery<Member> query5 =
			em.createQuery("select m from Member m where m.id = 10", Member.class);
		Member result = query5.getSingleResult();
		System.out.println("result : " + result);
		
		tx.commit();
		em.close();
		emf.close();
	}

}











