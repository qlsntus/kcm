package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.MemberService;

//** IOC/DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=> @Controller
//	-> 사용자 요청을 제어하는 Controller 클래스
//	-> DispatcherServlet이 해당 객체를 Controller객체로 인식하게해줌. 	
//	-> interface Controller 의 구현의무 없어짐
//	-> 이로인해 메서드 handleRequest() 의 오버라이딩 의무 없어짐
//	-> 이로인해 메서드명, 매개변수, 리턴타입(ModelAndView, String, void 중 선택) 에 자유로워짐
//	-> 그리고 클래스 와 메서드 단위 매핑이 가능한 @RequestMapping 사용가능
//	-> 그러므로 하나의 컨트롤러 클래스에 여러개의 매핑메서드의 구현이 가능해짐
//	-> 그래서 주로 테이블(엔티티) 단위로 작성함 ( MemberController.java )

//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//			   DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가 
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//** Spring 의 redirect ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//** RedirectAttributes
//=> Redirect 할 때 파라메터를 쉽게 전달할 수 있도록 지원하며,
// addAttribute, addFlashAttribute, getFlashAttribute 등의 메서드가 제공됨.
//=> addAttribute
// - url에 퀴리스트링으로 파라메터가 붙어 전달됨. 
// - 그렇기 때문에 전달된 페이지에서 파라메터가 노출됨.

//=> addFlashAttribute
// - Redirect 동작이 수행되기 전에 Session에 값이 저장되고 전달 후 소멸된다.
// - Session을 선언해서 집어넣고 사용 후 지워주는 수고를 덜어주고,
//    -> url에 퀴리스트링으로 붙지 않기때문에 깨끗하고 f5(새로고침)에 영향을 주지않음.  
//    -> 주의사항 
//       받는쪽 매핑메서드의 매개변수로 parameter를 전달받는 DTO가 정의되어 있으면
//       이 DTO 생성과 관련된 500 발생 하므로 주의한다.
//      ( Test : JoController 의 jupdate 성공시 redirect:jdetail )
//      단, VO로 받지 않는 경우에는 url에 붙여 전달하면서 addFlashAttribute 사용가능함        

//=> getFlashAttribute
//    - insert 성공 후 redirect:jlist 에서 Test (JoController, 결과는 null)
//    - 컨트롤러에서 addFlashAttribute 가 session에 보관한 값을 꺼내는것은 좀더 확인이 필요함 

//** redirect 로 한글 parameter 전달시 한글깨짐
//=> 한글깨짐이 발생하는경우 사용함.
//=> url 파라메터 로 전달되는 한글값 을 위한 encoding
//    - String message = URLEncoder.encode("~~ member 가 없네용 ~~", "UTF-8");
//      mv.setViewName("redirect:mlist?message="+message);  
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//** Model & ModelAndView **

//=> Model(interface)
//-> controller처리 후 데이터(Model) 을 담아서 반환 
//-> 구현클래스 : ConcurrentModel, ExtendedModelMap 등.
//-> 아래의 매핑 메서드들 처럼, ModelAndView 보다 심플한 코드작성 가능하므로 많이사용됨. 
// mv.setViewName("~~~~~") 하지않고 viewName 을 return 

//=> ModelAndView (class)
//-> controller처리 후 데이터(Model) 와 viewName 을 담아서 반환
//-> Object -> ModelAndView
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//** @RequestMapping
//=> DefaultAnnotationHandlerMapping에서 컨트롤러를 선택할 때 대표적으로 사용하는 애노테이션. 
//=> DefaultAnnotationHandlerMapping은 클래스와 메서드에 붙은 @RequestMapping 애노테이션 정보를 결합해 최종 매핑정보를 생성한다.
//=> 기본적인 결합 방법은 클래스 레벨의 @RequestMapping을 기준으로 삼고, 
//  메서드 레벨의 @RequestMapping으로 세분화하는 방식으로 사용된다.

