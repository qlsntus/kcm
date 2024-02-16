package javaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

//** DAO Test 시나리오
//=> Detail 정확성 
// -> Test Data
// -> 정확한 id 를 사용하면 not null : Green_Line
// -> 없는 id 를 사용하면 null : Red_Line

//=> Insert 정확성
// -> 입력 가능한 Data 적용 : 1 return : Green_Line
// -> 입력 불가능한 Data 적용 : 0 return : Red_Line

public class Ex03_DAOTest {
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = new MemberDTO();
	
	// 1) Detail 정확성
	
	public void detailTest() {
		String id="banana"; //banana: true, black: false
		dto = dao.selectOne(id);
		assertNotNull(dto);
		System.out.println("** dto => "+dto);
	}
	//2) Insert 정확성
	@Test
	public void insertTest() {
		dto.setId("junit");
		dto.setPassword("12345!");
		dto.setName("유니트");
		dto.setAge(20);
		dto.setJno(7);
		dto.setInfo("JUnit Test");
		dto.setPoint(200.456);
		dto.setBirthday("2000-02-02");
		dto.setRid("apple");
		//= 성공: 1, 실패: 0
		assertEquals(dao.insert(dto), 1);
		
	}

}

