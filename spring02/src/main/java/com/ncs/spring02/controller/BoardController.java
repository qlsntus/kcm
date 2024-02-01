package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	BoardService service;
	// ** Reply Insert
	@GetMapping("/replyInsert")
	public void replyInsert(BoardDTO dto) {
		//=> 답글처리를 위해 부모글의 root, step, indent를 인자로 전달받으면,
		// 이 인장에 담겨진 이값은 requestScope와 동일
		//=> 그러므로 response 전송 전까지는 서버(Jsp)에서 사용가능
		// 단, 객체명의 첫문자를 소문자로 해서 접근가능 (${boardDTO.~~})
	}
	
	//=> 메서드명과 요청명이 위의 메서드와 동일하지만,
	// Post 요청이고 인자가 다르기떄문에 허용된다. 
	@PostMapping("/replyInsert")
	public String replyInsert(Model model, BoardDTO dto, RedirectAttributes rttr) {
		//** 답글등록
		//=> 성공시: boardList 입력완료 확인
		// =>실패시: replyInsert 재입력유도
		String uri = "redirect:boardList";
		
		//=> dto 값 확인
		// -> id, title, content : 사용가능
		// -> 부모글의 root : 사용가능
		// -> 부모글의 step,indent : 1씩 증가
		// => sql 처리 
		// -> replyInsert, step의 update
		dto.setStep(dto.getStep()+1);
		dto.setIndent(dto.getIndent()+1);
		if(service.rinsert(dto) > 0) {
			rttr.addFlashAttribute("message","~~답글등록 성공~~");
		}else {
			uri = "board/replyInsert";
			model.addAttribute("message","~~답글등록 실패쓰~~");
		}
		
		return uri;
	}
	
	//** Board List
	@GetMapping("/boardList")
	public void boardList(Model model) {
		model.addAttribute("banana", service.selectList());
	}//boardList
	
	
	// ** Board Detail
	// => 글요청 처리중, 글을 읽기 전
	// => 조회수 증가 
	//  -> loginID와 board의 id가 다른 경우
	@GetMapping("/detail")
	public String boardDetail(HttpSession session, Model model, 
			@RequestParam("jCode") String jCode,
			@RequestParam("seq") int seq) {
		String uri = "board/boardDetail";
		//=> 조회수 증가 
		// ->selectOne 의 결과를 보관
		// ->update 요청이 아니고, loginID 와 글쓴이의 id 가 다른경우
		
		BoardDTO dto = service.selectOne(seq);
	
		if("U".equals(jCode)) uri = "board/boardUpdate";
		else if (!dto.getId().equals((String)session.getAttribute("loginID"))) {
		// => 조회수 증가 조건 만족
			dto.setCnt(dto.getCnt()+1);
			service.update(dto);
		}
		
		model.addAttribute("apple", dto);
		
	
		return uri;
		
	}//Board Detail
	
	
	// BoardInsert
	@GetMapping("/boardInsert")
	public void boardInsert() {
		
	}//BoardInsert
	// => Board_Insert 처리
	@PostMapping(value="/insert")
	public String insert(Model model, BoardDTO dto, RedirectAttributes rttr) {
		// ** Insert Service 처리
		// => 성공: joList 로 redirect
		// => 실패: 재입력 유도 -> insert 폼 요청
		String uri="redirect:boardList";
		System.out.println(dto);
		if ( service.insert(dto)>0 ) {
			rttr.addFlashAttribute("message", "~~ Board_Insert 성공 ~~");
		}else {
			model.addAttribute("message", "~~ Board_Insert 실패 , 다시 하세요 ~~");
			uri="board/boardInsert";
		}
		
		// 3) View 처리
		return uri;
	} //insert	
	
	// ** BoardUpdate
		@PostMapping(value="/update")
		public String update(Model model, BoardDTO dto, RedirectAttributes rttr) {
			
			// ** Update Service 처리
			// => 성공: joList 로 redirect
			// => 실패: 재수정 유도 -> joUpdate 폼으로
			// => 그러므로 출력가능하도록 dto 를 setAttribute
			model.addAttribute("apple", dto);
			String uri="redirect:boardList";
			
			if ( service.update(dto)>0 ) {
				// 수정성공 -> JList 로 redirect
				rttr.addFlashAttribute("message", "~~ Board 정보 수정 성공 ~~");
			}else {
				// 수정실패
				model.addAttribute("message", "~~ Board 정보 수정 실패 , 다시 하세요 ~~");
				uri="board/boardUpdate";
			}
			
			// 3) View 처리
			return uri;
		} //BoardUpdate
		
		// ** Delete
		@GetMapping(value="/delete")
		public String delete(BoardDTO dto, RedirectAttributes rttr) {
			
			// ** Delete Service 처리
			// => 성공, 실패 : joList 로 redirect
			String uri="redirect:boardList";
			if ( service.delete(dto)>0 ) {
				rttr.addFlashAttribute("message", "~~ 글 삭제 성공 !!! ~~");
			}else {
				rttr.addFlashAttribute("message", "~~ 글 삭제 실패 !!! ~~");
			}
			// 3) View 처리
			return uri;
		} //delete
	
	
	
	
	
	
	
} // class
