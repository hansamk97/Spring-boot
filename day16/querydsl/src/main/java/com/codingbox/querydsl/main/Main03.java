package com.codingbox.querydsl.main;

import java.util.List;

import static com.codingbox.querydsl.entity.QMember.*;

import com.codingbox.querydsl.entity.Member;
import com.codingbox.querydsl.entity.QMember;
import com.codingbox.querydsl.entity.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main03{
	public static void main(String[] args) {
		EntityManagerFactory emf =  
			 Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Team teamA = new Team("teamA");
			Team teamB = new Team("teamB");
			em.persist(teamA);
			em.persist(teamB);
			
			Member member1 = new Member("member1", 10, teamA);
			Member member2 = new Member("member2", 20, teamA);
			Member member3 = new Member("member3", 30, teamB);
			Member member4 = new Member("member4", 40, teamB);
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			
			// 초기화
			em.flush();
			em.clear();
			
			// List
//			List<Member> fetch = queryFactory.select(member)
//											 .from(member)
//											 .fetch();
			
//			List<Member> fetch = queryFactory.selectFrom(member)
//											 .fetch();
			
			// 단건
//			Member findMember1 = queryFactory.selectFrom(member)
//											 .fetchOne();
			
			// 처음 한 건 조회
			// fetchFirst() : limit(1).fetchOne()
//			Member findMember2 = queryFactory.selectFrom(member)
//											 .fetchFirst();
//			System.out.println("findMember2 : " + findMember2);
			
			Long totalCount = queryFactory.select(member.count())
										  .from(member)
										  .fetchOne();
			System.out.println("totalCount : " + totalCount);
			
			
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}

















