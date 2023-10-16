package com.codingbox.springprj.domain;

import com.codingbox.springprj.exception.NotEnoughStockException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Item {

	@Id @GeneratedValue
	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	// ====== 비즈니스 로직 =======
	public void removeStock(int count) {
		int restStock = this.stockQuantity - count;
		
		// 재고 부족시 로직 처리
		if( restStock < 0 ) {
			// 사용자 지정 Exception
			throw new NotEnoughStockException("need mode stock");
		}
		
		this.stockQuantity = restStock;
	}
	

	
	
	
}
