package j17_Lamda;

// ** 람다식을 메서드의 매개변수로 사용하는 경우
interface Printable2 {
	void myPrint(String s);
}

public class Lm07_02LamParam {

	public static void showString(Printable2 p, String s) {
		p.myPrint(s);
	}

	public static void main(String[] args) {
		
		// showString(p, s)
		showString((s) -> {
			System.out.println(s);
		}, "What is Lambda?");
		// showString 메서드의 첫번째 매개변수로는 Printable2를 구현한 클래스가 필요하며
		// 이를 람다식으로 적용함.
		
		showString(s -> System.out.println(s), "What is Lambda? => 생략형1");
		showString(System.out::println, "What is Lambda? => 생략형2");
		
	} // main
} // class
