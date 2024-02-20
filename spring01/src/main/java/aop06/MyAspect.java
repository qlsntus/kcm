package aop06;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//** 횡적(공통)관심사항 ( cross concerns => Aspect ) 구현
//=> 횡적(공통)관심사항 과 핵심관심사항 의 연결 방법 xml, @ 방식

//** @ 방식의 공통적 관심 사항 구현 1.
//=> 핵심적 관심사항 (pointcut) : 매개변수, return 값 없는 경우
//=> 개별 advice 메서드 구현
//   Before, After_returning, After_throwing, After 

//** @Aspect
//=> 현재 빈에 공통적 관심사항이 구현되어 있음을 스프링에 알리는 애노테이션
//   즉, 스프링 컨테이너가 MyAspect를  Aspect 객체로 인식하게 해줌
//=> 포인트컷과 어드바이스의 결합이므로 @Aspect 가 설정된 Aspect 객체에는  
//   반드시 포인트컷과 어드바이스를 결합하는 설정이 있어야 함.
//=> 포인트컷을 설정하기위해서는 더미메서드를 사용해야함. 
//   (몸체가 없이 단순히 포인트컷을 식별하는 용도로만 쓰이는 더미 메서드)

@Aspect
@Component
public class MyAspect {
	
	// ** 포인트컷 설정을 위한 더미메서드
	@Pointcut("execution(void doStudying())")
	public void myPointcut() { }
	
	// Before : 핵심적 관심사항 수행 이전
	@Before("myPointcut()")
	public void myBefore() {
		System.out.println("~~ 프로젝트 과제를 해야 됩니다 ~~ => Before");
	}
	// After_returning : 핵심적 관심사항 의 정상종료
	@AfterReturning("myPointcut()")
	public void myAfter_returning() {
		System.out.println("~~ 실행이 잘된다 ㅎㅎㅎ ~~ => 핵심적 관심사항 정상종료");
	}
	// After_throwing : 핵심적 관심사항  의 비정상종료
	@AfterThrowing("myPointcut()")
	public void myAfter_throwing() {
		System.out.println("~~ 밤새워 복구한다 zzz ~~ => 예외발생으로 핵심적관심사항 비정상종료 ");
	}
	// After : 핵심적 관심사항  의 종료
	//  => 정상 종료이건, 비정상 종료이건 무조건시행
	@After("myPointcut()")
	public void myAfter() {
		System.out.println("~~ finally : 무조건 제출한다 ~~ => 아무튼 종료");
	}

} // class
