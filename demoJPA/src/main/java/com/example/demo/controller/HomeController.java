package com.example.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;
import com.example.demo.entity.Testkey;
import com.example.demo.entity.TestkeyId;
import com.example.demo.service.GuestbookService;
import com.example.demo.service.TestkeyService;

import lombok.AllArgsConstructor;

//** JPA 기본사항
//* 기본특징
//=> JPA의 가장 큰 특징은 영속성(Persistence)이다.
//=> JPA를 사용한 DB매핑정보(엔티티)는 메모리(영속 컨텍스트)에 저장되고,
//이러한 엔티티를 영속객체라 부른다.

//* EntityManager
//=> 영속 컨텍스트에 접근하여 엔티티에 대한 DB 작업을 제공한다.
//=> 엔티티의 라이프사이클 관리를 위해 
//  persist(), remove(), merge(), flush() 등의 Entity Manger API를 제공. 
//=> 그러나 Spring Data JPA 환경에서는 이를 한단계 더 감싼
//   save(), delete(), findAll() 등의 JPA Repository API를 제공하기 때문에
// EM을 직접 다루는 경우가 적어 졌지만, 다양한 상황에 대응 하기위해서는 EM을 다룰수 있어야함.
//=> https://blog.naver.com/siniphia/223092387343
//  https://bnzn2426.tistory.com/143

//=> @PersistenceContext 
// - EntityManager 객체 주입시 사용하는 애너테이션
// - @Autowired 와 같은 역할, 
//  그러므로 EntityManager 인터페이스을 구현한 클래스가 생성되어 있어야 주입가능
// 이 구현 클래스 생성은 xml 또는 Config화일의 @Bean 등록으로 한다.
//  그러나 SpringBoot JPA 에서는 엔티티 매니저 팩토리 관련 부분을 작성하지 않아도 생성 & 주입 해줌
// ( DemoConfig.java 참고 )

//* Dialect(사투리,방언) 설정 
//=> ORM 프레임웤의 가장 큰 특징은 SQL 구문을 자동 생성하는 것이고
// JPA 에서는 이를 위해 최적화된 SQL 구문을 제공하기 위해  
// DBMS 마다 Dialect 클래스를 제공함
// 
// * Oracle 12g       org.hibernate.dialect.Oracle12cDialect
// * MySQL 5.x        org.hibernate.dialect.MySQL5Dialect  
// * DB2              org.hibernate.dialect.DB2Dialect  
// * Sybase 11.9.2    org.hibernate.dialect.Sybase11Dialect  
// * Sybase Anywhere  org.hibernate.dialect.SybaseAnywhereDialect    

//------------------------------------------------------------

@Controller
@AllArgsConstructor
public class HomeController {
	GuestbookService service;
	TestkeyService tservice;
	//Guest Book
	@GetMapping("/home")
	   //@GetMapping(value={"/", "/home"})
	   // => void : 요청명.jsp 를 viewName 으로 처리함 (home.jsp)
	   //           그러므로 "/" 요청은 .jsp 를 viewName 으로 찾게됨(주의) 
	   // => Boot 의 매핑메서드 에서 "/" 요청은 적용안됨(무시됨) 
	   //     WebMvcConfig 의 addViewControllers 메서드로 해결
	public void home(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
	
	} //home
	@GetMapping("/axtestform")
	public String axTestForm() {
		return "axTest/axTestForm";
	}
	
	@GetMapping("/ginsert")
	public String ginsert() {
		GuestbookDTO dto= GuestbookDTO.builder()
				.title("JPA Insert Test")
				.content("입력이 술술 잘된다~~")
				.writer("admin")
				.build();
		System.out.println("** guest Insert => "+service.register(dto));
		return "redirect:home";
	}
	@GetMapping("/glist")
	public String glist() {
		
		List<Guestbook> list = service.selectList();
		for (Guestbook g:list) {
			System.out.print(g+",regDate="+g.getRegDate()+", modDate="+g.getModDate());
			System.out.println("");
			
		}
		return "redirect:home";
	}
	
	@GetMapping("/gupdate")
	public String gupdate() {
		GuestbookDTO dto= GuestbookDTO.builder()
				.gno(5L)
				.title("JPA Update Test")
				.content("수정이 술술 잘된다~~")
				.writer("banana")
				.build();
		System.out.println("** guest Update => "+service.register(dto));
		return "redirect:home";
	}

