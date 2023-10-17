package com.codingbox.springprj.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codingbox.springprj.domain.Member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	// jpa 지원해주는 표준
	// spring이 entityManager를 만들어서 em에다가 주입해준다
	/*
	 * @PersistenceContext이 있어야 표준 EntityManager 주입이 가능하다.
	 * 그러나, spring의 @Autowired가 주입이 되도록 이러한 지원을 해준다. 
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

	public List<Member> findAll() {
//		List<Member> result = 
//			em.createQuery("select m from Member m", Member.class)
//			  .getResultList();
//		return result;
		
		return em.createQuery("select m from Member m", Member.class)
				 .getResultList();
	}

	public List<Member> findByname(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				 .setParameter("name", name)
				 .getResultList();
	}

	public Member findOne(Long memberId) {
		return em.find(Member.class, memberId);
	}
	
	
}













