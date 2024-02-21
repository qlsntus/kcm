package j08_AbsInterface;

//** interface 2.
//=> 상수, 추상메서드
//=> static, default 메서드 (구현부가 있는 메서드, Java8 부터 추가됨)

public interface Ex03_MyInterface {
	// ** 상수만 허용
	// => public static final 생략가능
	public static final int NUM=123;
	String NAME="Green";
	
	// ** 추상메서드만 허용
	// => public abstract 생략가능
	public abstract int getNum();
	String getName(); // public abstract 생략됨
	
	// ** 생성자 : 허용되지 않음
	//Ex03_MyInterface() { }
} //interface
