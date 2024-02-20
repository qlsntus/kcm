package aop04;

import java.util.Random;

public class Girl implements Programmer{

	@Override
	public String doStudying(int n) throws Exception {
	
		
			System.out.printf("열심히 게시판을 %d개 만듭니다 => 핵심적 관심사항\n", n);
			//** Test 를 위해 늘 실패로 처리
			//=> 항상 true가 되도록
			//if (new Random().nextBoolean()) {
			if (true) {
				// 실패
			throw new Exception("~~Error 404*100 =>예외발생");
		
				
			}
			return "취업성공 연봉1억" ;
		
		}//doStudying
} //class
	
