package j17_Lamda;

import java.util.function.Function;

//** FunctionalInterface 1
//=> Function <T, R> 
//   T type 입력받아 R type return

//** 실습
// => String 의 길이를 return
// => Double 입력받아 Double return

public class Lm04_FunctionTest {

	public static void main(String[] args) {
		// 실습1) String 의 길이를 return
		Function<String, Integer> f1 = s -> s.length();
				
		// 실습2) Double 입력받아 Double return 
		// => Double 입력받아 inch로,
		//    inch 를 cm 로 출력하기
		Function<Double, Double> cToi = d -> d*0.393701 ;
		Function<Double, Double> iToc = d -> d*2.54 ;		
				
		// ** 실행
		System.out.println("** 실습1) function 의 길이 => "+ f1.apply("function"));
		System.out.println("** 실습1) 가나다라마 의 길이 => "+ f1.apply("가나다라마"));
		
		System.out.println("** 실습2) cToi, 123cm => "+ cToi.apply(123.0)+" inch");
		System.out.println("** 실습2) iToc, 123inch => "+ iToc.apply(123.0)+" cm");
		
	} //main

} //class
