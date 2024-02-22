package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardDTO;

import pageTest.SearchCriteria;

public interface BoardService {
	//** Board Check_List
	public List<BoardDTO> bCheckList(SearchCriteria cri) ;
	public int bCheckRowsCount(SearchCriteria cri) ;
	
	
	
	//** Board_Paging
	// => ver01: Criteria 사용
	// => ver02: SearchCriteria 사용
	public List<BoardDTO> bPageList(SearchCriteria cri) ;
	public int totalRowsCount(SearchCriteria cri) ;
	
	
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
