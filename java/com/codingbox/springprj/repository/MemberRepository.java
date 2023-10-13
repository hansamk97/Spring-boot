package com.codingbox.springprj.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codingbox.springprj.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
//import jakarta.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	// jpa 지원해주는 표준
	// spring이 entityManager를 만들어서 em에 주입해준다.
	/*
	 *  @PersistenceContext이 있어야 표준 EntityManager 주입이 가능하다.
	 *  그러나 spring의 @Autowired가 주입이 되도록 이러한 지원을 해준다.
	 *   -> jpa에 대한 주입을 받을 수 있게 된다.
	 */
//	@PersistenceContext
	@Autowired
	private final EntityManager em;

	
	// 생성자 주입
//	private MemberRepository(EntityManager em) {
//		this.em = em;
//	}

	// 저장
	public void save(Member member) {
		em.persist(member);
	}
	
	// 조회
	// 메서드 이름 : findAll()
	// return arrayList
	// jpql
	public List<Member> findAll() {
//		em.createQuery("select m from Member m", Member.class)
//		  .getResultList();
//		return result;
		return em.createQuery("select m from Member m", Member.class)
				 .getResultList();
	}

	public List<Member> findByname(String name) {
		
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				 .setParameter("name", name)
				 .getResultList();
				 
	}
		
}










