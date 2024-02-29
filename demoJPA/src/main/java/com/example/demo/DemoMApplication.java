package com.example.demo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//http://localhost:8080/ 로컬번호!!
// ** ORM (Object Relational Mapping)
//=> 객체지향 패러다임(Java객체)을 관계형 패러다임(DB의 Table) 과 매핑하여 
// SQL 구문 생성

//** JPA (Java Persistence API)
//=> ORM을 Java에 맞게 사용하는 스펙 
//=> 대표적 오픈소스: Hibernate (SpringBoot 사용)

//** Spring Data JPA 라이브러리
//=> SpringBoot에서 Hibernate를 쉽게 사용할수있는 API들을 제공  
//=> 관계도 (교재48p 그림2-28 참고)
//   JavaCode -> Spring Data JPA -> Hibernate -> JDBC -> DB:MySql

//** 프로젝트 실행
//=> dataSource 설정 전 : ~ Failed To Start ~ 
//프로젝트 생성시 Spring Data JPA 를 추가했지만 이를 연동할 dataSource 설정이 없어 서버_Start_오류  
//=> dataSource 설정 후 : Tomcat started on port(s): 8080 (http) ~~

//** SpringBoot JPA (Java Persistence API) **********************
//1. 설정
//=> pom.xml: JDBC, JSP dependency 추가 
//=> application.properties: server.port, dataSource, Jsp_view 설정
// - application.properties 의 설정항목은 "Spring Boot Reference documents" 검색 참고.
//  - SpringBoot 의 DB Connection
//    스프링 부트는 라이브러만 있으면 해당 라이브러리의 설정을 자동으로 찾아서 실행하며,
//    DB Connection의 경우에도 위 dataSource 설정만 해주면 스프링부트가 자동으로 사용하는
//   HikariCP(Connection Pool)을 실행하여 제공한다.
//  - JPA Hibernate 설정

//2. Entity 클래스 만들기
//=> 일반규칙: 패키지명 entity, 클래스명 Table명과 동일

//3. Repository interface 만들기
//=> 일반규칙: 패키지명 repository, interface명 ~Repository, 
//       JpaRepository<T,E> interface 상속받음 
//=> 이 interface 작성하는것만으로 모든 연결이 되고 JPA 메서드 사용이 가능함.
//=> JpaRepository<T,E> interface
//  - JPA 를 통해 구현가능한 기본적인 기능을 미리 정의해 놓음 (소스확인하기)
//  - 용도에 맞게 개발자가 직접 작성해도 됨   

//4. Service & Controller 구현 Test

//** 실습
//=> 방명록
//=> BaseEntity -> Guestbook: 프로젝트실행, Table_guestbook 생성확인 (console창과 MySql에서) 
//-> GuestbookRepository -> GuestbookService -> Controller, home 메뉴추가 -> Test
//=> Member
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
@EnableJpaAuditing // BaseEntity 참고 : 자동감지하는 리스너를 작동시켜줌
//@MapperScan("mapperInterface")
public class DemoMApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMApplication.class, args);
	}

}
