package aop01;

import java.util.Random;

public class Girl implements Programmer{

	@Override
	public void doStudying() {
		
		System.out.println("프로젝트 과제를 합니다=> Before ");
		try {
			System.out.println("열심히 게시판을 만듭니다 => 핵심적 관심사항");
			if (new Random().nextBoolean()) {
				// 실패
			throw new Exception("~~Error 500*100 =>예외발생");
			}else {
				//성공
				System.out.println("~~글등록이 잘됩니다=> 핵심적 관심사항 정상종료");
			}
			
		} catch (Exception e) {
			System.out.println("**Girl Exception =>"+e.toString());
			System.out.println("** 밤새워 수정 합니다 zz~~=>예외발생으로 핵심적 관심사항 비정상종료");
			
		}finally {
			System.out.println("~~ finally :무조건 제출합니다~~=> 무조건 종료(After)");	
		}
		
	}// doStudying

}
