package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

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




/*
 * @Service public class MemberServiceImpl implements MemberService { //** 전역변수
 * 정의 private final MemberRepository repository;
 * 
 * @Override public List<Member> selectList() { return repository.findAll(); }
 * // ** selectOne
 * 
 * @Override public MemberDTO selectOne(String id) { return
 * mapper.selectOne(id); } // ** insert
 * 
 * @Override public int insert(MemberDTO dto) { return mapper.insert(dto); } //
 * ** update
 * 
 * @Override public int update(MemberDTO dto) { return mapper.update(dto); } //
 * ** Password_Update
 * 
 * @Override public int pwUpdate(MemberDTO dto) { return mapper.pwUpdate(dto); }
 * // ** delete
 * 
 * @Override public int delete(String id) { return mapper.delete(id); }
 * 
 * } //class
 */