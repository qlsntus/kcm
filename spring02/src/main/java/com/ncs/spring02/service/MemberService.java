package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.domain.MemberDTO;

import pageTest.SearchCriteria;

public interface MemberService {

    //** Member Check_List
	public List<MemberDTO> mCheckList(SearchCriteria cri) ;
	public int mCheckRowsCount(SearchCriteria cri) ;
	
	
		
	//** Member_Paging
	// => ver01: Criteria 사용
	// => ver02: SearchCriteria 사용
	public List<MemberDTO> mPageList(SearchCriteria cri) ;
	public int mtotalRowsCount(SearchCriteria cri) ;

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