package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {

	   // ** Join
	   List<MemberDTO> findMemberJoin();
	//2) @Query 를 이용한 직접쿼리 선언
	//=> password Update에 적용
	//** Password
	void updatePassword(String id, String password);
	
	// ** jno별 Member출력
	//=> JPARepository Method Naming 규약
	List<Member> findByJno(int jno);
	
	// ** selectList
	List<Member> selectList();

	// ** selectOne
	Member selectOne(String id);

	// ** insert,update
	Member save(Member entity);
	
	//** Password_Update
	Member pwUpdate(Member entity);

	// ** delete
	void deleteById(String id);



}