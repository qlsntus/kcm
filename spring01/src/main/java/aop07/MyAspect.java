package aop07;

import javax.management.RuntimeErrorException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// ** 횡적(공통) 관심사항 ( cross concerns => Aspect ) 구현
//=> 횡적(공통) 관심사항 과 핵심 관심사항 의 연결 방법 xml, @ 방식

//** @ 방식의 공통적 관심 사항 구현 2.
//=> 핵심적 관심사항 (pointcut): 매개변수, return 값 없는 경우
//=> Around 메서드 1개로 구현

@Component
@Aspect
public class MyAspect {
	
	// ** 포인트컷 설정을 위한 더미메서드
	@Pointcut("execution(void doStudying())")
	public void myPointcut() { }
	
	@Around("myPointcut()")
	public void myAround(ProceedingJoinPoint joinpoint) { 
		// Before: 핵심적 관심사항 수행 이전
		System.out.println("~~ 프로젝트 과제를 해야 됩니다 ~~ => Before");
		try {
			// PointCut: 핵심적 관심사항 수행
			// => Around 메서드의 매개변수를 통해 전달받아 수행
			joinpoint.proceed();
			// => Throwable 로 예외처리를 해야함
			
			// After_returning: 핵심적 관심사항 의 정상종료
			System.out.println("~~ 실행이 잘된다 ㅎㅎㅎ ~~ => 핵심적 관심사항 정상종료");
		} catch (Throwable e) {
			// After_throwing: 핵심적 관심사항  의 비정상종료 
			System.out.println("~~ 밤새워 복구한다 zzz ~~ => 예외발생으로 핵심적관심사항 비정상종료 ");
			throw new RuntimeException(e); // unChecked
			
		} finally {
			// After: 최종(아무튼) 종료 -> 정상 종료이건, 비정상 종료이건 무조건시행
			System.out.println("~~ finally : 무조건 제출한다 ~~ => 아무튼 종료");
		}
	} //myAround

} // class
