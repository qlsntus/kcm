package aop07;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		
		// ** IOC/DI 적용: 스프링 컨테이너를 통해 주입받기
		AbstractApplicationContext sc = 
				new GenericXmlApplicationContext("aop07.xml");
		Programmer programmerB = (Programmer)sc.getBean("boy");
		Programmer programmerG = (Programmer)sc.getBean("girl");
		try {
			System.out.println("\n** Girl Test **");
			programmerG.doStudying();
			System.out.println("\n** Boy Test **");
			programmerB.doStudying();
		} catch (Exception e) {
			System.out.println("\n** main Exception => "+e.toString());
		}
		sc.close();
	}//main

} //class
