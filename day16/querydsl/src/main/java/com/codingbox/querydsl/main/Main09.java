package com.codingbox.querydsl.main;

import java.util.List;

import static com.codingbox.querydsl.entity.QMember.*;
import static com.codingbox.querydsl.entity.QTeam.*;

import com.codingbox.querydsl.entity.Member;
import com.codingbox.querydsl.entity.QMember;
import com.codingbox.querydsl.entity.Team;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main09{
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
			
			// 상수 더하기
			List<Tuple> result = queryFactory
					.select(member.username, Expressions.constant("A"))
					.from(member)
					.fetch();
			for( Tuple tuple : result ) {
				System.out.println("tuple : " + tuple);
			}
			
			// 문자 더하기 concat
			// {username}_{age}
			// stringValue : 문자로 바꿔준다.
			List<String> result2 = queryFactory
				.select(member.username.concat("_").concat(member.age.stringValue()))
				.from(member)
				.where(member.username.eq("member1"))
				.fetch();
			
			for( String s : result2 ) {
				System.out.println("s : " + s);
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

















