package com.codingbox.querydsl.main;

import java.util.List;

import org.springframework.expression.spel.ast.Projection;

import static com.codingbox.querydsl.entity.QMember.*;
import static com.codingbox.querydsl.entity.QTeam.*;

import com.codingbox.querydsl.entity.Member;
import com.codingbox.querydsl.entity.MemberDTO;
import com.codingbox.querydsl.entity.QMember;
import com.codingbox.querydsl.entity.Team;
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

public class Main10{
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
			
			// jpql 사용
			List<MemberDTO> result = em.createQuery(
				"select new com.codingbox.querydsl.entity.MemberDTO(m.username, m.age)"
			+	" from Member m" , MemberDTO.class  
				).getResultList();
			
			for( MemberDTO memberDTO : result ) {
				System.out.println("memberDTO : " + memberDTO);
			}
			
			// QueryDSL - 프로퍼티 접근
			/*
			 * bean -> getter, setter 
			 * 	1param 	: MemberDto.class -> type 지정
			 *  2param~ : 꺼내올 값 나열
			 */
			List<MemberDTO> result2 = queryFactory
					.select( Projections.bean(MemberDTO.class, 
								member.username, member.age)
					)
					.from(member)
					.fetch();
			
			for(MemberDTO memberDTO : result2) {
				System.out.println("MemberDTO2 : " + memberDTO);
			}
			
			// 필드 직접 접근
			// getter, setter가 없어도 됨
			List<MemberDTO> result3 = queryFactory
					.select( Projections.fields(MemberDTO.class, 
								member.username, member.age)
					)
					.from(member)
					.fetch();
			
			for(MemberDTO memberDTO : result3) {
				System.out.println("MemberDTO3 : " + memberDTO);
			}
			
			// 생성자
			List<MemberDTO> result4 = queryFactory
					.select( Projections.constructor(MemberDTO.class, 
								member.username, member.age)
					)
					.from(member)
					.fetch();
			
			for(MemberDTO memberDTO : result4) {
				System.out.println("MemberDTO4 : " + memberDTO);
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

