//** @RequestMapping 특징
//=> url 당 하나의 컨트롤러에 매핑되던 다른 핸들러 매핑과 달리 메서드 단위까지 세분화하여 적용할 수 있으며,
// url 뿐 아니라 파라미터, 헤더 등 더욱 넓은 범위를 적용할 수 있다. 
//=> 요청과 매핑메서드 1:1 mapping 
//=> value="/mlist" 
// : 이때 호출되는 메서드명과 동일하면 value 생략가능 그러나 value 생략시 404 (확인필요함)
// : 해당 메서드 내에서 mv.setViewName("...."); 을 생략 
//  또는 아래의 메서드를 사용하는 경우에는 void 로 작성 (view 를 return 하지않음) 하는 경우
//   요청명을 viewName 으로 인식 즉, mv.setViewName("mlist") 으로 처리함.
//  또는 return "mlist" ( 즉, mlist.jsp 를 viewName으로 인식 )

//** @RequestMapping 속성
//=> value : URL 패턴 ( 와일드카드 * 사용 가능 )
//  @RequestMapping(value="/post")
//  @RequestMapping(value="/post.*")
//  @RequestMapping(value="/post/**/comment")
//  @RequestMapping(value={"/post", "/P"}) : 다중매핑 가능

//=> method 
// @RequestMapping(value="/post", method=RequestMethod.GET)
// -> url이 /post인 요청 중 GET 메서드인 경우 호출됨
// @RequestMapping(value="/post", method=RequestMethod.POST)
// -> url이 /post인 요청 중 POST 메서드인 경우 호출됨
//    GET, POST, PUT, DELETE, OPTIONS, TRACE 총 7개의 HTTP 메서드가 정의되어 있음.
//    ( 이들은 아래 @GetMapping ... 등으로도 좀더 간편하게 사용가능
//      그러나 이들은 메서드 레벨에만 적용가능    )  

//=> params : 요청 파라미터와 값으로도 구분 가능함.
// @RequestMapping(value="/post", params="useYn=Y")
// -> /post?useYn=Y 일 경우 호출됨
// @RequestMapping(value="/post", params="useYn!=Y")
// ->  not equal도 가능
// @RequestMapping(value="/post", parmas="useYn")
// > 값에 상관없이 파라미터에 useYn이 있을 경우 호출됨
// @RequestMapping(value="/post", params="!useYn")
// > 파라미터에 useYn이 없어야 호출됨
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//** Lombok 지원 로그메시지  
//=> @Log4j Test
// -> dependency 필요함 (pom.xml 확인)
// -> 로깅레벨 단계 준수함 ( log4j.xml 의 아래 logger Tag 의 level 확인)
//    TRACE > DEBUG > INFO > WARN > ERROR > FATAL(치명적인)
//    <logger name="com.ncs.green">
//       <level value="info" />
//    </logger>   

// -> Logger 사용과의 차이점 : "{}" 지원안됨 , 호출명 log
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired(required = false)
	MemberService service;

//** ID 중복확인
	@GetMapping("/idDupCheck")
	public void idDupCheck(@RequestParam("id")String id, Model model) {
		
		// 1) newID 존재여부 확인 & 결과처리
		if (service.selectOne(id) != null) {
			//=> 사용 불가능
			model.addAttribute("idUse", "F");
			
		}else {
			//=> 사용가능
			model.addAttribute("idUse", "T");
		}
		
		
		
		
		
		
	} //idDupCheck
	
	
	
	
// ** Login Form 출력	
// => ver01 : return String	
//	public String loginForm(Model model) {
//		return "member/loginForm";
//	} //loginForm
	
