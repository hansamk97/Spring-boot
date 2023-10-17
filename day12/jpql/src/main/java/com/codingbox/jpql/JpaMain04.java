package com.codingbox.jpql;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import oracle.net.aso.m;

public class JpaMain04 {

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
		

		//////////////////////////////////////////////
		// 1단계
		
		List resultList = 
			em.createQuery("select m.username, m.age from Member m")
			  .getResultList();
		// 타입을 지정할 수 없기때문에 Object 받아온다
		Object o = resultList.get(0);
		Object[] result = (Object[])o;
		System.out.println("username : " + result[0]);
		System.out.println("age : " + result[1]);
		
		//////////////////////////////////////////////
		// 2단계
		
		List<Object[]> resultList2 = 
			em.createQuery("select m.username, m.age from Member m")
			  .getResultList();
		
		Object[] result2 = resultList2.get(0);
		System.out.println("username : " + result2[0]);
		System.out.println("age : " + result2[1]);
				
		//////////////////////////////////////////////
		// 3단계 - new 명령어로 조회, 제일 권장하는 방법
		
		List<MemberDTO> result3 =
			em.createQuery("select new com.codingbox.jpql.MemberDTO(m.username, m.age) from Member m")
			  .getResultList();
		
		MemberDTO memberDTO = result3.get(0);
		System.out.println("memberDTO : " + memberDTO.getUsername());
		System.out.println("memberDTO : " + memberDTO.getAge());
		
		
		tx.commit();
		em.close();
		emf.close();
	}

}











