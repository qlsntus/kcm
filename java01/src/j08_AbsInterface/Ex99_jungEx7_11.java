package j08_AbsInterface;

// ** 정석기초 269p 예제
// => interface 의 default, static 메서드

interface MyInterface1 {
	default void method1() { 
		System.out.println("method1() in MyInterface1");
	}

	default void method2() { 
		System.out.println("method2() in MyInterface1");
	}

	static void staticMethod() { 
		System.out.println("staticMethod() in MyInterface1");
	}
}

interface MyInterface2 {
	default void method1() { 
		System.out.println("method1() in MyInterface2");
	}

	static void staticMethod() { 
		System.out.println("staticMethod() in MyInterface2");
	}
}

class Parent3 {
	public void method2() {	
		System.out.println("method2() in Parent3");
	}
}

// ** 메서드간의 충돌 Test
// 1) interface 와 조상 class간의 충돌
// => default 메서드 method2 와 조상의 메서드 method2
//    조상 메서드가 상속되고, default 메서드가 무시됨
// => 에디터의 자동완성시에는 interface 의 정보가 우선 표시될 수 있음
class Child3 extends Parent3 implements MyInterface1, MyInterface2 {
// 2) interface 간의 층돌_method1()
// => 동일한 default 메서드명을 가지고 있는 MyInterface1, MyInterface2 를 동시에 구현했더니 
//    Duplicate default Methods name.. Error 발생	
//    그러나 동일한 이름의 메서드를 오버라이딩하면 오류는 사라짐.
//    이유는 오버라이딩 과 동시에 조상의 동일이름의 메서드를 더이상 참조하지않으므로
	@Override
	public void method1() {
		System.out.println("method1() in Child3"); // 오버라이딩
	}
} //Child3

public class Ex99_jungEx7_11 {

	public static void main(String[] args) {
		Child3 c = new Child3();
		c.method1(); // in Child3
		c.method2(); // 코딩: MyInterface1 이표시됨 -> 실행 in Parent3
		MyInterface1.staticMethod();
		MyInterface2.staticMethod();
	} //main

} //class
