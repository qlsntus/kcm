package aop09;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//** @ 방식의 공통적 관심 사항 구현 4.
//=> 핵심적 관심사항 (pointcut) 에 매개변수와 return 값이 있음.  
//=> 개별 advice 메서드 적용

@Component
@Aspect
public class MyAspect {
	
	// ** 포인트컷 설정을 위한 더미메서드
	@Pointcut("execution(* do*(*,*))")  // "execution(* do*(..))" 매개변수의 갯수,타입 무관
	public void myPointcut() { }	
	
	// Before : 핵심적 관심사항 수행 이전
	@Before("myPointcut()")
	public void myBefore(JoinPoint joinpoint) {
		System.out.println("~~ 프로젝트 과제를 해야 됩니다 ~~ => Before");
		
		Object[] args = joinpoint.getArgs();
		for (Object a:args) {
			System.out.println("myBefore 핵심사항의 매개변수 => "+a);
		}
	}
	// After_returning : 핵심적 관심사항 의 정상종료
	@AfterReturning(pointcut="myPointcut()", returning="result")
	public void myAfter_returning(Object result) {
		System.out.println("~~ 실행이 잘된다 ㅎㅎㅎ ~~ 핵심적 관심사항 정상종료 result => "+result);
		result = "Result Value Change" ; // JoinPoint 의 결과를 변경하는것은 아님
		System.out.println("~~핵심사항 정상종료 , result 변경 => "+result);
	}	
	
	// After_throwing : 핵심적 관심사항  의 비정상종료
	@AfterThrowing(pointcut="myPointcut()", throwing="e")
	public void myAfter_throwing(Exception e) {
		System.out.println("~~ myAfter_throwing, 전달된 e => "+e.toString());
		System.out.println("~~ 밤새워 복구한다 zzz ~~ => 예외발생으로 핵심적관심사항 비정상종료 ");
	}
	// After : 핵심적 관심사항  의 종료
	//  => 정상 종료이건, 비정상 종료이건 무조건시행
	@After("myPointcut()")
	public void myAfter() {
		System.out.println("** finally : 무조건 제출한다 ~~ => 아무튼 종료");
	}
} // class
