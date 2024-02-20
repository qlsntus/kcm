package aop05;


//** Aop 구현
//1 단계 : 핵심적 관심사항 과  공통적 관심사항 분리
//=> 핵심적 관심사항만 구현
//=> 공통적 관심사항(Aspect) 분리 : 별도의 클래스로 분리 -> MyAspect.java

public class Boy implements Programmer {
	
	@Override
	public String doStudying(int n, int i) throws Exception {
		System.out.printf("~~ 열심히 회원관리를 %d개 만듭니다 => 핵심적 관심사항\n", (n+i));
		// ** Test 를 위해 늘 성공으로 처리
		// => 늘 false 값이 되도록 조건설정
		// if ( new Random().nextBoolean() ) {
		if ( 1==2 ) {
			// 실패
			throw new Exception("~~ 404*100 ㅠㅠ ~ => 예외발생");
		}
		return "취업성공 연봉1억";
	} //doStudying

} //class
