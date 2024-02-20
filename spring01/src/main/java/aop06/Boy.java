package aop06;

import java.util.Random;

import org.springframework.stereotype.Component;

//** Aop 구현 6
//=> 핵심적 관심사항만 구현 (매개변수, return 값 없음) 
//=> @ 방식Test

@Component("boy")
public class Boy implements Programmer {
	
	public Boy() { System.out.println("** Boy default 생성자 **"); }

	@Override
	public void doStudying() throws Exception  {
		
		System.out.println(" ~~ 게시판을 만듭니다 ~~ => 핵심적 관심사항");
		
		//if (new Random().nextBoolean()) // true : 실패
		if (true) // true : 실패 -> 항상 비정상종료 되도록 
			throw new Exception("~~ 홀랑 다 날렸다 ㅠㅠㅠ !!! ~~ => 예외발생");
			
	} //doStudying
	
} //Boy
