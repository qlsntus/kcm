package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;

import mapperInterface.MemberMapper;
//** Mybatis 적용
//=> CRUD 처리를 Mapper 를 이용
//=> DAO 대신 Mapper interface ->  ~Mapper.xml
import pageTest.SearchCriteria;

//** Mybatis interface 방식으로 적용
//=> MemberDAO 대신 MemberMapper 사용
//=> MemberMapper 의 인스턴스를 스프링이 생성해주고 이를 주입받아 실행함
//  (스프링이 생성해주는 동일한 타입의 클래스는 JUnit Test 로 확인가능, 추후 실습) 
//=> 단, 설정화일에 <mybatis-spring:scan base-package="mapperInterface"/> 반드시 추가해야함
//  MemberDAO의 Sql구문 처리를 mapperInterface 사용으로 MemberMapper 가 역할을 대신함

//=> SQL 구문 : xml 로작성 -> 이 화일을 Mapper 라 함 
//=> Mapper 작성규칙
// -> mapperInterface 와 패키지명, 화일명이 동일해야함
// -> 즉, Java interface , Mapper, Mapper 의 namespace 값(패키지와 화일명)이 모두 동일해야함
// -> 그리고 해당메서드는 Mapper의 xml 구문의 id 속성값으로 찾음




@Service
public class MemberServiceImpl implements MemberService {
	// ** 전역변수 정의
	//@Autowired
	//MemberDAO dao;
	//MemberDAO dao = new MemberDAO();
	
	// ** Mybatis 적용
	// => mapper 의 구현객체는 스프링이 실행시 자동으로 만들어 주입해줌.
	// => 그러므로 개발자는 interface 와 xml 만 구현하고 Service 와 연결해주면 됨. 
	@Autowired
	MemberMapper mapper;
	// ** selectJoList
	// => 조별 맴버 검색
	@Override
	public List<MemberDTO> selectJoList(int jno) {
		return mapper.selectJoList(jno);
	}
	
	//** Member Check_List
		
		@Override
		public List<MemberDTO> mCheckList(SearchCriteria cri) {
			return mapper.mCheckList(cri);
		}
		@Override
		public int mCheckRowsCount(SearchCriteria cri) {
			return mapper.mCheckRowsCount(cri);
		}
	 
		
		//** Member_Paging
		// => ver01: Criteria 사용
		// => ver02: SearchCriteria 사용
		@Override
		public List<MemberDTO> mPageList(SearchCriteria cri) {
			//return mapper.mPageList(cri); //ver01
			return mapper.mSearchList(cri); //ver02
			
		}
		@Override
		public int mtotalRowsCount(SearchCriteria cri) {
			//return mapper.mtotalRowsCount(cri); ver01
			return mapper.mSearchRowsCount(cri); //ver02
			
		}
	
	// ** selectList
	@Override
	public List<MemberDTO> selectList() {
		return mapper.selectList();
	}
	// ** selectOne
	@Override
	public MemberDTO selectOne(String id) {
		return mapper.selectOne(id);
	}
	// ** insert
	@Override
	public int insert(MemberDTO dto) {
		return mapper.insert(dto);
	}
	// ** update
	@Override
	public int update(MemberDTO dto) {
		return mapper.update(dto);
	}
	// ** Password_Update
	@Override
	public int pwUpdate(MemberDTO dto) {
		return mapper.pwUpdate(dto);
	}
	// ** delete
	@Override
	public int delete(String id) {
		return mapper.delete(id);
	}
	
} //class
