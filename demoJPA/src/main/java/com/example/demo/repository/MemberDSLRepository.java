package com.example.demo.repository;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

//** QueryDSL 적용
public interface MemberDSLRepository {
	
	// => Entity return
	List<Member> findMemberJnoDSL(int jno);
	
	//=> Join & DTO return
	List<MemberDTO> findMemberJoinDSL();

}
