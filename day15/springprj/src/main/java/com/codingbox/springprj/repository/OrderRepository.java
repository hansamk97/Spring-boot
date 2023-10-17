package com.codingbox.springprj.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.codingbox.springprj.domain.Order;
import com.codingbox.springprj.domain.OrderStatus;
import com.codingbox.springprj.domain.QMember;
import com.codingbox.springprj.domain.QOrder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}

	// 이 경우는 파라미터 값이 다 존재한다는 뜻
	// 그런데 status,name의 값이 null일 수 있다.
//	public List<Order> findAll(OrderSearch orderSearch) {
//		List<Order> resultList =
//			em.createQuery("select o from Order o join o.member m " 
//					 + "where o.status = :status "
//					 + "and m.name like :name",Order.class)
//			  .setParameter("status", orderSearch.getOrderStatus())
//			  .setParameter("name", orderSearch.getMemberName())
//			  .setFirstResult(100)
//			  .setMaxResults(10)
//			  .getResultList();
//		
//		return resultList;
//	}
	
	// QueryDSL - 실무에서 많이 사용
	public List<Order> findAll(OrderSearch orderSearch) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		// Order, Member
		QOrder order = QOrder.order;
		QMember member = QMember.member;
		
		return query.select(order)
					.from(order)
					.join(order.member, member)	// member : member의 알리아스
//					.where(order.status.eq(orderSearch.getOrderStatus()) , 
//							member.name.like(orderSearch.getMemberName()))
					.where(statEq(orderSearch.getOrderStatus()),
							nameLike(orderSearch.getMemberName()))
					.fetch();
	}
	
	private BooleanExpression statEq(OrderStatus orderStatus) {
		if(orderStatus == null) {
			return null;
		}
		return QOrder.order.status.eq(orderStatus);
	}
	
	private BooleanExpression nameLike(String memberName) {
		if( memberName == null || memberName.equals("") ) {
			return null;
		}
		return QMember.member.name.contains(memberName);
	}

	public Order findOne(Long orderId) {
		return em.find(Order.class, orderId);
	}
	

}














