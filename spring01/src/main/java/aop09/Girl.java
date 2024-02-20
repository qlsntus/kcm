package aop09;

import java.util.Random;

import org.springframework.stereotype.Component;

//** Aop 구현 9.
//=> @ 방식 : 핵심관심사항에 인자(매개변수) 와  return값이 있는 경우
//=> 개별 advice 메서드 적용

@Component("girl")
public class Girl implements Programmer {

	public Girl() { System.out.println("** Girl default 생성자 **"); }
	
	@Override
	public String doStudying(int n, int p) throws Exception {
		
		System.out.println(" ~~ 회원관리를 "+(n+p)+"개 만듭니다 ~~ => 핵심적 관심사항");
		// if (new Random().nextBoolean()) { // 실패
		if (1==2) // false : 성공 -> 항상 정상종료 되도록
			throw new Exception("~~ 홀랑 다 날렸다 ㅠㅠㅠ !!! ~~ => 예외발생");
		return "취업성공 연봉 1억" ;	
				
	} //doStudying
} //Girl
