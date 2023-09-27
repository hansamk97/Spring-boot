package com.codingbox.item.test.enums;

public enum Week {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	
	// enum타입은 일반 메서드를 가질 수 있다.
	public void dayInfo() {
		System.out.println("dayInfo enum");
	}
}
