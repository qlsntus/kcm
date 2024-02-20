package aop09;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		
		// ** IOC/DI 적용: 스프링 컨테이너를 통해 주입받기
		AbstractApplicationContext sc = 
				new GenericXmlApplicationContext("aop09.xml");
		Programmer programmerB = (Programmer)sc.getBean("boy");
		Programmer programmerG = (Programmer)sc.getBean("girl");
		try {
			System.out.println("\n** Girl Test **");
			System.out.println(" Girl 결과 => "+ programmerG.doStudying(10, 5));
			System.out.println("\n** Boy Test **");
			System.out.println(" Boy 결과 => "+ programmerB.doStudying(10, 3));
		} catch (Exception e) {
			System.out.println("\n** main Exception => "+e.toString());
		}
		sc.close();
	}//main

} //class
