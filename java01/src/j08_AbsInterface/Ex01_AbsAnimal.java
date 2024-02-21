package j08_AbsInterface;

//** 추상 클래스 
//=> 추상메서드가 하나라도 정의되면 그 클래스는 반드시 추상클래스로 정의 해야 함 
//=> 직접 인스턴스를 생성하지 못함. ( new 사용불가 )
//   그러나 참조형 변수(인스턴스)의 타입으로 선언은 가능하다.
//   그리고 후손으로 인스턴스를 생성할 수 있음. 
//   그러므로 후손은 반드시 추상메서드 오버라이딩의 의무를 가짐

//** 추상 메서드
//=> Body 부분이 없고 Header 만 선언
//=> 앞쪽에 abstract, 매개변수 뒤에 ; 붙여야됨.
//=> Body 부분은 Child 클래스에서 반드시 재정의(오버라이딩) 해야 함.
//  ( 실행문이 없어도 공백으로 작성해야함 -> { } )
//=> 추상 메서드 목적 : 후손에게 오버라이딩 의 강제성을 부여 (메서드명의 통일성)

abstract class Animal {
	
	String kind;
	
	// ** 추상 클래스 생성자
	// => 생성자가 있어도 직접 생성은 불가능함.
	// => 후손에 의해 생성시 호출됨.
	Animal() { System.out.println("** Animal default 생성자 -> 그래도 직접 인스턴스 생성은 불가능 ");  }
	
	// ** 일반메서드
	void breath() { System.out.println(kind+"는 숨을 쉽니다.~~"); }
	
	//** 추상 메서드
	abstract void sound();
	abstract void special();
	
} //Animal

class Cat extends Animal {
	
	public Cat() { kind="고양이"; }	
	@Override
	void sound() { System.out.println("~~ 야옹 야옹 웁니다 ~~"); }	 
	@Override
	void special() { System.out.println("** special: 고양이 eyeColor 는 멋집니다 ~~"); }
	
	void eyeColor() { System.out.println("~~ 고양이 eyeColor 는 멋집니다 ~~"); }	
} //Cat

class Dog extends Animal {
	public Dog() { kind="강아지"; }	
	@Override
	void sound() { System.out.println("~~ 멍멍 멍멍 웁니다 ~~"); }
	@Override
	void special() { System.out.println("** special: 빠르게 달립니다 ~~"); }
	
	void speed() { System.out.println("~~ 빠르게 달립니다 ~~"); } 	
} //Dog

class Eagle extends Animal {
	public Eagle() { kind="독수리";}
	@Override
	void sound() { System.out.println("~~ 수리 수리 웁니다 ~~"); }
	@Override
	void special() { System.out.println("~~ 훨훨 높이 날아 다닙니다 ~~"); }
} //Eagle

class Bear {
	void breath() { System.out.println(" 곰은 숨을 쉽니다.~~"); }
	void sound() { System.out.println("~~ 어흥 어흥 웁니다 ~~"); }
	void special() { System.out.println("~~ 덩치는 크지만 나무를 잘 탑니다 ~~"); }
}

public class Ex01_AbsAnimal {

