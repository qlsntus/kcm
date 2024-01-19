package j01_basic;

import jdbc02.JoDTO;
import jdbc02.StudentDTO;

class Store {
	private Object data;
	public Object getData() { return data; }
	public void setData(Object data) { this.data=data; }
} //Store

//------------------------------------------------------------
//** Generic (포괄적인, 총칭[통칭]의)
//=> 컬렉션(Collection:자료구조) 을 보강해준 기능
//=> 컴파일 단계에서 객체의 자료형을 선언(정의) 해주면
//   다른 타입의 자료가 저장될수 없도록 해주는 기능

//** Generic 클래스 정의
//=> 클래스 이름 옆에 <> 사이에 알파벳 1글자를 사용하여 
// Generic Type 명을 선언해줌 
// ex : <T> 처럼 "<" 와 ">" 사이에 선언 
//=> 대문자로 T, E 등을 많이 사용 
// Type 의미로 "T" 를 주로 사용
//=> Generic 타입으로 클래스를 사용한다는 의미 
//=> 제네릭으로 기본 자료형(int, float, double....)은 사용할 수 없지만
// 기본자료형의 wrapper 클래스를 이용할 수있다. 

//** Generic 타입제한 (사용시, Wildcards_와일드카드타입 이용으로)
//=> <?>
// Unbounded Wildcards (제한없음_모든 클래스나 인터페이스 타입 가능)

//=> <? extends JoDTO>
// Upper Bounded Wildcards (상위클래스 제한_같거나 하위 타입 가능)

//=> <? super StudentDTO>
// Lower Bounded Wildcards (하위클래스 제한_ 같거나 상위타입 가능)

//=> 정의할때: <T> , <T extends 클래스명> , <T super 클래스명>
//------------------------------------------------------------

class StoreG<T> {
	private T data;
	public T getData() { return data; }
	public void setData(T data) { this.data=data; }
} //StoreG

// ** 제네릭 클래스의 타입 인자 제한
class Box<T extends Number> {
    private T data;

    public void setData(T data) { this.data = data;  }
    public T getData() { return this.data; }
} //Box




class GenArray<T> {
	private T[] arr;
	public T[] getData() { return arr; }
	public void setData(T[] arr) { this.arr=arr; }
	
	public void arrayPrint() {
		for (T a:arr) {
			System.out.println(a);
		} //for
	} //arrayPrint
} //GenArray

public class Gn01_StoreTest {

	public static void main(String[] args) {
		// 1. Object 를 이용한 기존방식
		Store s1 = new Store();
		s1.setData("짜장면");
		s1.setData(123); 	//int <-> Integer : 자동형변환
		s1.setData(123.456);//double <-> Double
		s1.setData(123.456f);
		s1.setData(123456789L);
		//s1.setData(new JoDTO(7, "Banana", 77, "화이팅","Generic Test"));
		System.out.println("** Test 1 => "+s1.getData());
		
		// => 단점 Test
		//String s = (String)s1.getData();
		// => 컴파일 오류 없으나 런타임오류 발생 : java.lang.ClassCastException
		//System.out.println("** 단점 Test => "+s);
		
		// 2. Generic StoreG
		StoreG g1 = new StoreG(); // 1. 과 동일: Type 생략시 Object 로 지정됨  

		// => 제네릭 클래스의 타입 인자 제한
		// => 클래스 생성시 정의
		Box<Integer> b1 = new Box();
		b1.setData(12345);
		// Box<String> b2 = new Box(); 컴파일오류 발생
		
		// ----------------------------------------
		// => 생성시 Type 제한 의 경우는 확인이 필요함  
		StoreG<?> g11 = new StoreG();
		//g11.setData("111");
		StoreG<? extends JoDTO> g12 = new StoreG();
		//g12.setData(new JoDTO());
		// -----------------------------------------
		
		StoreG<String> g2 = new StoreG<String>();
		g2.setData("스트링만 가능");
		//g2.setData(12345); // 컴파일오류발생
		
		StoreG<Integer> g3 = new StoreG<Integer>();
		g3.setData(12345);
		//g3.setData(123.456); // 컴파일오류발생
		
		// GenArray Test
		// 1) String
		String[] ss = {"가","나","Do","Re","Mi" };
		GenArray<String> ga1 = new GenArray<String>();
		ga1.setData(ss);
		ga1.arrayPrint();
		
		// 2) Integer
		Integer[] ii = {1, 2, 3, 4, 5};
		GenArray<Integer> ga2 = new GenArray<Integer>();
		ga2.setData(ii);
		ga2.arrayPrint();
		
		// 3) Character (char)
		Character[] cc = {'A','B','C','디','이'};  
		GenArray<Character> ga3 = new GenArray<Character>();
		ga3.setData(cc);
		ga3.arrayPrint();
		
		// 4) 객체
		JoDTO[] jj = { new JoDTO(), new JoDTO(), new JoDTO() };
		GenArray<JoDTO> ga4 = new GenArray<JoDTO>();
		ga4.setData(jj);
		ga4.arrayPrint();
		
	} //main

} //class
