package com.codingbox.querydsl.main;

import java.util.List;

import org.springframework.expression.spel.ast.Projection;

import static com.codingbox.querydsl.entity.QMember.*;
import static com.codingbox.querydsl.entity.QTeam.*;

import com.codingbox.querydsl.entity.Member;
import com.codingbox.querydsl.entity.MemberDTO;
import com.codingbox.querydsl.entity.QMember;
import com.codingbox.querydsl.entity.Team;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main12{
	static JPAQueryFactory queryFactory ;
	
	public static void main(String[] args) {
		EntityManagerFactory emf =  
			 Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		queryFactory = new JPAQueryFactory(em);
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

			String usernameParam = null;
			Integer ageParam = null;
			
			List<Member> result = searchMember1(usernameParam, ageParam);
			System.out.println("result.size() : " + result.size());
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}
	
	private static List<Member> searchMember1(String username, Integer age){
		BooleanBuilder builder = new BooleanBuilder();
		if( username != null ) {
			builder.and(member.username.eq(username));
		}
		
		if( age != null ) {
			builder.and(member.age.eq(age));
		}
		
		System.out.println("builer : " + builder.toString());
		
		return queryFactory
					.selectFrom(member)
					.where(builder)
					.fetch();
	}
	
	
	
	

}

















