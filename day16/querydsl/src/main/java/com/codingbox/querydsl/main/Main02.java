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

public class Main02{
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
			
			// jpql
			String jpqlString = "select m from Member m where m.username = :username";
			Member findByJpql = em.createQuery(jpqlString, Member.class)
								  .setParameter("username", "member1")
								  .getSingleResult();
			System.out.println("findByJpql : " 
								  + findByJpql.getUsername().equals("member1"));
			
			// queryDSL
			//QMember m = new QMember("m");	// QMember의 이름을 부여한다, 별칭부여,
			//QMember m = QMember.member;
			
			Member findByQueryDSL = queryFactory.select(member)
												.from(member)
												.where(member.username.eq("member1"))
												.fetchOne();
			System.out.println("findByQueryDSL : " 					
						+ findByQueryDSL.getUsername().equals("member1"));
			
			// queryDSL 검색조건
//			Member findByQueryDSL2 = queryFactory.select(member)
//												 .from(member)
//												 .where(member.username.eq("member1")
//														 	  .and(member.age.between(10, 30)))
//												 .fetchOne();
			
			Member findByQueryDSL2 = queryFactory.select(member)
												 .from(member)
												 .where(member.username.eq("member1"),
														member.age.between(10, 30))
												 .fetchOne();
			
			System.out.println("findByQueryDSL2 : " 					
					+ findByQueryDSL2.getUsername().equals("member1"));
			
			
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}

















