package com.codingbox.jpql;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import oracle.net.aso.m;

public class JpaMain03 {

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
		
		List<Member> result 
			= em.createQuery("select m from Member m",Member.class)
				.getResultList();
		
		Member findMember = result.get(0);
		findMember.setAge(20);
		
		////////////////////////////////////////////////
		/*
		 * jpql입장에서는 select문이 나가지만, 실제 sql입장에서는
		 * member와 team을 조인해서 결과를 찾아야 한다.
		 * 근데 join은 실제 sql문과 비슷하게 작성해야 한다. 
		 */
		List<Team> result2 = 
			em.createQuery("select m.team from Member m",Team.class)
			  .getResultList();
		
		List<Team> result3 = 
				em.createQuery("select t from Member m join m.team t",Team.class)
				  .getResultList();
			
		//////////////////////////////////////////////////////
		
		em.createQuery("select o.address from Order o", Address.class)
		  .getResultList();
		
//		em.createQuery("select o.address from Address o", Address.class)
//		  .getResultList();
		
		/////////////////////////////////////////////////////////
		
		em.createQuery("select distinct m.username, m.age from Member m")
		  .getResultList();
		
		
		tx.commit();
		em.close();
		emf.close();
	}

}











