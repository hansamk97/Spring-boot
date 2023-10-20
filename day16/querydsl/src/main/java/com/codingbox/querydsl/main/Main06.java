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

public class Main06{
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
			
			// inner join 
			List<Tuple> result2 = queryFactory
									.select(member, team)
									.from(member)
									.join(member.team, team).on(team.name.eq("teamA"))
									.fetch();

			for( Tuple tuple : result2 ) {
				System.out.println("tuple = " + tuple);
			}
			
			List<Tuple> result3 = queryFactory
									.select(member, team)
									.from(member)
									.join(member.team, team)
									.where(team.name.eq("teamA"))
									.fetch();

			for( Tuple tuple : result3 ) {
				System.out.println("tupleWhere = " + tuple);
			}
			
			
			
			/*
			 * 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원 모두 조회
			 * SQL : select m.*, t.* 
			 * 		 from Member m left outer join Team t
			 * 		 ON m.team_id = t.id and t.name = 'teamA'
			 * jpql : select m, t from Member m 
			 * 		  left join m.team t  
			 * 		  on t.name = "teamA"
			 */
			
//			List<Tuple> result = queryFactory
//									.select(member, team)
//									.from(member)
//									.leftJoin(member.team, team).on(team.name.eq("teamA"))
//									.fetch();
			
			List<Tuple> result = queryFactory
									.select(member, team)
									.from(member)
									.leftJoin(member.team, team)
									.where(team.name.eq("teamA"))
									.fetch();
			
			for( Tuple tuple : result ) {
				System.out.println("tuple = " + tuple);
			}
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}

















