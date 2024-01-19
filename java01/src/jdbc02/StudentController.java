package jdbc02;

import java.util.List;

//** Controller
//=> 요청 : 요청분석 -> 담당 Service -> Service 는 DAO 
//=> 결과 : DAO -> Service -> Controller
//=> View : Controller -> View 담당 (Java:Console // Web:JSP, Html.., React) 

public class StudentController {
	
	// ** 전역변수
	StudentService service = new StudentService();
	
	// ** View 역할 메서드
	// => selectList
	public void printList(List<StudentDTO> list) {
		System.out.println("** Student List **");
		// => 출력자료의 존재 확인
		if (list!=null) {
			// ** List 출력
			for (StudentDTO s:list) {
				System.out.println(s);
			}
		}else {
			System.out.println("** selectList 결과가 1건도 없음 **");
		}
	} //printList
	
	public void printDetail(StudentDTO dto) {
		System.out.println("** selectOne **");
		if (dto!=null) {
			System.out.println(dto);
		}else {
			System.out.println("** selectOne 결과 없음 **");
		}
	} //printDetail
	
	public static void main(String[] args) {
		
		StudentController sc = new StudentController();
		
		// ** selectList
		// => 요청 에 해당하는 Service 의 메서드 호출
		//    -> 처리결과를 View 에 전달해서 출력하도록함. 
		sc.printList(sc.service.selectList());    
		// ** selectOne
		sc.printDetail(sc.service.selectOne(18));
		
		StudentDTO dto = new StudentDTO();
		// ** Insert
		// => dto에 입력값 담기 -> Service(-> DAO) -> 결과출력
//		dto.setName("바나나");
//		dto.setAge(30);
//		dto.setJno(9);
//		dto.setInfo("insert test");
//		if ( sc.service.insert(dto)>0 ) {
//			System.out.println("** insert 성공 **");
//		}else System.out.println("** insert 실패 **");

		// ** Update
		// => info, point 수정, sno=19번 
		dto.setSno(19);
		dto.setInfo("수정 테스트");
		dto.setPoint(123.456);
		if ( sc.service.update(dto)>0 ) { 
			System.out.println("** update 성공 & 확인 **");
			sc.printDetail(sc.service.selectOne(19));
		}else System.out.println("** update 실패 **");
		
		// ** Delete
		if ( sc.service.delete(19)>0 ) {
			System.out.println("** delete 성공 & 확인 **");
			sc.printDetail(sc.service.selectOne(19));
		}else System.out.println("** delete 실패 **");
		
		// ** 참조자료형 Test
		StudentDTO dto2 = new StudentDTO();
		dto2.setSno(18);
		sc.service.selectOne2(dto2);
		sc.printDetail(dto2);
		
		// ** Join Test 
		//sc.printList(sc.service.joinList());   
		
		// ** Transaction Test
		sc.service.transactionTest();
		
	} //main

} //class
