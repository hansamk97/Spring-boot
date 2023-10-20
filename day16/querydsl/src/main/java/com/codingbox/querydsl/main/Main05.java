package com.codingbox.querydsl.main;

import java.util.List;

import static com.codingbox.querydsl.entity.QMember.*;
import static com.codingbox.querydsl.entity.QTeam.*;

import com.codingbox.querydsl.entity.Member;
import com.codingbox.querydsl.entity.QMember;
import com.codingbox.querydsl.entity.Team;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main05{
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

			// having
			List<Tuple> result3 = queryFactory
									.select(team.name, member.age.avg())
									.from(member)
									.join(member.team, team)
									.groupBy(team.name)
									.having(member.age.avg().gt(10))
									.fetch();
			
			Tuple resultTeamA2 = result3.get(0);
			Tuple resultTeamB2 = result3.get(1);
			
			System.out.println("resultTeamA2 : " + resultTeamA2.toString());
			System.out.println("resultTeamB2 : " + resultTeamB2.toString());
					
			
			
			
			
			
			
			
			
			
			
			
			// Group by 사용
			// 팀 이름과 각 팀의 평균 연령을 구해라
			List<Tuple>  result2 = queryFactory
									.select(team.name, member.age.avg())
									.from(member)
									.join(member.team, team)
									.groupBy(team.name)
									.fetch();
									
			Tuple resultTeamA = result2.get(0);
			Tuple resultTeamB = result2.get(1);
			
			System.out.println("resultTeamA : " + resultTeamA.toString());
			System.out.println("resultTeamB : " + resultTeamB.toString());
			
			
			/*
			 *  집합
			 */
			List<Tuple> result = queryFactory.select(
												member.count(),
												member.age.sum(),
												member.age.avg(),
												member.age.max(),
												member.age.min()
											  )
											 .from(member)
											 .fetch();
			Tuple tuple = result.get(0);
			System.out.println(tuple.get(member.count()));
			System.out.println(tuple.get(member.age.sum()));
			System.out.println(tuple.get(member.age.avg()));
			System.out.println(tuple.get(member.age.max()));
			System.out.println(tuple.get(member.age.min()));
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}

