	@GetMapping("/gdetail")
	// => 쿼리스트링으로 Test : /gdelete?gno=6
	public String gdetail(Long gno) {
		System.out.println("** gdetail=> "+service.selectOne(gno));
		return "redirect:home";
		
	}
	
	
	@GetMapping("/gdelete")
	// => 쿼리스트링으로 Test : /gdelete?gno=6
	public String gdelete(Long gno) {
		try {
			service.delete(gno);
			System.out.println("** gDelete 대성공 => "+gno);
		} catch (Exception e) {
			System.out.println("** gDelete Exception =>" +e.toString());
			// => 자료가 없으면 org.springframework.dao.EmptyResultDataAccessException 발생확인
		}
		
		return "redirect:home";
	}

	//** JPA Pageing & Sort
	// => PageRequestDTO(페이징 조건들)-> PageRequestDTO(최종결과)
	//=> 사용객체들 : Page<Entity>, Pageable(i) -> PageRequest(구현체) 등
	@GetMapping("/gpage")
	public String gpage() {
		PageRequestDTO requestDTO = PageRequestDTO.builder()
				.page(1).size(5).build();
		//=> 출력할 pageNo, Page당 출력할 row 갯수 입력
		PageResultDTO<GuestbookDTO, Guestbook> resultDTO =
				service.pageList(requestDTO);
		System.out.println("Page List=>" +requestDTO.getPage());
		for (GuestbookDTO g:resultDTO.getDtoList()) {
			System.out.println(g);
		}
		return "redirect:home";
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   // ** JPA 복합키 실습 (@IdClass 방법)
	   @GetMapping("/tinsert")
	   public String tinsert() {
	      Testkey entity = Testkey.builder()
	               .id("green")
	               .no(1)
	               .name("김그린")
	               .count(1)  // JPA save 에서는 MySql에서 정의한 default 1 적용안됨.
	               .build();
	      try {
	         tservice.save(entity);
	         System.out.println("** Testkey SAVE => "+entity);
	      } catch (Exception e) {
	         System.out.println("** SAVE Exception => "+e.toString());
	      }
	      return "redirect:home" ;
	   }
	   
	   // => Update
	   @GetMapping("/tupdate")
	   public String tupdate() {
	      // => Test Data 작성
	      String id="green";
	      int no=1;
	      int count=10;
	      try {
	         tservice.updateCount(id, no, count);
	         System.out.println("** Testkey Update count값 누적=> "+id+no+", "+count);
	      } catch (Exception e) {
	         System.out.println("** UPDATE Exception => "+e.toString());
	      }
	      return "redirect:home" ;
	   }
	   
	   //=> DUPLICATE KEY UPDATE (장바구니 응용)
	   //   없으면 Save 있으면 Update
	   @GetMapping("/tdupupdate")
	   public String tdupupdate() {
	      // => Test Data 작성
	      String id="banana";
	      int no=2;
	      String name="바나나";
	      int count=1;
	      try {
	         tservice.dupUpdateCount(id, no, name, count);
	         System.out.println("** Testkey Update count값 누적=> "+id+no+", "+count);
	      } catch (Exception e) {
	         System.out.println("** DupUpdate Exception => "+e.toString());
	      }
	      return "redirect:home" ;
	   }
	   
	   // ** default 메서드 활용 update
	   @GetMapping("/tcalcCount")
	   public String tcalcCount() {
	      // => Test Data 작성
	      String id="green";
	      int no=1;
	      int count=10;
	      try {
	         tservice.calcCount(id, no, count);
	         System.out.println("** calcCount count+no+100 => "+id+no+", "+count);
	      } catch (Exception e) {
	         System.out.println("** calcCount Exception => "+e.toString());
	      }
	      return "redirect:home" ;
	   }
	   
	   @GetMapping("/testlist")
	   public String testlist() {
	      
	       List<Testkey> list = tservice.selectList(); 
	       for ( Testkey t:list ) {
	          System.out.println(t);
	       }
	      return "redirect:home" ;
	   }

	   @GetMapping("/tdetail")
	   // => 퀴리스트링으로 Test : /tdetail?id=apple&no=1
	   public String tdetail(TestkeyId testid) {
	      System.out.println("tdetail => "+tservice.selectOne(testid));
	      return "redirect:home" ;
	   }
	   
	   @GetMapping("/tdelete")
	   // => 퀴리스트링으로 Test : /tdelete?id=green&no=1
	   public String tdelete(TestkeyId testid) {
	      try {
	         tservice.delete(testid);
	         System.out.println("** tdelete 삭제성공 **");
	      } catch (Exception e) {
	         System.out.println("** tdelete Exception => "+e.toString());
	      }
	      return "redirect:home" ;
	   }
	   
	   // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

}//class

