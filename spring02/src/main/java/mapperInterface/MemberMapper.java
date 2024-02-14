package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

public interface MemberMapper {
	
	
	// ** selectJoList
	// => 조별 맴버 검색
	List<MemberDTO> selectJoList(int jno);

	// ** selectList
	List<MemberDTO> selectList();

	// ** selectOne
	MemberDTO selectOne(String id);

	// ** insert
	int insert(MemberDTO dto);

	// ** update
	int update(MemberDTO dto);
	
	//** Password_Update
	int pwUpdate(MemberDTO dto);

	// ** delete
	int delete(String id);

}
