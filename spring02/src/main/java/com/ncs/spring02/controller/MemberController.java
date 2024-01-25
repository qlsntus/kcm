package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.MemberService;



//** IOC/DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=>  @Controller 
//->사용자 요청을 제어하는 Controller 클래스
//->DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌.
//-> interface Controller 의 구현의무 없어짐
//-> 이로인해 메서드 handleRequest()의 오버라이딩 의무 없어짐
//-> 이로인해 메서드명, 매개변수, 리턴타입(ModelAndView, String, void중 선택)에 자유로워짐
//-> 그리고 클래스와 메서드 단위 매핑이 가능한 @RequestMapping 사용가능 
//-> 그러므로 하나의 컨트롤러안에 클래스에 여러개의 매핑메서드 구현이 가능해짐
//-> 그래서 주로 테이블(엔티티) 단위로 작성함 (MemberController.java)


//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//       DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가

@Controller
@RequestMapping(value="/member")
public class MemberController {
	@Autowired(required = false)
	MemberService service;
//	
//	@RequestMapping(value ="/loginForm",method = RequestMethod.GET)
//	public String loginForm(Model model) {		
//		return "member/loginForm";
//	} //loginForm
	// => ver02 : return void
	//=> viewName 생략
	//- 요청명과 동일한 viewName을 찾음
	// -"/WEB-INF/views/member/loginForm.jsp" 완성됨
	@RequestMapping(value="/loginForm", method = RequestMethod.GET)
	public void loginForm() {
		
	}//loginForm
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpSession session, Model model, MemberDTO dto ) {
// => 메핑메서드의 인자객체 와 동일한 컬럼명의 값은 자동으로 할당함.
//		아래의 구문은 필요없음
//		String id = request.getParameter("id");
//		dto.setId(id);
		
	      // 1. 요청분석
	      // => requst 로 전달되는 id, password 처리: 
	      //    매서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
	      //   ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌 )
	      // => 전달된 password 보관
		String password = dto.getPassword();
		String uri = "redirect:/home"; // 성공시
		
		  // 2. 서비스 & 결과 처리
	      // => id 확인 
	      // => 존재하면 Password 확인
	      // => 성공: id, name은 session에 보관, home 으로
	      // => 실패: 재로그인 유도
		dto = service.selectOne(dto.getId());
		if (dto !=null && dto.getPassword().equals(password)) {
			// 성공
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName",dto.getName());
			
		}else {
			 //실패
			uri="member/loginForm";
			model.addAttribute("message","~~id 또는 password 오류!! 다시하세요~");
		}
		
		return uri;
	}
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model ) {
		session.invalidate();
		return "redirect:/";
		
	}//logout
	   // ** Member Detail
	   // => 단일 Parameter 의 경우 @RequestParam("...") 활용
	   //    String jCode = request.getParameter("jCode") 과 동일
	   //     단, 해당하는 Parameter 가 없으면 400 오류 발생
	   //    그러므로 detail 요청에도 ?jCode=D 를 추가함. 
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public String detail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
		//1. 요청분석
		// => id: session 에서 get 
		// => detail 또는 수정 Page 출력을 위한 요청인지 jCode 로 구별
		String id = (String)session.getAttribute("loginID");
		String uri = "member/memberDetail"; //detail
		
		//=> update 요청 확인후 uri수정
		if ("U".equals(jCode))
			uri = "member/updateForm";
		
		//2. Service & 결과 처리
		model.addAttribute("apple", service.selectOne(id));
		return uri;
	}	// detail
	
	
	
	
	
	

	//**Join Form
	@RequestMapping(value="/joinForm", method = RequestMethod.GET)
	public void joinForm() {
	}//joinForm
	
	// ** Join
    @RequestMapping(value="/join", method = RequestMethod.POST)
    public String join(Model model, MemberDTO dto) {
    	// 1. 요청분석
    	// => 이전 : 한글처리, request값 -> dto에 set
    	// => 스프링: 한글은 filter, request 처리는 매개변수로 자동화
    	String uri = "member/loginForm"; //성공시 
    	

    	// 2. Service & 결과
    	if (service.insert(dto) > 0) {
    		model.addAttribute("message", "~~회원가입성공 !! 로그인 후 이용하세요~~");
    
    	}else {
    		// 실패 재가입 유도
    		uri = "member/joinForm";
    		model.addAttribute("message", "~~회원가입실패 !! 다시하세요~~");
    	}
    	return uri;
    }//join
  
    	// ** Update
        @RequestMapping(value="/update", method = RequestMethod.POST)
        public String update(HttpSession session, Model model, MemberDTO dto) {
        	// 1. 요청분석
        	// => 성공: memberDetail, 실패 : updateForm
        	// => 두경우 모두 출력하려면 dto 객체의 값("apple") 이 필요하므로 보관
        
        	String uri = "member/memberDetail"; //성공시 
        	model.addAttribute("apple", dto);

        	// 2. Service & 결과
        	if (service.update(dto) > 0) {
        		model.addAttribute("message", "~~회원 정보 수정 성공 !! ");
        		//=> name을 수정할수도 있으므로 loginName을 수정해준다
        		session.setAttribute("loginName", dto.getName());
        	}else {
        		// 실패 재수정 유도
        		uri = "member/updateForm";
        		model.addAttribute("message", "~~회원 정보 수정 실패 !! 다시하세요~~");
        	}//update
        	return uri;
        }
        	// ** Delete
        	@RequestMapping(value="/delete", method = RequestMethod.GET)
        	public String delete(HttpSession session, Model model) {
        		//1. 요청분석
        		// => id: session 에서 get 
        		// => detail 또는 수정 Page 출력을 위한 요청인지 jCode 로 구별
        		String id = (String)session.getAttribute("loginID");
        		String uri = "redirect:/home";
        		
        		//=> update 요청 확인후 uri수정
        	
        		//2. Service & 결과 처리
        if (service.delete(id)>0) {
        	model.addAttribute("message", "~~탈퇴 성공!! 1개월후 재가입 가능합니다");
        	session.invalidate();
        }else {
        	model.addAttribute("message", "~~탈퇴 실패!! 관리자에게 연락바람");
        
        
        
        	}	// detail

	
        	return uri;

//	//1. memberList
//	@RequestMapping(value ="/memberList",method = RequestMethod.GET)
//	public void mList(Model model) {
//	model.addAttribute("banana", service.selectList());		
//	} //mList
//
//	
//	
//		// Member Detail
//	@RequestMapping(value ={"/mdetailsp","medtail"},method = RequestMethod.GET)
//	public String mDetail(Model model) {
//		model.addAttribute("apple", service.selectList());		
//		return "member/memberDetail";
//	} //mdetail
//			
//		
		
		
	}// class
}