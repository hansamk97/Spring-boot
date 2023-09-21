package com.codingbox.core2.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.codingbox.core2.dto.Member;

import jakarta.persistence.EntityManager;

@Repository
public class JpaMemberRepository implements MemberRepository {

	private final EntityManager em;
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}
	
	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m",Member.class)
				 .getResultList();
	}
	
}