	public static void main(String[] args) {
		// 1) Animal 생성하기
		// => 직접 인스턴스를 생성하지 못함. ( new 사용불가 )
		//Animal animal = new Animal();
		
		// 2) Cat 생성
		// => 후손 생성시 추상클래스인 조상의 생성자 호출함
		//    그렇다 하더라도 추상클래스는 직접 인스턴스를 생성하지 못함
		// 2.1) Animal Type 으로 생성
		Animal c1 = new Cat();
		c1.breath();
		c1.sound();
		
		// 2.2) Cat Type 으로 생성
		Cat c2 = new Cat();
		c2.breath();
		c2.sound();
		c2.eyeColor();
		
		// ** 결론
		// => Animal Type, Cat Type 모두 생성가능하지만,
		//    접근 가능한 맴버의 범위는 Type 에 정의된 범위로 제한됨
		//    즉, Cat 에만 정의된 메서드는 Animal Type 에서는 접근할 수 없음
		// => 그러나 실행은 Cat 의 메서드가 실행됨.
		//   ( Animal 에는 header 부분만 있기 때문에 실행이 불가능함 )
		
		// => 장점
		//	-> Animal Type 으로 선언하게되면 우측을 Cat, Dog 만 변경하면
		//     클래스교체를 쉽게 할 수 있음
		//	-> 같은 조상을 상속하는 후손들은 조상의 타입으로 정의하면 클래스교체가 편리하다
		
		// 2.3) Dog 생성
		Animal d1 = new Dog();
		d1.breath();
		d1.sound();
		//d1.speed(); 
		//-> 접근불가 Error: Method speed() undefined for the Type Animal 
		
		Dog d2 = new Dog();
		d2.breath();
		d2.sound();
		d2.speed();
		
		d1=c1 ; // 동일 Type 이므로 성립됨 (클래스 교체 편리)
		//d2=c2;  // 다른 Type 이므로  Error
		d1.breath();
		d1.sound();

		// 2.4) 다형성 적용
		// => 조상의 타입으로 정의하고 후손클래스로 인스턴스를 생성함
		// => 우측의 생성자만 교체하면 쉽게 클래스 교체 가능
		// => 코드 내에서 조상의 정의된 맴버만 사용 했다는 의미
		// => 추상 뿐만 아니라 상속관계에서는 모두 적용가능함 
		// => 추상을 사용하면 의무구현의 강제성을 줄수있음 (메서드명을 일원화 할 수 있음)  
		
		// => Animal Type 은 생성자의 종류에 따라 다른 객체가 될 수있다.
		// => Animal Type 으로 정의했다는 의미는 우측에 누가 오던
		//    Animal 에 정의된 맴버만 사용했다는 의미이므로 클래스 교체 가능 
		System.out.println("** 다형성 적용 Test **");
		Animal animal = new Eagle();  //Cat() Dog() Eagle() Bear()->Type mismatch Error
		animal.breath();
		animal.sound();
		animal.special();
		
		// 2.5) 메서드 매개변수로 다형성 Test
		// => 아래의 메서드를 구현해 보세요~~
		animalUse(animal);
		animalUse(c1); // Animal Type
		animalUse(c2);  
		// => c2는 Cat Type 아래와 동일효과
		//    Animal animal = new Cat()
		
		Animal[] ans = { c1, c2, animal, new Dog() };
		animalUse(ans);
		
		// => 매개변수 (Object animal) Test
		animalUse(new Bear()); 
		
		// ** 연산자 instanceof
		// => 인스턴스 확인 연산자 
		if ( new Cat() instanceof Animal ) System.out.println("** c2 는 Animal 인스턴스 입니다 **");
		else System.out.println("** c2 는 Animal 인스턴스가 아닙니다 **");
		
		// => Animal 처럼 추상클래스 또는 클래스 Type 을 확인하는 경우에는
		//    컴피일 오류 발생 instanceof 연산자의 필요성이 없지만,
		//    interface 의 경우에는 런타임시에 오류 발생 하므로 instanceof 연산자를 이용한 확인이 필요함 
		//if ( new Bear() instanceof Animal ) System.out.println("** bear 는 Animal 인스턴스 입니다 **");
		//else System.out.println("** bear 는 Animal 인스턴스가 아닙니다 **");
		
	} //main

	// ** animalUse 오버로딩
	// => Animal Type 배열, Animal, Object
	
	public static void animalUse(Animal[] animals) {
		for (Animal a :animals) {
			a.breath();
			a.sound();
			a.special();
		}
	} //animalUse
	
	public static void animalUse(Animal animal) {
		animal.breath();
		animal.sound();
		animal.special();
	} //animalUse
	
	// ** 매개변수 Type : Object
	// => 모든 클래스 사용가능하고, Object에 정의된 맴버 만큼만 사용가능 
	public static void animalUse(Object animal) {
		System.out.println("** Your Address => "+animal);
	} //animalUse
	
} //class
