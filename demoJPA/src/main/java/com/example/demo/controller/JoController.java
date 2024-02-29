package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Jo;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
@RequestMapping(value="/jo")
@AllArgsConstructor
// => 모듬 맴버변수를 초기화하는 생성자 자동 추가 & 사용
//    그러므로 아래의 @Autowired는 생략 가능
public class JoController {
	
	//@Autowired
	JoService service;	// = new JoService();
	//@Autowired
	MemberService mservice;
	
	// ** JoList
	@GetMapping("/joList")
	public void joList(Model model) {
		model.addAttribute("banana", service.selectList());
	} //joList
	
	// ** JoDetail
	// => 아랫쪽에 조원목록 출력 (추가기능) -> joDetail.jap 에 Member_List 출력 코드 추가  
	// => jo Table에서 selectOne  ->  apple 
	// => member Table에서 조건검색 jno=#{jno} -> banana 
	@GetMapping("/detail")
	public String detail(HttpServletRequest request, Model model, 
						Jo entity, @RequestParam("jCode") String jCode) {
		
		String uri = "jo/joDetail";
		model.addAttribute("apple", service.selectOne(entity.getJno()));
		// => 수정요청시엔 수정폼으로 
		if ( "U".equals(jCode) )
			uri = "jo/joUpdate";
		
		// ** 조원목록 출력하기 추가 ( detail 출력시에만 )
		// => MemberService 실행
		//	-> findByJno(int jno) 메서드 추가
		//	-> 실행결과는 banana 로 
		if ( "D".equals(jCode) )
	model.addAttribute("banana", mservice.findByJno(entity.getJno()));
		
		return uri;
	} //detail
	
	// ** Jo_Insert
	// => joInsert 출력
	@GetMapping(value="/joInsert")
	public void joInsert() {
		// viewName 생략
	} //joInsert
	
	// => jo_insert 처리
	@PostMapping(value="/insert")
	public String insert(Model model, Jo entity, RedirectAttributes rttr) {
		// ** Insert Service 처리
		// => 성공: joList 로 redirect
		// => 실패: 재입력 유도 -> insert 폼 요청
		String uri="redirect:joList";
		try {
			log.info("** Jo insert 성공 => "+	service.save(entity));
			model.addAttribute("message", "~~ 회원가입 성공 !! 로그인 후 이용하세요 ~~");
			} catch (Exception e) {
				log.info("** member insert Exception => "+e.toString());
				uri = "member/joinForm";
				model.addAttribute("message", "~~ 회원가입 실패 !! 다시 하세요 ~~");
			}
			return uri;
	
		// 3) View 처리
		
	} //insert	
	
	// ** Update
	@PostMapping(value="/update")
	public String update(Model model, Jo entity, RedirectAttributes rttr) {
		
		// ** Update Service 처리
		// => 성공: joList 로 redirect
		// => 실패: 재수정 유도 -> joUpdate 폼으로
		// => 그러므로 출력가능하도록 dto 를 setAttribute
		model.addAttribute("apple", entity);
		String uri="redirect:joList";
		try {
			log.info("** Jo Update 성공 => "+	service.save(entity));
			model.addAttribute("message", "~~ 회원가입 성공 !! 로그인 후 이용하세요 ~~");
			} catch (Exception e) {
				log.info("** Jo Update Exception => "+e.toString());
				uri = "jo/joInsert";
				model.addAttribute("message", "~~ Jo 정보 수정 실패 !! 다시 하세요 ~~");
			}
			return uri;
		// 3) View 처리
		
	} //update
	
	// ** Delete
	@GetMapping(value="/delete")
	public String delete(int jno, RedirectAttributes rttr) {
		
		// ** Delete Service 처리
		// => 성공, 실패 : joList 로 redirect
		String uri="redirect:joList";
		try {
			service.deleteById(jno);
			log.info("** Jo Delete 성공 =>"+jno);
			rttr.addFlashAttribute("message", "~~ 탈퇴 성공 !! 1개월 후 재가입 가능합니다. ~~");
			
		} catch (Exception e) {
			log.info("** Jo Delete Exception =>"+e.toString());
			rttr.addFlashAttribute("message", "~~ 탈퇴 실패 !! 관리자에게 연락 하세요 ~~");
		}
		return uri;
	} //delete

} //class
