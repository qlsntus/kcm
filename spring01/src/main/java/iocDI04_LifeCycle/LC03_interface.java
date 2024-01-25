package iocDI04_LifeCycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Bean(객체) 의 LifeCycle 
//=> 객체생성 -> 사용 -> 소멸
//=> 해당되는 특정시점에 자동실행 : xml, @, interface

//** Test3. interface
//=> 각 시점별로 자동호출되는 메서드를 구현해놓은 interface 를 활용
//	-> InitializingBean : afterPropertiesSet()
//	-> DisposableBean : destroy()
//=> 실행 순서
//	생성자 -> afterPropertiesSet() (자동호출) -> 사용...  
//    -> 컨테이너 Close -> destroy() (자동호출)

@Component("lci")
class LifeCycleTestI implements InitializingBean, DisposableBean  {
	public LifeCycleTestI() { System.out.println("~~ LifeCycleTestI 생성자 ~~"); }
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("~~ LifeCycleTestI afterPropertiesSet() ~~");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("~~ LifeCycleTestI destroy() ~~");
	}
	public void login() { System.out.println("~~ LifeCycleTestI login() ~~"); }
	public void list() { System.out.println("~~ LifeCycleTestI list() ~~"); }
} //LifeCycleTestI

public class LC03_interface {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new 
				GenericXmlApplicationContext("iocDI04_LifeCycle/lc01.xml");
		LifeCycleTestI lc = (LifeCycleTestI)sc.getBean("lci");
		lc.login();
		lc.list();
		sc.close();
	} //main

} //class
