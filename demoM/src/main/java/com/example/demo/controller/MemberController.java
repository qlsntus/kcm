package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.MemberDTO;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageTest.PageMaker;
import pageTest.SearchCriteria;


@Log4j2 //@Log4j -> Boot 에서는 2015년 이후 지원중단
@AllArgsConstructor // 개별적인 @Autowired 생략가능
@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired(required = false)
	MemberService service;
	@Autowired
	PasswordEncoder passwordEncoder; 
	// ** Axios MemberList
	@GetMapping("/aximlist")
	public String axiMemberList(Model model) {
		model.addAttribute("banana", service.selectList());
		log.info("**axMemberList 성공 **");
		return "axTest/axMemberList";
	}

	
	@Autowired(required = false)
	JoService jservice;
	
	//** Lombok @Log4j Test
	@GetMapping("/log4jTest")
	public String lof4jTest() {
		String name="banana";
		log.error("**Lombok @Log4j Test Error: name="+ name);
		log.warn("**Lombok @Log4j Test Error: name="+ name);
		log.info("**Lombok @Log4j Test Error: name="+ name);
		log.debug("**Lombok @Log4j Test Error: name="+ name);
		log.trace("**Lombok @Log4j Test Error: name="+ name);
		return "redirect:/";
	}
	
	
	
	
	
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
	
	
	
	//** Member_Paging
	// => ver01 : Criteria 사용
	//public void bPageList(Model model, Criteria cri, PageMaker pageMaker )
	// => ver02 : SearchCriteria 사용 (검색기능 추가)
	//** Member Check_List
	@GetMapping("/mCheckList")
	public String mCheckList(HttpServletRequest request,Model model, SearchCriteria cri, 
			PageMaker pageMaker ){
		
		String uri="member/mPageList";
		//=> 요청명을 uri에 포함하기 위함
		String mappingName=
	request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		System.out.println("=>ReqeustURI: "+request.getRequestURI());
		//=> RequestURI: /spring02/member/mPageList
		System.out.println("=>mappingName:"+mappingName);
		
		
		
		
		//1) Criteria 처리
		cri.setSnoEno();
		
		//2) Service
		   // => check 의 값을 선택하지 않은경우 check 값을 null 로 확실하게 해줘야함.
	      //    mapper 에서 명확하게 구분할수 있도록해야 정확한 저리가능 
		if (cri.getCheck() !=null && cri.getCheck().length<1)
			cri.setCheck(null);
		model.addAttribute("banana", service.mCheckList(cri));
		
		//3) View처리 : PageMaker 이용
		// => cri, totalRowsCount ( Read from DB)
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.mCheckRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		return uri;
		
		
		
	} //mCheckList
	
	//** Member_Paging
	// => ver01 : Criteria 사용
	//public void bPageList(Model model, Criteria cri, PageMaker pageMaker )
	// => ver02 : SearchCriteria 사용 (검색기능 추가)
	@GetMapping("/mPageList")
	public void mPageList(HttpServletRequest request,Model model, 
			           SearchCriteria cri, PageMaker pageMaker ) {
		//1) Criteria 처리
		// => ver01: currPage, rowsPerPage 값들은 Parameter 로 전달되어 자동으로 cri에 set
		//=> ver02: ver01 + searchType, keyword 도 동일하게 cri에 set
		cri.setSnoEno();
		
		//2) Service
		model.addAttribute("banana", service.mPageList(cri));
		
		//3) View처리 : PageMaker 이용
		// => cri, totalRowsCount ( Read from DB)
		
		String mappingName=
		request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.mtotalRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		
		
	} //mPageList
	
	

	@RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public void loginForm() {
	} //loginForm
	
	@PostMapping("/login")
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
		
		
		// => PasswordEncoder 적용
		//if ( dto !=null && dto.getPassword().equals(password)) {
			if ( dto !=null && 
				passwordEncoder.matches(password, dto.getPassword())) {
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
	public void joinForm(Model model) {
	} //joinForm

	// ** Join
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(HttpServletRequest request, 
			Model model, MemberDTO dto) throws IOException{
		// 1. 요청분석
		
		String uri = "member/loginForm"; //성공시
		
	      // *** Upload File 처리 **************************
	     
	      // 1) 물리적 실제저장위치 확인
	      // 1.1) 현재 웹어플리케이션의 실행위치 확인
	      //   => 이클립스 개발환경 (배포전) : ~~-tomcat-~~ 포함되어있지 않음
	      //   => 톰캣 서버 배포후 :  ~~-tomcat-~~ 포함
		  //
	      String realPath = request.getRealPath("/");
	      System.out.println("** realPath => "+realPath);
	      //=> Spring Boot_realPath => E:\\mtest\\gitwork\\kcm\\demoM\\src\\main\\webapp\\
	      
	      // 1.2) realPath 를 이용해서 물리적저장위치 (file1) 확인
	      if ( !realPath.contains("-tomcat-") ) // 개발중
	          realPath +="resources\\uploadImages\\";
	      else realPath ="E:\\mtest\\IDEset\\apache-tomcat-9.0.85-windows-x64\\webapps\\deomM\\resources\\uploadImages\\";
	      
	      // 1.3) 폴더 만들기 (없을수도 있음을 가정, File 실습)
	      // => File type 객체 생성 : new File("경로");
	      // => file.exists()
	      //   -> 파일 또는 폴더가 존재하는지 리턴
	      //   -> 폴더가 아닌, 파일존재 확인하려면 file.isDirectory() 도 함께 체크해야함. 
	      //     ( 참고: https://codechacha.com/ko/java-check-if-file-exists/ )
	      // => file.isDirectory() : 폴더이면 true (exists()는 true 이면서 false면 file이 존재 한다는 의미가 됨.) 
	      // => file.isFile()
	      //   -> 파일이 존재하는 경우 true 리턴,
	      //   -> file의 Path 가 폴더인 경우는 false 리턴
	      File file = new File(realPath);
	      if ( !file.exists() ) {
	         // => 저장폴더가 존재하지 않는경우 만들어줌
	         file.mkdir();
	      }
	      
	      // --------------------------------------------
	      // ** File Copy 하기 (IO Stream 실습)
	      // => 기본이미지(basicman1.png) 가 uploadImages 폴더에 없는경우 기본폴더(images) 에서 가져오기
	      // => IO 발생: Checked Exception 처리
	      file = new File(realPath+"basicman1.jpg"); // uploadImages 폴더에 화일존재 확인을 위함
	      //file = new File("E:\\mtest\\IDEset\\apache-tomcat-9.0.85-windows-x64\\webapps\\spring02\\"+realPath+"basicman1.jpg");
	      if ( !file.isFile() ) { // 존재하지않는 경우
	         String basicImagePath 
	               = "E:\\mtest\\gitwork\\kcm\\demoM\\src\\main\\webapp\\resources\\images\\basicman1.jpg";
	         FileInputStream fi = new FileInputStream(new File(basicImagePath));
	         // => basicImage 읽어 파일 입력바이트스트림 생성
	         FileOutputStream fo = new FileOutputStream(file); 
	         // => 목적지 파일(realPath+"basicman1.jpg") 출력바이트스트림 생성  
	         FileCopyUtils.copy(fi, fo);
	      }
	      // --------------------------------------------
	      // ** MultipartFile
	      // => 업로드한 파일에 대한 모든 정보를 가지고 있으며 이의 처리를 위한 메서드를 제공한다.
	      //    -> String getOriginalFilename(), 
	      //    -> void transferTo(File destFile),
	      //    -> boolean isEmpty()
	      
	      // 1.4) 저장경로 완성
	      // => 기본 이미지 저장
	      String file1="", file2="basicman1.jpg";
	      
	      MultipartFile uploadfilef = dto.getUploadfilef();
	      if ( uploadfilef!=null && !uploadfilef.isEmpty() ) {
	         // => image_File 을 선택함  
	         // 1.4.1) 물리적위치 저장 (file1)
	         file1=realPath+uploadfilef.getOriginalFilename(); //저장경로(relaPath+화일명) 완성
	         uploadfilef.transferTo(new File(file1)); //해당경로에 저장(붙여넣기)
	         
	         // 1.4.2) Table 저장경로 완성 (file2)
	         file2 = uploadfilef.getOriginalFilename();
	      }
	      // -------------------------------------------
		dto.setUploadfile(file2);
		// 2. Service & 결과
		// => PasswordEncoder 적용
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
      // ** *****************************************
     
		
		//** Service 처리
		if ( service.insert(dto) > 0 ) {
			model.addAttribute("message", "~~ 회원가입 성공 !! 로그인 후 이용하세요 ~~");
		}else { 
			// 실패 : 재가입 유도
			uri = "member/joinForm";
			model.addAttribute("message", "~~ 회원가입 실패 !! 다시 하세요 ~~");
		}
		return uri;
	} //join
	
	// ** Password 수정 (PasswordEncorder 추가후)
	@GetMapping("/pwUpdate")
	public void pwUpdate() {
		// view_name 생략
	}
	
	// ** PasswordUpdate
	// => Service, DAO 에 pwUpdate(dto) 메서드 추가
	// => 성공: session 무효화, 로그인창으로 
	// => 실패: pwUpdate, 재수정유도
	@PostMapping("/pwUpdate")
	public String pwUpdate(HttpSession session, MemberDTO dto, Model model) {
		//1) 요청분석
		// => id: session 에서 
		// => password : 암호화
		dto.setId((String)session.getAttribute("loginID"));
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		String uri="member/loginForm"; //성공시
		//2) Service
		if (service.pwUpdate(dto)>0) {
			//=> 성공
			session.invalidate();
			model.addAttribute("message","Password수정 성공!, 재로그인 하세요~~");
		}else {
			//=> 실패
			model.addAttribute("message","Password수정 실패!, 다시 하세요~~");
			uri="member/pwUpdate";
			
			
		}
		
		return uri;
	} //pwUpdate
	
	// ** Update
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpSession session, Model model, MemberDTO dto
			) throws IllegalStateException, IOException {
		// 1. 요청분석
		// => 성공: memberDetail, 실패: updateForm
		// => 두경우 모두 출력하려면 dto 객체의 값("apple") 이 필요하므로 보관
		
		String uri = "member/memberDetail"; //성공시
		model.addAttribute("apple", dto);
		
		// ** uploadFile 처리
		// => newImage 선택 여부
		// => 선택 -> oldImage 삭제, new Image저장 : uploadfilef 사용
		// => 선택하지않음 -> oldImage가 uploadfile로 전달되었으므로 그냥 사용하면됨.
		
		  MultipartFile uploadfilef = dto.getUploadfilef();
	      if ( uploadfilef!=null && !uploadfilef.isEmpty() ) {
	         // => newImage 을 선택함  
	         // 1.4.1) 물리적위치 저장 (file1)
	    	     String realPath = request.getRealPath("/");
	    	     String file1;
	    		    
	    	     
	   	      // 1.2) realPath 를 이용해서 물리적저장위치 (file1) 확인
	   	      if ( !realPath.contains("-tomcat-") ) // 개발중
	   	          realPath +="resources\\uploadImages\\";
	   	      else realPath ="E:\\mtest\\IDEset\\apache-tomcat-9.0.85-windows-x64\\webapps\\deomM\\resources\\uploadImages\\";      
				/*
				 * // 1.2) realPath 를 이용해서 물리적저장위치 (file1) 확인 if (
				 * !realPath.contains(".eclipse.") ) // 개발중 realPath
				 * ="E:\\mtest\\gitwork\\kcm\\demoM\\src\\main\\webapp\\resources\\uploadImages\\";
				 * else realPath
				 * ="E:\\mtest\\IDEset\\apache-tomcat-9.0.85-windows-x64\\webapps\\demoM\\resources\\uploadImages\\";
				 */
	   	      
	   	      //3) oldFile 삭제 
	   	      //=> oldFile Name: dto.getUploadfile();
	   	      //=> 삭제경로 : realPath+dto.getUploadfile();
	   	      File delFile = new File(realPath+dto.getUploadfile());
	   	      if (delFile.isFile()) delFile.delete(); // file 존재시 삭제 
	   	      
	   	      //4) newFile 저장 
	   	      
	         file1=realPath+uploadfilef.getOriginalFilename(); //저장경로(relaPath+화일명) 완성
	         uploadfilef.transferTo(new File(file1)); //해당경로에 저장(붙여넣기)
	         
	         // 5) Table 저장경로 완성 (file2)
	         dto.setUploadfile(uploadfilef.getOriginalFilename());
	      }
	      // --------------------------------------------
	
	   
		
		
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
			rttr.addFlashAttribute("message", "~~ 탈퇴 성공 !! 1개월 후 재가입 가능합니다. ~~");
			session.invalidate();
		}else {
			model.addAttribute("message", "~~ 탈퇴 실패 !! 관리자에게 연락 하세요 ~~");
			rttr.addFlashAttribute("message", "~~ 탈퇴 실패 !! 관리자에게 연락 하세요 ~~");
		}
		return uri;
	} //delete
} //class

