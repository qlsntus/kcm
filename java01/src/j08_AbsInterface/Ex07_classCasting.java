package j08_AbsInterface;

//*** 클래스의 형변환
//=> 자동 형변환 (형변환 생략가능)
//   조상 <- 자손 (Up_Casting)

//=> 명시적 형변환 (형변환 생략불가능)
//  자손 <- 조상 (Down_Casting)
//  실제적 클래스타입이 변환가능한 경우에만 적용됨 
// (변환불가능한 경우에는 컴파일 오류 없어도 런타임 오류(ClassCastException) 발생)

//Animal => Mammal 포유류  => PetAnimal
//Animal => Reptil 파충류 

// ** 복습 (교재 예제 입력하고 실행 해보기)
// => 249p 예제7_7
// => 253p 예제7_8
// => 253p 예제7_11

class Animal2 {
	String name;
	public Animal2() {System.out.println("** Animal 생성자 **"); }
	void breath() {
		System.out.println(name +" 는 숨을 쉽니다 ~~");
	}
} // Animal

class Mammal extends Animal2 {
	String cry ;
	public Mammal() {System.out.println("** Mammal 생성자 **"); }
	void crying() {
		System.out.println(super.name+ " 은(는) "+cry + " 웁니다 ~~");
	}
} // Mammal

class PetAnimal extends Mammal {
	PetAnimal() { System.out.println("~~ PetAnimal 생성 ~~"); }
	void checking() { System.out.printf("** %s는 예방접종을 했습니다.\n", name);  } 
} //PetAnimal

class Reptile extends Animal2 {
} // Reptile


public class Ex07_classCasting {

	public static void main(String[] args) {
		// Test1.
		PetAnimal dog = new PetAnimal();
		// => instanceof Test 
		if (dog instanceof PetAnimal) System.out.println("** dog 는 PetAnimal 입니다. **");
		if (dog instanceof Mammal) System.out.println("** dog 는 Mammal 입니다. **");
		if (dog instanceof Animal2) System.out.println("** dog 는 Animal2 입니다. **");
		
		// Test2. class Up_Casting Test
		Animal2 an1 = new PetAnimal(); // 조상<-후손 : Up_Casting, 자동
		//an1 = new Reptile(); // 모든후손은 교체가능 (다형성 적용가능)
		
		//PetAnimal pa = new Animal2(); // 후손<-조상 : Down_casting, XXXX
		
		// => 생성자 종류에 따른 Animal 인스턴스(an1, an2) 비교
		// => 모두 Up_Casting 허용됨.
		Animal2 an2 = new Animal2();
		//an1=dog;
		//an2=dog; // an2 = new PetAnimal() 과 동일효과
		
		// Test3. Down_casting
		// => 가능한 경우에만 명시적으로 허용
		System.out.println("** Down_casting Test **");
		an1.breath(); // Animal2 에 정의된 맴버만 접근 가능
		PetAnimal cat = null;
		cat = (PetAnimal)an1; // PetAnimal(후손) Type <- Animal2(조상) Type
		cat.checking(); // PetAnimal 에 정의된 맴버 접근가능 
		
		// cat = (Reptile)an1; -> 형제로 형변환은 안됨 
		
		//cat = (PetAnimal)an2;
		//cat.checking();
		// => 컴파일오류 없음, 런타임오류_ClassCastException
		//    ( 위 60행 있을때(정상실행), 없을때(런타임오류) 비교 )
		// => 그러므로 instanceof 연산자로 확인 후 Down_Casting 적용
		if (an2 instanceof PetAnimal) {
			cat = (PetAnimal)an2;
			System.out.println("** true: an2는 PetAnimal 입니다 **");
		}else System.out.println("** false: an2는 PetAnimal 이 될수없습니다 **"); 
		
		     
		// ** 인스턴스의 class Type 확인하기
		// => Object 에 정의된 getClass() 메서드 이용
		System.out.println("** dog 의 Class Type => "+dog.getClass().getName());
		System.out.println("** cat 의 Class Type => "+cat.getClass().getName());
		System.out.println("** an1 의 Class Type => "+an1.getClass().getName());
		System.out.println("** an2 의 Class Type => "+an2.getClass().getName());
		
		System.out.println("** Program Stop **");
	} //main

} //class
