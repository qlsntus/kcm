package aop08;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//** @ 방식의 공통적 관심 사항 구현 3.
//=> 핵심적 관심사항 (pointcut) 에 매개변수와 return 값이 있음.  
//=> Around 메서드 1개로 구현

@Component
@Aspect
public class MyAspect {
	
	// ** 포인트컷 설정을 위한 더미메서드
	@Pointcut("execution(* do*(*))")  // "execution(* do*(..))" 매개변수의 갯수,타입 무관
	public void myPointcut() { }
	
	@Around("myPointcut()")
	public Object myAround(ProceedingJoinPoint joinpoint) { 
		// Before: 핵심적 관심사항 수행 이전
		System.out.println("~~ 프로젝트 과제를 해야 됩니다 ~~ => Before");
		
		// 1) return 값 처리
		Object result = null;
		
		// 2) 핵심관심사항의 인자(매개변수) 사용
		Object[] args = joinpoint.getArgs();
		for (Object o:args) {
			System.out.println("** Before 핵심적 관심사항(joinpoint) 의 매개변수 =>"+o);
		}
		try {
			// PointCut: 핵심적 관심사항 수행
			// => Around 메서드의 매개변수를 통해 전달받아 수행
			// => joinpoint 의 return 값을 전달받아 return
			result = joinpoint.proceed();
			// => Throwable 로 예외처리를 해야함
			
			// After_returning: 핵심적 관심사항 의 정상종료
			System.out.println("~~ 실행이 잘된다 ㅎㅎㅎ ~~ => 핵심적 관심사항 정상종료");
		} catch (Throwable e) {
			// After_throwing: 핵심적 관심사항  의 비정상종료 
			System.out.println("~~ 밤새워 복구한다 zzz ~~ => 예외발생으로 핵심적관심사항 비정상종료 ");
			
			// => 발생된 예외를 Throwable로 처리(처리&종료) 했으므로 main까지 전달되지않음 (확인후 Test)
			throw new RuntimeException(e); // unChecked
			
		} finally {
			// After: 최종(아무튼) 종료 -> 정상 종료이건, 비정상 종료이건 무조건시행
			System.out.println("~~ finally : 무조건 제출한다 ~~ => 아무튼 종료");
		}
		return result;
	} //myAround

} // class
