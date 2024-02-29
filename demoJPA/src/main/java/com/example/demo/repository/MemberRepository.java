package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
//** JPA 쿼리의 특징
//=> 테이블이 아닌 엔티티 객체를 대상으로 처리함.

//** JPA의 다양한 쿼리 방법
//1) JPARepository Method 규약
//2) @Query : JPQL, Native_SQL
//3) JPA Criteria
//4) QueryDSL
//5) Repository intreface 직접 작성 & EntityManager 사용하기   
// => MyRepository, MyRepositoryImpl 참고 
//6) JDBC API 직접 사용, MyBatis, SpringJdbcTemplate 함께 사용

//** JPA Repository에 쿼리 메서드 추가하기
//( https://blog.naver.com/mion0602/223183393956 )

//1) JPARepository Method 규약 이용하기
//=> findBy + 검색칼럼 (첫문자는 반드시 대문자로)
//  Optional<CalCategory> findByCtgrIdAndCtgrSeqn(String id, Long seqn);
//  -> select * from categoryinfo where ctgrId = id and ctgrSeqn = seqn 라는 쿼리 자동생성됨.
//  List<Member> findbySearchId(String keyword);   
//  -> List 형식 return 의 경우에도 findby~~~   
//
//=> 가능하면 @Query("...") 보다는 Method 규약을 이용하는것이 작업량을 줄일수 있으므로 권장됨.
//  ( JPA자체가 반복적인 작업량을 줄이기 위해 나온 것이니 만큼... ) 

//=> 다양한 사용예시 https://blog.naver.com/mion0602/223183393956
//  ( count함수, LIKE 등... ) 

//2) @Query선언을 이용한 직접쿼리 선언
//=> @Query("...")
//=> Spring Data JPA에서 JPQL 또는 네이티브 SQL쿼리를 사용할 수 있도록 지원하는 @
//=> @Query는 JPQL(Java Persistence Query Language) 을 기본으로 사용하며
//  Native_SQL 사용시에는 nativeQuery 속성을 true로 설정    

//2.1) JPQL(Java Persistence Query Language)
//=> 객체 지향 쿼리 언어
//=> Table 명은 Entity명 사용함.
//=> SQL과 대부분의 문법이 유사함. (SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원)
//=> 그러나 특정 데이터베이스에 종속적인 기능은 지원하지 않음 
//  ( Ex.   - 특정 DB만 지원하는 함수, 문법, SQL 쿼리 힌트
//      - 인라인 뷰, UNION, INTERSECT, 스토어드 프로시저)
//      - 다양한 이유로 JPQL을 사용할 수 없을 때, Native_SQL을 통해 SQL 직접 사용가능. )
//=> 단, JPQL 의 select 의 return Type은 List<Object[]> 이며,
//  이를 ~DTO 로 받기 위해서 select new ~~~ 를 사용함.

//2.2) Native_SQL 및 
//=> DB 테이블을 대상으로함 
//=> @Query의 nativeQuery 속성을 true로 설정, value 속성에 SQL구문 작성 
//  -> 예)   
//   @Query(value = "SELECT * FROM USERS u WHERE u.status = 1"
//         , nativeQuery = true)
//   List<User> findAllActiveUsersNative();

//2.3) 공통사항
//=> 파라미터는 @Param("파라미터명")String id 로 선언후 "=:파라미터명" 으로 사용
//=> DML 사용시에는 @Modifying, @Transactional 반드시 사용.

//=> https://blog.naver.com/emblim98/223222404308 참고

//3) JPA Criteria Query(객체지향 쿼리 빌더)
//=> JPQL Query Builder Class
//=> JPQL을 자바 코드로 작성하도록 도와주는 빌더 클래스 API
//  자바코드로 JPQL을 작성할 수 있도록 도와주는 JPQL의 빌더 역할이라고 보면됨.
//=> 장점: 자바코드이므로 오타가 나면 컴파일 오류가 난다. 동적 코드 가능
//=> 단점: 유지보수가 너무 어렵다. (본인의 코드도 나중에 다시 보면 이해하기 힘들다.)     
//=> 그러므로 QueryDSL 사용을 권장.

//4) QueryDSL
//=> Querydsl - 레퍼런스 문서
// http://querydsl.com/static/querydsl/4.0.1/reference/ko-KR/html_single/
//=> QueryDSL사용법.txt 메모장 참고

//=> 문자가 아닌 자바코드로 쿼리를 작성함으로 컴파일 시점에 오류를 찾을수 있다.
//=> IDE의 자동완성 도움을 받을 수 있음.
//=> 동적 쿼리를 작성하기 편리하다.
//=> 쿼리 작성시 제약조건 등을 메서드추출을 통해 재사용할 수 있다.
//=> 결론
//  JPQL에 비하여 풍부한 체이닝메서드 및 유틸리티 메서드 와 Q클래스를 기반으로 직관적으로 쿼리를 작성함.

