package j17_Lamda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

// ** Supplier <T>
// => 단순히 무언가를 반환할 때 유용.
//    T get();

public class Lm06_Supplier {
	
	// 1. Supplier 를 이용해서 크기가 n 인 list 를 만들어줌.
	// => sp 의 get()이 return 하는 값을 list 에 add, n번 반복  
	//    즉, get() 의 구현 내용에 따라서 return 하는 값은 달라짐
	public static List<Integer> makeList(Supplier<Integer> sp, int n) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i=0; i<n; i++) { list.add(sp.get()); }
		return list;
	} //makeList
	
	// 2. DoubleSupplier
	public static List<Double> makeDoubleList(DoubleSupplier sp, int n) {
		List<Double> list = new ArrayList<Double>();
		for (int i=0; i<n; i++) { list.add(sp.getAsDouble()); }
		return list;
	} //makeDoubleList
	
	public static void main(String[] args) {
		// 1. Supplier Test
		// => get() : 0 ~ 50 사이의 random 값을 return 하도록 구현
		Supplier<Integer> sp = () -> {
			return new Random().nextInt(50) ;
		} ;
		// => 5회 반복하여 list 만들기
		System.out.println("** Supplier 5회 => "+ makeList(sp, 5)); 
		System.out.println("** Supplier 10회 => "+ makeList(sp, 10)); 
		
		// 2. DoubleSupplier
		DoubleSupplier dsp = () -> {
			return new Random().nextDouble()*50.0+0.0;
			//return new Random().nextDouble(50.0); -> 오류
		};
		System.out.println("** DoubleSupplier 5회 => "+ makeDoubleList(dsp, 5)); 
		
	} //main

} //class
