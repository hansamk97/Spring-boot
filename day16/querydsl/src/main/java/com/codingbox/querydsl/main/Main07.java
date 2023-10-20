package com.codingbox.querydsl.main;

import java.util.List;

import static com.codingbox.querydsl.entity.QMember.*;
import static com.codingbox.querydsl.entity.QTeam.*;

import com.codingbox.querydsl.entity.Member;
import com.codingbox.querydsl.entity.QMember;
import com.codingbox.querydsl.entity.Team;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main07{
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
			
			// select 절에 subquery
			QMember memberSub4 = new QMember("memberSub");
			List<Tuple> fetch = queryFactory
									.select(member.username,
										JPAExpressions
											.select(memberSub4.age.avg())
											.from(memberSub4))
									.from(member)
									.fetch();
			
			for( Tuple tuple : fetch ) {
				System.out.println("username = " + tuple.get(member.username));
				System.out.println("age = " + 
					tuple.get(JPAExpressions.select(memberSub4.age.avg())
											.from(memberSub4)) );
			}
			
			
			// in 절
			QMember memberSub3 = new QMember("memberSub");
			List<Member> result3 = queryFactory
					.selectFrom(member)
					.where(member.age.in(
						JPAExpressions.select(memberSub3.age)
									  .from(memberSub3)
									  .where(memberSub3.age.gt(10))
					))
					.fetch();
					
			
			
			
			// 나이가 평균 나이 이상인 회원
			QMember memberSub2 = new QMember("memberSub");
			List<Member> result2 = queryFactory
						.selectFrom(member)
						.where(member.age.goe(
							JPAExpressions.select(memberSub2.age.avg())
										  .from(memberSub2)
						))
						.fetch();
					
			
			
			// 서브쿼리 - 나이가 가장 많은 회원 조회
			QMember memberSub = new QMember("memberSub");
			
			List<Member> result = queryFactory
				.selectFrom(member)
				.where(member.age.eq(
					JPAExpressions.select(memberSub.age.max())
								  .from(memberSub)
				))
				.fetch();
			
			System.out.println("result : " + result.get(0).getAge());
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}

















