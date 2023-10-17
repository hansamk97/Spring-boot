package com.codingbox.springprj.test;

public class Main01 {

	public static void main(String[] args) {
		/*
		 * 파라미터 가변인자
		 *  - 자바에서는 파라미터 개수가ㅣ 다르면 다른 메서드로 인식한다.
		 *  동일한 기능을 하지만, 파라미터가 가변적으로 변경되는 경우 오버로딩을 사용한다.
		 *  그런데 오버로딩은 파라미터 개수에 맞춰 메서드가 계속 늘어나는 구조이다.
		 *  
		 *  // 파라미터가 한 개인 경우
		 *  public void search(String one){}
		 *  
		 *  // 파라미터가 두 개인 경우
		 *  public void search(String one, String two){} 
		 *  
		 *  // 파라미터가 세 개인 경우
		 *  public void search(String one, String two, String three){}
		 *  
		 *  이런 케이스 에서는 map, List, 객체배열 등으로 파라미터를 전달하기 한다.
		 *  
		 *  // Map을 사용하는 경우
		 *  public void search(Map<String, String> param){}
		 *  
		 *  // 배열을 사용하는 경우
		 *  public void search(String[] param){}
		 *  
		 *  // List를 사용하는 경우
		 *  public void search(List<String> param){}
		 *  
		 *  // Vo를 사용하는 경우
		 *  public void search(ParamVO param){}
		 *  
		 *  ===============================================
		 *  [가변인자]
		 *   가변인자를 사용하면 동적으로 파라미터를 받을 수 있다.
		 *   사용법은 변수 타입 뒤에 기호(...)를 붙여주면 된다.
		 *   가변인자를 가지고 있는 메소드를 호출할 때 아래 방법을 참고
		 */
		// 파라미터가 없는 경우
		test();
		
		// 파라미터 한개
		test("A");
		
		// 파라미터 두개
		test("A", "B");
		
		// 배열로 전달
		test(new String[] {"A", "B", "C"});
		
		test(5, new String[] {"A", "B", "C"});
		
		test(10);
		
	}
	
	public static void test(String... param) {
		System.out.println("-------1번-------");
		String[] array = param;
		for( String str : array ) {
			System.out.println(str);
		}
	}
	
	public static void test(int num, String... param) {
		System.out.println("-------2번-------");
		System.out.println("num : " + num);
		String[] array = param;
		for( String str : array ) {
			System.out.println(str);
		}
	}
	
	
	

}








