package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.JoDTO;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;

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
						JoDTO dto, @RequestParam("jCode") String jCode) {
		
		String uri = "jo/joDetail";
		model.addAttribute("apple", service.selectOne(dto));
		// => 수정요청시엔 수정폼으로 
		if ( "U".equals(jCode) )
			uri = "jo/joUpdate";
		
		// ** 조원목록 출력하기 추가 ( detail 출력시에만 )
		// => MemberService 실행
		//	-> selectJoList 메서드 추가 : service, DAO 
		//	-> 실행결과는 banana 로 
		if ( "D".equals(jCode) )
			model.addAttribute("banana", mservice.selectJoList(dto.getJno()));
		
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
	public String insert(Model model, JoDTO dto, RedirectAttributes rttr) {
		// ** Insert Service 처리
		// => 성공: joList 로 redirect
		// => 실패: 재입력 유도 -> insert 폼 요청
		String uri="redirect:joList";
		
		if ( service.insert(dto)>0 ) {
			rttr.addFlashAttribute("message", "~~ Jo_Insert 성공 ~~");
		}else {
			model.addAttribute("message", "~~ Jo_Insert 실패 , 다시 하세요 ~~");
			uri="jo/joInsert";
		}
		
		// 3) View 처리
		return uri;
	} //insert	
	
	// ** Update
	@PostMapping(value="/update")
	public String update(Model model, JoDTO dto, RedirectAttributes rttr) {
		
		// ** Update Service 처리
		// => 성공: joList 로 redirect
		// => 실패: 재수정 유도 -> joUpdate 폼으로
		// => 그러므로 출력가능하도록 dto 를 setAttribute
		model.addAttribute("apple", dto);
		String uri="redirect:joList";
		
		if ( service.update(dto)>0 ) {
			// 수정성공 -> JList 로 redirect
			rttr.addFlashAttribute("message", "~~ Jo 정보 수정 성공 ~~");
		}else {
			// 수정실패
			model.addAttribute("message", "~~ Jo 정보 수정 실패 , 다시 하세요 ~~");
			uri="jo/joUpdate";
		}
		
		// 3) View 처리
		return uri;
	} //update
	
	// ** Delete
	@GetMapping(value="/delete")
	public String delete(JoDTO dto, RedirectAttributes rttr) {
		
		// ** Delete Service 처리
		// => 성공, 실패 : joList 로 redirect
		String uri="redirect:joList";
		if ( service.delete(dto)>0 ) {
			rttr.addFlashAttribute("message", "~~ Jo 삭제 성공 !!! ~~");
		}else {
			rttr.addFlashAttribute("message", "~~ Jo 삭제 실패 !!! ~~");
		}
		// 3) View 처리
		return uri;
	} //delete

} //class
