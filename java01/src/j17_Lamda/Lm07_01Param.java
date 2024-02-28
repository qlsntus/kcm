package j17_Lamda;

import java.util.Random;

// 1) 매개변수 2개 , void
interface Calculate {
	void cal(int a, int b);
}
// 2) 매개변수 2개 , return int
interface CalculateR {
	int cal(int a, int b);
}
// 3) 매개변수 1개 , return int
interface HowLong {
	int len(String s);
}
// 4) 매개변수 없고 , return int
interface Generator {
	int rand();
}


public class Lm07_01Param {

	public static void main(String[] args) {
		// 1) 매개변수 2개 , void
		System.out.println("** 1) 매개변수 2개 , void **");
		Calculate c1;
		c1 = (a,b) -> System.out.println(" a+b => "+(a+b));
		c1.cal(2, 7);
		c1 = (a,b) -> System.out.println(" a-b => "+(a-b));
		c1.cal(2, 7);
		c1 = (a,b) -> System.out.println(" a-b => "+(a*b));
		c1.cal(2, 7);
		
		// 2) 매개변수 2개 , return int
		System.out.println("** 2) 매개변수 2개 , return int **");
		CalculateR cr;
		cr = (a,b) -> a+b ; 
		System.out.println("** 2.1) a+b => "+ cr.cal(4,5));
		cr = (a,b) -> a*b ; 
		System.out.println("** 2.2) a*b => "+ cr.cal(4,5));
		
		// 3) 매개변수 1개 , return int
		System.out.println("** 3) 매개변수 1개 , return int **");
		HowLong hl = s -> s.length();
		System.out.println("** 3.1) s.length => "+ hl.len("안녕하세요 !!!"));
		hl = s -> Integer.parseInt(s) ;
		System.out.println("** 3.2) parseInt(s) => "+ hl.len("1234567"));

		// 4) 매개변수 없고 , return int
		System.out.println("** 4) 매개변수 없고 , return int **");
		Generator gn = () -> {
			Random rn = new Random();
			return rn.nextInt(50);
		};
		System.out.println("Random Number => "+ gn.rand());
		
	} //main
} //class
