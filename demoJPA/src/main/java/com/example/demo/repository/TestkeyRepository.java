package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Testkey;
import com.example.demo.entity.TestkeyId;


// ** JPA 복합키실습 (@IdClass 방법) 과 count 값 수정하기  
// => DML 사용시 @Modifying, @Transactional 적용 필수.
// 1) JPQL : OK
// 2) Native_SQL : OK
// 3) default 메서드로 구현
//	=> 복잡한 계산식에 활용
//	=> 계산식을 메서드로 구현하고, 쿼리메서드를 호출하여 적용
//		-> 서비스는 default 메서드를 호출

public interface TestkeyRepository 
					extends JpaRepository<Testkey, TestkeyId> {
	
	// ** Update 구문
	@Modifying 
	@Transactional
	// => JPQL: OK (Entity명 사용) 
	@Query("update Testkey t SET t.count = t.count + :count where t.id = :id and t.no = :no")
	// => Native_SQL : OK (Table명 사용)	
	//@Query( nativeQuery=true, 
	//		  value="update testkey SET count=count+:count where id=:id and no=:no" )
	void updateCount(@Param("id") String id, @Param("no") int no, @Param("count") int count);
	
	// ** DUPLICATE KEY UPDATE 구문
	@Modifying 
	@Transactional
	@Query( nativeQuery=true,
			value = "insert into testkey VALUES (:id, :no, :name, :count)"
				 + " ON DUPLICATE KEY UPDATE count = count+:count")
	void dupUpdateCount(@Param("id") String id, @Param("no") int no, 
						@Param("name") String name, @Param("count") int count);
	
	//** default 메서드 사용하기
	@Modifying
    @Transactional
    @Query("update Testkey t SET t.count = :result where t.id = :id and t.no = :no")
    void updateSql(@Param("id") String id, @Param("no") int no, @Param("result") int result);
	
	default void calcCount(@Param("id") String id, @Param("no") int no, @Param("count") int count) {
		System.out.println("** JPA 로직 진입 **");
        // 1) 계산 로직을 구현하여 result 값을 계산
        int result = count+no+100 ;
        System.out.println("** result =>" + result);

        // 2) updateSql 쿼리 메서드를 호출하여 업데이트 수행
        updateSql(id, no, result);
        System.out.println("** JPA 로직 종료 **");
	} //calcCount
	 
}
