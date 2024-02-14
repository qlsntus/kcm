package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.JoDTO;

public interface JoMapper {
	
	
	// ** selectList
	List<JoDTO> selectList();

	// ** selectOne
	JoDTO selectOne(JoDTO dto);

	// ** Insert
	int insert(JoDTO dto);

	// ** Update
	int update(JoDTO dto);

	// ** Delete
	int delete(JoDTO dto);

}