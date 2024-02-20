package aop06;

import java.util.Random;

import org.springframework.stereotype.Component;

//** Aop 구현 6
//=> 핵심적 관심사항만 구현 (매개변수, return 값 없음) 
//=> @ 방식Test

@Component("girl")
public class Girl implements Programmer {

	public Girl() { System.out.println("** Girl default 생성자 **"); }
	
	@Override
	public void doStudying() throws Exception {
		
		System.out.println(" ~~ 회원관리를 만듭니다 ~~ => 핵심적 관심사항");
		// if (new Random().nextBoolean()) { // 실패
		if (1==2) // false : 성공 -> 항상 정상종료 되도록
			throw new Exception("~~ 홀랑 다 날렸다 ㅠㅠㅠ !!! ~~ => 예외발생");
			
	} //doStudying
} //Girl
