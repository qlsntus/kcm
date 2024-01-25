package iocDI04_LifeCycle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//** Bean(객체) 의 LifeCycle
//=> 객체생성 -> 사용 -> 소멸
//=> 해당되는 특정시점에 자동실행 : xml, @, interface

//** Test1. xml
//=> xml 에 해당 시점별 속성에 등록
// -> init-method="begin"  destroy-method="end" 
//=> 실행순서 (생성 ........ 소멸) 
// 생성자 -> init-method(생성직후 자동호출) ..... 
//       -> 소멸(컨테이너 close) -> destroy-method(자동호출) 

class LifeCycle {
 public LifeCycle() { System.out.println("~~ LifeCycle_Test Default 생성자 ~~"); }
 public void begin() { System.out.println("~~ LifeCycle_Test begin() ~~"); }
 public void end() { System.out.println("~~ LifeCycle_Test end() ~~"); }
 public void login() { System.out.println("~~ LifeCycle_Test login() ~~"); }
 public void list() { System.out.println("~~ LifeCycle_Test list() ~~"); }
} //LifeCycle
public class LC01_xml {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new
	            GenericXmlApplicationContext("iocDI04_LifeCycle/lc01.xml");
	      LifeCycle lc = (LifeCycle)sc.getBean("lc");
	      lc.login();
	      lc.list();
	      sc.close();

	} //main

} //class