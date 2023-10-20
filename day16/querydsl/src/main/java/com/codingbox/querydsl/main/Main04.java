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

public class Main04{
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
			Member member5 = new Member(null, 100, teamB);
			Member member6 = new Member("member6", 100, teamB);
			Member member7 = new Member("member7", 100, teamB);
			
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			em.persist(member5);
			em.persist(member6);
			em.persist(member7);
			
			
			// 초기화
			em.flush();
			em.clear();
			
			// 페이징
			List<Member> result2 = queryFactory.selectFrom(member)
											  .orderBy(member.username.desc())
											  .offset(1)
											  .limit(10)
											  .fetch();
			
			
			/*
			 * 회원 정렬 순서
			 * 1. 회원 나이 내림차순(desc)
			 * 2. 회원 이름 올림차순(asc)
			 * 단, 2에서 회원이름이 없으면 마지막에 출력(nullsLast)
			 */
			
			List<Member> result = queryFactory
				.selectFrom(member)
				.where(member.age.eq(100))
				.orderBy(member.age.desc(), member.username.asc().nullsLast())
				.fetch();
			
			System.out.println("resultMember5 : " + result.get(0));
			System.out.println("resultMember6 : " + result.get(1));
			System.out.println("memberNull : " + result.get(2));
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}

















