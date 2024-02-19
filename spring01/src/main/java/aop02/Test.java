package aop02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
	  // ** IOC/DI 적용
      // => 스프링컨테이너 생성
      // => 필요한 Bean 을 주입받는다
		AbstractApplicationContext sc = 
				new GenericXmlApplicationContext("aop02.xml");
		Programmer programmerB=(Programmer)sc.getBean("boy");
		Programmer programmerG=(Programmer)sc.getBean("girl");
	
      try {
          System.out.println("** Boy Test **");
          programmerB.doStudying();
          System.out.println("\n** Girl Test **");
          programmerG.doStudying();
       } catch (Exception e) {
          System.out.println("\n** main Exception => "+e.toString());
       }
       sc.close();


	}  //main

}//class
