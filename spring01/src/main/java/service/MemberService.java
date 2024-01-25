package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import domain.MemberDTO;
import model.MemberDAO;
//@Component
@Service
public class MemberService {
   // ** 전역변수 정의
	@Autowired
	MemberDAO dao;
//   MemberDAO dao = new MemberDAO();
   
   // ** selectList
   public List<MemberDTO> selectList() {
      return dao.selectList();
   }
   // ** selectOne
   public MemberDTO selectOne(String id) {
      return dao.selectOne(id);
   }
   // ** insert
   public int insert(MemberDTO dto) {
      return dao.insert(dto);
   }
   // ** update
   public int update(MemberDTO dto) {
      return dao.update(dto);
   }
   // ** delete
   public int delete(String id) {
      return dao.delete(id);
   }
} // class