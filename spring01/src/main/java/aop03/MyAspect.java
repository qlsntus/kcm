package aop03;

import java.util.Random;

import org.aspectj.lang.ProceedingJoinPoint;

//** 횡적(공통)관심사항 ( cross concerns => Aspect ) 구현
//=> Boy, Girl : 핵심 관심사항 (core concerns) 만 구현하면 됨.
//=> 횡적(공통)관심사항 과 핵심관심사항 의 연결 방법 xml, @ 방식

//** xml 방식의 공통적 관심 사항 구현 2.
//=> pointcut : 매개변수, return 값 없음  
//=> 개별 advice 메서드를 구현 
//  Before, After_returning, After_throwing, After 

//** 용어정리
//Target: 핵심사항(Core concerns) 가 구현된 객체 : Boy, Girl
//JoinPoint: 클라이언트가 호출하는 모든 비즈니스 메서드    
//       ( 공통관심사항이 적용될수있는 지점, ex:메소드 호출시, 객체생성시 등 )
//Pointcut: JoinPoint 중 실제 공통적 관심사항이 적용될 대상 : doStudying()  
//Advice : 공통관심사항(Cross-Cutting) 구현 코드 + 적용시점
//     : 시점별 메서드들 ( myBefore() .... )
//     : 적용시점 (핵심로직 실행 전, 후, 정상 종료 후, 비정상 종료 후 등 )
//Aspect : Advice + Pointcut

public class MyAspect {
	
	//** Before
	public void myBefore() {
		System.out.println("프로젝트 과제를 합니다=> Before ");
	}

	//** After_returning : 핵심적 관심사항(기능)의 정상종료
	public void myAfter_returning() {
		System.out.println("~~200 OK: 회원가입,글등록이 잘됩니다=> 핵심적 관심사항 정상종료");
	}

	//** After_throwing : 핵심적 관심사항(기능)의 비정상종료
	public void myAfter_throwing() {
		 System.out.println("** 밤새워 수정 합니다 zz~~=>예외발생으로 핵심적 관심사항 비정상종료");
	}

	//** After : 정상/비정상 관계없이 무조건 시행
	public void myAfter() {
		System.out.println("~~ finally :무조건 제출합니다~~=> 무조건 종료(After)");
	}
	
	
	
		

} // class
