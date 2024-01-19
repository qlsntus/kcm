package mvcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



//** DAO(Data Access Object)
//=> SQL 구문 처리
//=> CRUD 구현 
//   Create(Insert), Read(selectList, selectOne), Update, Detete

//** 첫번째 예제 DBStart 와 ~~~DAO 와 차이점
//=> 결과를 직접 처리하지않고 제공해야됨
//=> 즉, 메서드 역할별로 처리결과를 return 해야함
//=> 그러므로 특히 select 결과를 잘 전달하기위해 결과를 객체화해야함 

public class StudentDAO {
	// ** 전역변수 정의
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	// ** Join Test
	// => sno, name, age, jno, jname, project, captain, 조장이름 출력하기
	// => JoDTO 작성, joinList() 메서드작성( Controller, Service, DAO )
	// ** selectList
	public List<StudentDTO> joinList() {
		sql="select s1.sno, s1.name, s1.age, s1.jno, jname, project, captain," 
				+"(select name from student where sno=captain) cname"
				+" from student s1 Left Outer Join  jo j ON s1.jno=j.jno";
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		try {
			pst=cn.prepareStatement(sql);
			rs=pst.executeQuery();	
			// => 결과의 존재여부
			// => 존재: list 에 담기
			// => 없음: return null
			if (rs.next()) {
				do {
					// => setter 사용
					StudentDTO dto = new StudentDTO();
					dto.setSno(rs.getInt(1));
					dto.setName(rs.getString(2));
					dto.setAge(rs.getInt(3));
					dto.setJno(rs.getInt(4));
					dto.setJname(rs.getString(5));
					dto.setProject(rs.getString(6));
					dto.setCaptain(rs.getInt(7));
					dto.setCname(rs.getString(8));
					list.add(dto);
				}while(rs.next());
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** joinList Exception => "+e.toString());
			return null;
		}
	} //joinList
	
	// ** selectList
	public List<StudentDTO> selectList() {
		sql="select * from student";
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		
		try {
			pst=cn.prepareStatement(sql);
			rs=pst.executeQuery();	
			// => 결과의 존재여부
			// => 존재: list 에 담기
			// => 없음: return null
			if (rs.next()) {
				do {
					// => setter 사용
					//StudentDTO dto = new StudentDTO();
					//dto.setSno(rs.getInt(1));
					//dto.setName(rs.getString(2));
					//dto.setAge(rs.getInt(3));
					//dto.setJno(rs.getInt(4));
					//dto.setInfo(rs.getString(5));
					//dto.setPoint(rs.getDouble(6));
					
					// => 생성자 활용 (cname 추가후 수정)
					StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2),
							rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), null);
					
					list.add(dto);
				}while(rs.next());
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectList Exception => "+e.toString());
			return null;
		}
	} //selectList
	
	// ** selectOne
	// => 기본자료형 매개변수 _ Call By Value
	public StudentDTO selectOne(int sno) {
	  	sql = "SELECT * FROM student WHERE sno=?";
	  	// => Login ~~~~where sno = ? and name =?
	  	// password 암호화 떄문에 직접비교 불가능 하기떄문에 비권장함.
	  	try {
	  		pst = cn.prepareStatement(sql);
	  		pst.setInt(1, sno);
	        rs = pst.executeQuery();

	        if (rs.next()) {
	        	StudentDTO dto = new StudentDTO();
	        	dto.setSno(rs.getInt(1));
	        	dto.setName(rs.getString(2));
	        	dto.setAge(rs.getInt(3));
	        	dto.setJno(rs.getInt(4));
	        	dto.setInfo(rs.getString(5));
	        	dto.setPoint(rs.getDouble(6));
	        	return dto;
	        } else {
            return null;
	        }
	  	}catch (Exception e) {
	  		System.out.println("** selectOne Exception => " + e.toString());
	  		return null;
	  	}
	} //selectOne
	
	// => 비교 Test
	// => 참조자료형 매개변수 Test_Call By Reference
	public void selectOne2(StudentDTO dto) {
	  	sql = "SELECT * FROM student WHERE sno=?";
	  	try {
	  		pst = cn.prepareStatement(sql);
	  		pst.setInt(1, dto.getSno());
	        rs = pst.executeQuery();

	        if (rs.next()) {
	        	dto.setName(rs.getString(2));
	        	dto.setAge(rs.getInt(3));
	        	dto.setJno(rs.getInt(4));
	        	dto.setInfo(rs.getString(5));
	        	dto.setPoint(rs.getDouble(6));
	        } else {
	        	System.out.println("** Student 없음 **");
	        }
	  	}catch (Exception e) {
	  		System.out.println("** selectOne2 Exception => " + e.toString());
	  	}
	} //selectOne2
	
	// ** insert
	// => 입력 컬럼: name, age, jno, info
	public int insert(StudentDTO dto) {
		sql="insert into student(name,age,jno,info) values(?,?,?,?)";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());
			
			//int count = pst.executeUpdate();
			//return count;	
			//=> 비교 
			return pst.executeUpdate(); // 처리갯수
			
		} catch (Exception e) {
			System.out.println("** insert Exception => "+e.toString());
			return 0;
		}
	} //insert
	
	// ** update
	// => info, point 수정 
	public int update(StudentDTO dto) {
		sql="update student set info=?, point=? where sno=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, dto.getInfo());
			pst.setDouble(2, dto.getPoint());
			pst.setInt(3, dto.getSno());
			
			return  pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** update Exception => "+e.toString());
			return 0;
		}
	} //update
	
	// ** delete
	// => sno 로 삭제
	public int delete(int sno) {
		sql="delete from student where sno=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, sno);
			
			return pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** delete Exception => "+e.toString());
			return 0;
		}
	} //delete
	
	// ** Transaction Test
	// => Connection 객체가 관리
	// => 기본값은 AutoCommit  true 임.
	// => setAutoCommit(false) -> commit 또는 rollback 
	// => Test 사항
	//	- 동일자료를 2번 입력 -> 2번째 입력에서 p.key 중복 오류발생 

	// 1) Transaction 적용전
	// => 동일자료를 2번 입력
	//	- 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생 
	//	- Rollback 불가능
	//	- MySql Command 로 1번째 입력 확인 가능 
		
	// 2) Transaction 적용후 
	// => 동일자료를 2번 입력 
	//	- 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생
	//	- Rollback 가능 -> 둘다 취소됨
	
	public void transactionTest() {
		sql="insert into student values(20,'김길동',99,9,'Transaction 적용전',123.45)";
		
		/* 1) Transaction 적용전
		try {
			pst=cn.prepareStatement(sql);
			pst.executeUpdate(); // 1번째는 Table에 입력완료
			pst.executeUpdate(); // 2번째는 p.key 중복오류 발생 -> catch 블럭으로
		} catch (Exception e) {
			System.out.println("** Transaction 적용전 => "+e.toString());
		}
		*/
		// 2) Transaction 적용후 
		try {
			cn.setAutoCommit(false); // Start Transaction
			pst=cn.prepareStatement(sql);
			pst.executeUpdate(); // 1번째는 입력완료 되었지만 Buffer에 보관
			pst.executeUpdate(); // 2번째는 p.key 중복오류 발생 -> catch 블럭으로 -> Rollback
			cn.commit();
		} catch (Exception e) {
			System.out.println("** Transaction 적용후 => "+e.toString());
			// => Rollback
			try {
				cn.rollback();
				System.out.println("** Rollback 성공 **");
			} catch (Exception e2) {
				System.out.println("** Rollback Eception => "+e.toString());
			}
		} //try_catch		
	} //transactionTest
	

} //class
