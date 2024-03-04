package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;

@Transactional
@Repository
public class MyRepositoryImpl implements MyRepository {
	
	private final EntityManager em;
	
	public MyRepositoryImpl(EntityManager em) {
		this.em=em;
	}
	
	//@Override
	public List<Member> emMemberList2() {
		return em.createQuery("select m from Member m order by m.id asc",Member.class)
				.getResultList();
		
	//=> JPQL 적용
	//=> "select * from Member order by id asc" 500 오류 발생
	//	antlr.NoviableAltException: unexpected token: *~~~
	//  Entity 를 통해 접근하기 떄문에 * 사용금지, 앨리어스를 사용해 컬럼명 접근
		
	}
	//=> Paremeter 적용
	@Override
	public List<Member> emMemberList() {
		
		return em.createQuery("select m from Member m where jno<:jno order by jno", Member.class)
				.setParameter("jno", 7)
				.getResultList();
	}
	
	@Override
	public Member emMemberDetail(String id) {
		
		return em.createQuery("select m from Member m where id=:ii", Member.class)
						.setParameter("ii", id)
						.getSingleResult();
	}
	
}//class
