package com.example.demo.repository;

import static com.example.demo.entity.QJo.jo;
import static com.example.demo.entity.QMember.member;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDSLRepositoryImpl implements MemberDSLRepository {
	
	private final JPAQueryFactory jPAQueryFactory;
	
	//1) Entity return
	// => Q클래스로 SQL 구문 작성하고 Entity return
	// => Parameter로 전달된 조원들만 출력하기
	@Override
	public List<Member> findMemberJnoDSL(int jno) {
		return jPAQueryFactory.selectFrom(member)
				.where(member.jno.eq(jno).and(member.point.goe(4000)))
				.orderBy(member.age.desc())
				.fetch();
				
				
	}
	   // 2) Join & DTO return
	   // => QueryDSL 에서 DTO 적용하기
	   // => 메모장 QueryDSL사용법.txt 참고  
	   //    4종류 방법중 1) Setter 접근 , 2) 필드 직접접근 적용
	   
	   // 2.1) Setter 접근 
	   // => MemberDto의 setter 를 호출해서 ,  Dto 의 멤버변수에 injection 해주는 방식.
	   // => Projections.bean(~~~)  로 접근

	   //@Override
	   public List<MemberDTO> findMemberJoinDSL() {
	   return jPAQueryFactory.select(
			  Projections.bean(MemberDTO.class,
			member.id, member.name, member.jno, jo.jname, jo.project)) //bean
			  .from(member)
			  .leftJoin(jo)
			  .on(member.jno.eq(jo.jno))
			  .fetchJoin()
			  .fetch();
					  
	   }
	   // 2.2) 필드 직접 접근 
	   // => 필드에 직접 접근해서 값을 injection 하는 방식.
	   // =>Projections.fields(~~~) 로 접근
	   //    그러므로 DTO 에 setter/getter 없어도 가능하며
	   //    MemberDto의 멤버변수에 값이 injection 된다.
	   
	   public List<MemberDTO> findMemberJoinDSL2() {
		   return jPAQueryFactory.select(
				  Projections.fields(MemberDTO.class,
				member.id, member.name, member.jno, jo.jname, jo.project)) //bean,select
				  .from(member)
				  .leftJoin(jo)
				  .on(member.jno.eq(jo.jno))
				  .fetchJoin()
				  .fetch();
						  
		   }
	   
	 
	

}
