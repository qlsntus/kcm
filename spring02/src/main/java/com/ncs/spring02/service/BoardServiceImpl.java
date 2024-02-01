package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.model.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO dao ;
	@Override
	public int rinsert(BoardDTO dto) {
		// TODO Auto-generated method stub
		return dao.rinsert(dto);
	}
	
	@Override
	public List<BoardDTO> selectList() {
		
		return dao.selectList();
	}
	
@Override
public BoardDTO selectOne(int seq) {

	return dao.selectOne(seq);
}
@Override
public int insert(BoardDTO dto) {
	
	return dao.insert(dto);
}
@Override
public int update(BoardDTO dto) {
	
	return dao.update(dto);
}

@Override
public int delete(BoardDTO dto) {
	
	return dao.delete(dto);
}

}// class 


