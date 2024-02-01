package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

public interface BoardService {

	// ** selectList
	public List<BoardDTO> selectList() ;
		
	
	// ** selectOnt
	public BoardDTO selectOne(int seq) ;
		
	
	// ** insert
	public int insert(BoardDTO dtd) ;
	
	// ** replyInsert
	public int 	rinsert(BoardDTO dto);

		
	
	// ** update
	public int update(BoardDTO dto) ;
	
	
	
	// ** delete
	public int delete(BoardDTO dto ) ;


	
		
	
	
}//class