// => ver02 : return void	
// => vireName 생략
//	- 요청명과 동일한 viewName 을 찾음 
//	- "/WEB-INF/views/member/loginForm.jsp" 완성됨
	@RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public void loginForm() {
	} //loginForm
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpSession session, Model model, MemberDTO dto) {
		// => 매핑메서드의 인자객체 와 동일한 컬럼명의 값은 자동으로 할당함. 	
		//		아래의 구문은 필요없음
		//		String id = request.getParameter("id");
		//		dto.setId(id);
		
		// 1. 요청분석
		// => requst 로 전달되는 id, password 처리: 
		//    매서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
		//   ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌 )
		// => 전달된 password 보관
		String password = dto.getPassword();
		String uri = "redirect:/home";  //성공시
		
		// 2. 서비스 & 결과 처리
		// => id 확인 
		// => 존재하면 Password 확인
		// => 성공: id, name은 session에 보관, home 으로
		// => 실패: 재로그인 유도
		dto = service.selectOne(dto.getId());
		if ( dto !=null && dto.getPassword().equals(password)) {
			// 성공
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
		}else {
			// 실패
			uri="member/loginForm";
			model.addAttribute("message", "~~ id 또는 password 오류 !! 다시하세요 ~ ");
		}
		return uri;
	}
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	} //logout
	
	// ** Member Detail
	// => 단일 Parameter 의 경우 @RequestParam("...") 활용
	//    String jCode = request.getParameter("jCode") 과 동일
	//	  단, 해당하는 Parameter 가 없으면 400 오류 발생
	//    그러므로 detail 요청에도 ?jCode=D 를 추가함. 
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public String detail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
		// 1. 요청분석
		// => id: session 에서 get
		// => detail 또는 수정 Page 출력을 위한 요청인지 jCode 로 구별 
		String id = (String)session.getAttribute("loginID");
		String uri = "member/memberDetail"; // detail
		
		// => update 요청확인후 uri 수정 
		if ("U".equals(jCode))
			uri = "member/updateForm";
		
		// 2. Service & 결과 처리
		model.addAttribute("apple", service.selectOne(id));
		return uri;
	} //detail
	
	// ** Member List
	@RequestMapping(value="/memberList", method = RequestMethod.GET)
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
	} //mList
	
	// ** Join Form **********************************
	@RequestMapping(value="/joinForm", method = RequestMethod.GET)
	public void joinForm() {
	} //joinForm

	// ** Join
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(Model model, MemberDTO dto) {
		// 1. 요청분석
		// => 이전: 한글처리, request 값 -> dto 에 set
		// => 스프링: 한글은 filter, request 처리는 매개변수로 자동화
		String uri = "member/loginForm"; //성공시
		
		// 2. Service & 결과
		if ( service.insert(dto) > 0 ) {
			model.addAttribute("message", "~~ 회원가입 성공 !! 로그인 후 이용하세요 ~~");
		}else { 
			// 실패 : 재가입 유도
			uri = "member/joinForm";
			model.addAttribute("message", "~~ 회원가입 실패 !! 다시 하세요 ~~");
		}
		return uri;
	} //join
	
	// ** Update
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(HttpSession session, Model model, MemberDTO dto) {
		// 1. 요청분석
		// => 성공: memberDetail, 실패: updateForm
		// => 두경우 모두 출력하려면 dto 객체의 값("apple") 이 필요하므로 보관
		
		String uri = "member/memberDetail"; //성공시
		model.addAttribute("apple", dto);
		
		// 2. Service & 결과
		if ( service.update(dto) > 0 ) {
			model.addAttribute("message", "~~ 회원 정보 수정 성공 !! ~~");
			// => name 을 수정할수도 있으므로 loginName 을 수정해준다
			session.setAttribute("loginName", dto.getName());
		}else { 
			// 실패 : 재수정 유도
			uri = "member/updateForm";
			model.addAttribute("message", "~~ 회원 정보 수정 오류 !! 다시 하세요 ~~");
		}
		return uri;
	} //update
	
	// ** Delete
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(HttpSession session, Model model, RedirectAttributes rttr) {
		// 1. 요청분석
		// => id: session 에서 get
		// => delete & session 처리
		String id = (String)session.getAttribute("loginID");
		String uri = "redirect:/home"; 
	
		// 2. Service & 결과 처리
		if ( service.delete(id) > 0 ) {
			model.addAttribute("message", "~~ 탈퇴 성공 !! 1개월 후 재가입 가능합니다.");
			// => requestScope 의 message 를  redirect 시에도 유지하려면
			// session 에 보관했다가 사용후에는 삭제해야함
			// session 에 보관후 redirect 되어진 요청 처리시에 requestScope에 옮이고
			// session 의 message는 삭제 
			// => 이런것들을 처리해주는 API가 RedirectAttriboue이다.
			rttr.addFlashAttribute("message", "~~ 탈퇴 성공 !! 1개월 후 재가입 가능합니다. ~~");
			session.invalidate();
		}else {
			model.addAttribute("message", "~~ 탈퇴 실패 !! 관리자에게 연락 하세요 ~~");
			rttr.addFlashAttribute("message", "~~ 탈퇴 실패 !! 관리자에게 연락 하세요 ~~");
		}
		return uri;
	} //delete
	
} //class
