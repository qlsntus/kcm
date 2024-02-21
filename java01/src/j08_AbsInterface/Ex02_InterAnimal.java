package j08_AbsInterface;

// ** interface 1.
//=> 상수, 추상메서드
//=> static, default 메서드 (구현부가 있는 메서드, Java8 부터 추가됨)

interface Animali {
	void breath();
	void sound();
	void special();
}

class Cati implements Animali {
	@Override
	public void breath() { System.out.println("~~ 고양이는 숨을 쉽니다.~~"); }
	@Override
	public void sound() { System.out.println("~~ 야옹 야옹 웁니다 ~~"); }
	@Override
	public void special() { System.out.println("** special: 고양이 eyeColor 는 멋집니다 ~~"); }
	
	void eyeColor() { System.out.println("~~ 고양이 eyeColor 는 멋집니다 ~~"); }	
}

class Dogi implements Animali {
	@Override
	public void breath() { System.out.println("~~ 강아지 는 숨을 쉽니다.~~"); }
	@Override
	public void sound() { System.out.println("~~ 멍멍 멍멍 웁니다 ~~"); }
	@Override
	public void special() { System.out.println("*** special: 빠르게 달립니다 ~~"); }
}
class Eaglei implements Animali {
	@Override
	public void breath() { System.out.println("~~ 독수리는 숨을 쉽니다.~~"); }
	@Override
	public void sound() { System.out.println("~~ 수리 수리 웁니다 ~~"); }
	@Override
	public void special() { System.out.println("** special: 훨훨 높이 날아 다닙니다 ~~"); }
}

public class Ex02_InterAnimal {

	public static void main(String[] args) {
		// 1) 생성
		// => 직접생성은 불가능
		// => 인스턴스의 Type 으로는 정의가능, 구현클래스를 통해 생성됨
		// => 그러나 사용범위는 interface 에 정의된 만큼만
		Animali c1 = new Cati();
		c1.breath();
		c1.sound();
		c1.special();
		//c1.eyeColor();
		
		Cati c2 = new Cati();
		c2.breath();
		c2.sound();
		c2.special();
		c2.eyeColor();
		
		// 2) 다형성 적용
		// => 후손은 조상의 Type 이 될 수 있다
		// => 반대는 성립안됨 (조상이 후손 Type이 될수없음)   
		Animali animal = new Dogi(); // Cati() Eaglei() Dogi()
		animal.breath();
		animal.sound();
		animal.special();
		// => 비교 Test
		animal = c1;
		animal = c2; // Animali animal = new Cati(); 와 동일구문
		//c2=c1;     //컴파일오류, Cati c2 = new Animali();  와 동일구문
		//c2=animal; //컴파일오류, Cati c2 = new Animali();  와 동일구문
		
		// 3) 메서드 호출
		animalUse(c1);
		animalUse(c2);
		animalUse(animal);
		animalUse(new Eaglei());
		
		// 4) 연산자 instanceof 적용
		Bear bear = new Bear();
		if ( bear instanceof Animali ) {
			  System.out.println("** bear 는 Animali 인스턴스 입니다");
		}else System.out.println("** bear 는 Animali 인스턴스 가 아닙니다");
		
	} //main
	
	public static void animalUse(Animali animal) {
		animal.breath();
		animal.sound();
		animal.special();
	} //animalUse

} //class
