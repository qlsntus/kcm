package j08_AbsInterface;

// ** 정석기초 249p 예제
// => class Casting 관련예제

class Car {
	String color;
	int door;
	// 운전하는 기능
	void drive() { System.out.println("drive, Brrrr~"); }
	// 멈추는 기능	
	void stop() { System.out.println("stop!!!"); }
} //Car

class FireEngine extends Car {	// 소방차
	// 물을 뿌리는 기능
	void water() { System.out.println("water!!!"); }
}

public class Ex99_jungEx7_07 {

	public static void main(String[] args) {
		Car car = null; // 인스턴스 선언만 
		FireEngine fe = new FireEngine();
		FireEngine fe2 = null; // 인스턴스 선언만
		
		fe.water();
		car = fe;  // 자동형변환 
		// Car car = new FireEngine(); -> 그러므로 Car 에 정의된 만큼만 접근 가능
		// car.water(); -> Error
		
		fe2 = (FireEngine)car; // OK (28행 때문에 다운캐스팅 가능)
		fe2.water();
		//car.water(); -> 여전히 car는 car일뿐 
		//=> 32행에서 car 를 다운캐스팅한 값을 fe2 에 전달했을뿐 car 자신이 변경된것은 아님  
		Car redCar = new Car();
		//fe2 = (FireEngine)redCar; 
		//=> 다운캐스팅 불가능: 런타임 오류 (java.lang.ClassCastException)
		//=> 그러므로 instanceof 연산자로 확인 후 Down_Casting 적용
		if (redCar instanceof FireEngine) fe2 = (FireEngine)redCar;
		else System.out.println("Error : redCar 는 FireEngine Type이 될 수 없음 !!!");
		
	} //main
} //class
