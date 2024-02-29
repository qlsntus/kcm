package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//=> 해당클래스가 엔티티(테이블)를 위한 클래스이며, 
//해당클래스의 인스턴스들은 JPA의 엔티티매니저가 관리하는 엔티티 객체임을 의미함. 
//DTO 와는 용도를 분리해서 사용할것을 권장함.
//이경우에는 이들을 옮겨주는 메서드가 필요하며, 라이브러리(ModelMapper, MapStruct 등)를 이용할수도 있고,
//DTO 또는 Service interface에 직접 작성하기도함.
//본예제는 Service interface에 default 메서드로 dtoToEntity() 와 entityToDto() 를 작성.
//그러나 List 처리시에 적용 구문이 어려워서 register 에서만 적용해봄
@Table(name="member")
//=> Entity에 해당하는 테이블을 name 속성을 사용하여 매핑함.
//name 생략시에는 클래스의 이름이 매핑됨

public class Member {
	
	@Id
   //=> 테이블의 기본(Primary) Key와 매핑함
   // ** @GeneratedValue(strategy = GenerationType.IDENTITY) 
   // => id로 설정된 기본키의 값을 자동으로 생성할때 추가적으로 사용
   // => strategy 속성: 키 생성전략
   //      - GenerationType.AUTO: default, JPA구현체 (Hibernate 등)가 생성방식 결정  
   //      - GenerationType.IDENTITY: DB가 생성방식 결정 (MySql, Maria 는 auto_increment)  
   //      - GenerationType.SEQUENCE: DB의 sequence를 이용해 생성, @SequenceGenerator 와 같이 사용  
   //      - GenerationType.TABLE: 키생성 전용 테이블을 생성해서 키생성, @TableGenerator 와 같이 사용

	private String id; // Primary_Key
	
	@Column(updatable = false) // 별도로 수정하기 위함
   // ** @Column(name="password", nullable=false, length=10)
   // => 프로퍼티의 이름과 테이블의 칼럼명 같다면 생략 가능하지만, 다른 경우에는 @Column 으로 지정
   // => 컬럼에 다양한 속성 지정 가능 (nullable, name, length, insertable, updatable 등) 
   // => JPA는 INSERT, UPDATE, DELETE의 동작이 보통과 다르기 때문에 예상치못한 실수를 방지하기 위해
   //     insertable 과 updateble 속성을 false로 하여 읽기전용 매핑설정을 할수있다.
   //     이렇게 하면 JPA가 자동으로 생성하는 쿼리에서 제외된다.
   // => columnDefinition 으로 default 값 지정 가능
   //    @Column(columnDefinition="varchar(10) default 'apple'")

	private String password; 
	private String name;
	private int age;
	private int jno;
	private String info;
	private double point;
	private String birthday;
	private String rid; //추천인
	private String uploadfile; //Table 보관용(File_Name)
	
	@Transient // SQL 구문 처리시 제외시켜줌
	private MultipartFile uploadfilef;
	   /*
	   @Temporal(TemporalType.TIMESTAMP)
	   // => 날짜 타입의 변수에 선언하여 날짜타입을 매핑
	   //       TemporalType.DATE : 날짜 정보만 출력
	   //       TemporalType.TIME : 시간정보만 출력
	   //       TemporalType.TIMESTEMP : 날짜 시간 모두
	   private Date myDate = new Date();
	   
	   
	   @Enumerated(EnumType.STRING) 
	   => 열거타입에 대한 매핑은 @Enumerated 를 사용한다.  
	   => EnumType.~~ : 열거형을 DB로 저장할 때 어떤 값으로 저장할지 결정하는속성
	      - EnumType.STRING : 문자열로 저장 "val1, val2, val3" 
	       - EnumType.ORDINAL: 인덱스가 저장 0 ~ 4
	   */
}