//=> 방법
//- 프로젝트 내의 @Entity 어노테이션을 선언한 클래스를 탐색하고, 
//  JPAAnnotationProcessor를 사용해 Q 클래스를 생성.
// ( pom.xml 의 설정을 통해 자동실행,  QueryDSL사용법.txt 메모장 참고)    
//- Q클래스 파일에 접근 해서 DB 작업
// - QueryDSL로 쿼리를 작성할때 Q클래스를 사용함으로써쿼리를 Type-Safe하게 작성할 수 있음.

//=> 적용 MemberDSLRepository.java

//5) JDBC API 직접 사용
//=> JPA를 사용하면서 JDBC 커넥션을 직접 사용하거나, 스프링 JdbcTemplate, Mybatis 등 함께 사용 가능하다.
//=> 단, 영속성 컨텍스트를 적절한 시점에 강제로 플러시 필요하다. 
//  즉, JPA에서 지원하는 기본 쿼리문들은 모두 flush()처리를 해주지만, 
//  JDBC를 직접사용하는 경우에는 직접 flush() 를 해야함.

//** JPQL 의 Fetch Join
//=> JPQL에서 성능 최적화를 위해 제공하는 기능이다.
//=> 연관된 엔티티나 컬렉션을 SQL한번에 함께 조회 가능하며
//일반 Join 구문이 2단계로 처리되는것에 비하여 한번에 로딩함. 
//=> 페치 조인은 N+1문제를 해결 (member 의 갯수 만큼 호출)
//=> [ LEFT [OUTER] | INNER ] JOIN FETCH 조인경로
//=> JPQL 예시
//select m from Member m join fetch m.jno
//=> SQL 과 비교
//SELECT m.*, j.* FROM member m INNER JOIN jo j ON m.jno=j.jno
//
//=> Fetch Join 의 한계
//- 별칭(alias)을 줄 수 없다.
//  하이버네이트에서는 가능하지만 사용하지 않는것을 권장
//  join fetch를 연달아 반복 사용하는 극히 일부 상황에서만 사용하기도 하지만 가급적 권장하지 않음.
//  (차라리 별도의 쿼리를 다시 날리는 것을 권장한다.)   
//- 둘 이상의 컬렉션은 패치 조인 할 수 없다.
//- 컬렉션을 페치조인 할 경우, 페이징 API(setFirstResult, setMaxResults)를 사용할 수 없다. 
//- 조인결과가 엔티티 와는 다른 결과를 내야 한다면, 페치조인 보다는 일반조인을 사용하고 별도의 DTO를 사용하는것이 효과적.

//** Join 1
//=> @Query("...") 에 JPQL, LEFT_JOIN 구문, MemberJoDTO return
//=> select 결과를 받기위한 ~DTO 작성
//=> Repository 에 Join 메서드 정의
//=> @Query("...") 에 JPQL로 SQL구문 작성,
//=> 연관성 (Foreign_key) 설정이 없는 경우도 Join 가능

//=> Join 2 : MemberDSLRepositoryImpl.java, findMemberJoinDSL() 참고







public interface MemberRepository 
                   extends JpaRepository<Member, String>{
	//1) JPARepository Method Naming 규약
	// => jno별 Member 출력
	List<Member> findByJno(int jno);
	
	//2) @Query 를 이용한 직접쿼리 선언
	//=> password Update에 적용
	//2.1) JPQL
	//=> table 명 대신 Entity명을 사용
	@Modifying
	@Transactional
	@Query("update Member set password=:password where id=:id")
	void updatePassword1(@Param("id") String id,@Param("password") String password);
	
	//2.2) nativeQuery 적용
	// => nativeQuery 속성 true, Table명 사용
	@Modifying
	@Transactional
	@Query(nativeQuery=true, 
			value="update Member set password=:password where id=:id")
	void updatePassword(@Param("id") String id, @Param("password") String password);
	
	   // 2.3) Join 구문에 @Query 적용
	   // => JPQL
	   // -entity 가 아닌 MemberDTO로 return 받기위해 new 사용
	   // - Table 명이 아닌 Entity 명 사용
	  @Query("SELECT new com.example.demo.domain.MemberDTO(m.id, m.name, m.jno, j.jname, j.project) FROM Member m LEFT JOIN Jo j ON m.jno=j.jno order by m.jno")
	  List<MemberDTO> findMemberJoin();
	
}


	
	
