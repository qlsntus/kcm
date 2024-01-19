package mvcTest;

import java.util.List;

// ** Service
// => Controller 요청에 해당하는 DAO 의 메서드를 실행
// => Controller 와 DAO의 중간에 위치하면서
//    이 둘의 의존성을 낮추어줌.   

public class StudentService {
	
	// ** 전역변수 정의
	StudentDAO dao = new StudentDAO();
	
	// ** Join Test
	public List<StudentDTO> joinList() {
		return dao.joinList();
	}
	
	// ** selectList
	public List<StudentDTO> selectList() {
		return dao.selectList();
	}
	// ** selectOne
	public StudentDTO selectOne(int sno) {
		return dao.selectOne(sno);
	}
	// ** selectOne2
	public void selectOne2(StudentDTO dto) {
		dao.selectOne2(dto);
	}
	
	// ** insert
	public int insert(StudentDTO dto) {
		return dao.insert(dto);
	}
	// ** update
	public int update(StudentDTO dto) {
		return dao.update(dto);
	}
	// ** delete
	public int delete(int sno) {
		return dao.delete(sno);
	}
	// ** Transaction Test 
	public void transactionTest() {
		dao.transactionTest();
	}

} //class
