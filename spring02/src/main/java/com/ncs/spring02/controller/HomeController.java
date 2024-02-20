package com.ncs.spring02.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//-----------------------------------------------------------------
//** Locale : (사건등의 현장), 다국어 지원 설정을 지원하는 클래스
//=> locale 값을 받아서 현재 설정된 언어를 알려줌 -> 한글 메시지 출력 가능
//=> jsp 의 언어설정을 받아 해당 언어에 맞게 자동으로 message가 출력 되도록 할때 사용.
//  logger.info("Welcome home! 로그 메시지 Test -> locale is {}.", locale);
//    -> locale: ko_KR
//=> {} : 일종의 출력 포맷 으로 우측 ',' 뒷편 변수의 값이 표시됨.

//-----------------------------------------------------------------

//** Logger : 현재 위치상태를 보여줘서 에러 위치를 잡을수 있게 해 줄 수 있는 코드
//=> Log4J의 핵심 구성 요소로 6개의 로그 레벨을 가지고 있으며,
//  출력하고자 하는 로그 메시지를 Appender (log4j.xml 참고) 에게 전달한다.

//=> 활용 하려면 pom.xml 에 dependency (log4j, slf4j) 추가 (되어있음),
//=> Controller에는 아래의 코드를 넣어주고,
//=> 확인이 필요한 위치에서 원하는 메세지 출력, Sysout 은 (I/O 발생으로) 성능 저하 유발
//  현재 클래스 내에서만 사용가능하도록 logger 인스턴스 선언 & 생성

//** Log4J : Log for Java(Apache Open Source Log Library)의 준말
//=> 자바기반 로깅 유틸리티로 디버깅용 도구로 주로 사용됨.
// 로그를 남기는 가장 쉬운 방법은 System.out.println("로그 메세지")이지만
// 프로그램 개발 완료 후 불필요한 구문은 삭제해야 하며 성능 저하 요인이 됨.
// Log4J 라이브러리는 멀티스레드 환경에서도 성능에 전혀 영향을 미치지 않으면서
// 안전하게 로그를 기록할 수 있는 환경을 제공함.

//=> 로깅레벨 단계
// TRACE > DEBUG > INFO > WARN > ERROR > FATAL(치명적인)
// TRACE: Debug보다 좀더 상세한 정보를 나타냄
// DEBUG: 애플리케이션의 내부 실행 상황을 추적하기 위한 상세 정보 출력
//    ( Mybatis 의 SQL Log 확인 가능 )
// INFO : 상태변경과 같은 주요 실행 정보 메시지를 나타냄
// WARN : 잠재적인 위험(에러)를 안고 있는 상태일 때 출력 (경고성 메시지)
// ERROR: 오류가 발생했지만, 애플리케이션은 계속 실행할 수 있을 때 출력
// FATAL: 애플리케이션을 중지해야 할 심각한 에러가 발생 했을 때 출력

//=> 실제는 DEBUG, WARN 이 주로 이용됨.
//-------------------------------------------------

//** 로깅레벨 조정 Test (log4j.xml 의)
//=> root Tag 에서 출력 level 조정 (system 오류 level조정) 
//     <root> <priority value 값 >
//=> <logger name="com.ncs.green"> 에서 출력 level 조정
//   <level value="DEBUG" />
//=> 이 두곳의 값을 warn (default) , error, debug, trace

//=> DEBUG Level 에서 Mybatis SQL구문 오류 메시지
// 아래와 같이 SQL 구문에 전달된 값을 정확하게 확인 할 수 있다. 
// DEBUG: mapperInterface.MemberMapper.insert - 
//          ==>  Preparing: insert into member values(?,?,?, ?,?,?,?,?,?) 
// DEBUG: mapperInterface.MemberMapper.insert - 
//          ==> Parameters: apple(String), 12345!(String), 가나다(String), 
//          열심히 하겠습니다!!!(String), 2022-11-17(String), 9(Integer), 22(Integer),
//          1000.55(Double), resources/uploadImage/adv.gif(String)
//----------------------------------------------------------------

//** Lombok 의 @Log4j Test

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
//		// ** Logger Message Test
//		// 1) {} 활용
//		String name="홍길동"; int age=100;
//		logger.info("test1) 안녕하세요~~");
//		logger.info("test2) 안녕하세요, {}님, age={}~~",name,age);
//		
//		 // 2) 로깅레벨 조정 Test
//	     //=> 로깅레벨 단계
//	     //   TRACE > DEBUG > INFO > WARN > ERROR > FATAL(치명적인)
//		logger.error("* 로깅레벨 Test Error: name={},age={}", name, age);
//		logger.warn("* 로깅레벨 Test Error: name={},age={}", name, age);
//		logger.info("* 로깅레벨 Test Error: name={},age={}", name, age);
//		logger.debug("* 로깅레벨 Test Error: name={},age={}", name, age);
//		logger.trace("* 로깅레벨 Test Error: name={},age={}", name, age);
//		
		
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	} //home
	
	


@GetMapping("/bcrypt")
public String bcrypt() {
   
   PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
   String password = "12345!";
   
   // 1) encode
   // => 동일한 원본(rawData) 에 대해 각기 다른 결과(digest)를 생성
   String digest1 = passwordEncoder.encode(password);
   String digest2 = passwordEncoder.encode(password);
   String digest3 = passwordEncoder.encode(password);
   String digest4 = passwordEncoder.encode("6789@");
   String digest5 = passwordEncoder.encode("abcd#");
   
   System.out.println("** digest1 => "+digest1);
   System.out.println("** digest2 => "+digest2);
   System.out.println("** digest3 => "+digest3);
   System.out.println("** digest4 => "+digest4);
   System.out.println("** digest5 => "+digest5);
   
   // 2) matches(rawData, digest) 
   System.out.println("** digest1 matches => "+ passwordEncoder.matches(password, digest1)); 
   System.out.println("** digest2 matches => "+ passwordEncoder.matches(password, digest2)); 
   System.out.println("** digest3 matches => "+ passwordEncoder.matches(password, digest3)); 
   System.out.println("** digest4 matches => "+ passwordEncoder.matches(password, digest4)); 
   System.out.println("** digest5 matches => "+ passwordEncoder.matches(password, digest5)); 
   
   return "redirect:home";
} //bcrypt

// ** Sring_Exception Test
// * Web의 Exception 처리  
// => WebPage 별, ExceptionType 별, 응답상태코드 별 
// => web.xml
//   : Exception Type 은 전달되어 해당하는 jsp는 실행되지만, 
//   : page 디렉티브의 isErrorPage="true" 와 무관하게 ~.jsp 에 ${exception.message} 전달안됨. 

// * Spring exceptionResolver 적용  
// => servlet~~~.xml 에 설정
// => page 디렉티브의 isErrorPage="true" 와 무관하게 ~.jsp 에 ${exception.message} 전달됨. 
//    그러나 Spring exceptionResolver 가 오류 메시지를 받아 처리하므로 
//    console 에는 Exception Message가 출력(전달) 되지않는다.
//    ( DEBUG level Message 는 출력됨.)
	@GetMapping("/etest")
	public String etest(HttpServletRequest request) {
	      // 1) ArithmeticException 
	      // => ArithmeticException -> 500 
	      int i=100/10;
	      logger.info("** ArithmeticException 정수 => "+i);
	      
	      // => 실수형연산: by Zero Exception 발생하지않음
	      //    
	      double d = 100.0/0.0; // Infinity (무한수)
	      double p = 100.0%0.0; // NaN (Not a Number)
	      
	      if (Double.isInfinite(d)) d=1;
	      if (Double.isNaN(p)) p=0;
	      logger.info("** ArithmeticException 실수d => "+(d*100));
	      logger.info("** ArithmeticException 실수p => "+(p+100));
	      
	      // 2) NumberFormatException (Java, web.xml) or IllegalArgumentException (Spring)
	      String s="12345"; // "12345a" 와 비교
	      i+= Integer.parseInt(s);
	      logger.info("** IllegalArgumentException => "+i);
	      
	      // 3) NullPointerException
	      // => getParameter() : parameter 가 없으면 null return
	      s=request.getParameter("name");
	      
	      //if (s.equals("홍길동")) s="Yes";
	      // => NullPointerException 예방위해
	      if ("홍길동".equals(s)) s="Yes";
	      else s="No";
	      logger.info("** NullPointerException => "+s);
	      
	      // 4) ArrayIndexOutOfBoundsException
	      // => <props> 에 정의안됨 : defaultErrorView Test
	      String[] menu = {"오징어떡볶기","카레라이스","떡갈비"};
	      logger.info("** ArrayIndexOutOfBoundsException 500 => "+menu[2]);
	      
	      // 5) SQL Test : Transaction 또는 join 으로 
		
		 return "redirect:home";
	}
	
	   // ** KaKaoMap Test ***************************
	   @GetMapping("/greensn")
	   public String greensn() {
	      return "kakaoMapJsp/map01_greenSN";
	   } //greensn   
	   
	   @GetMapping("/greenall")
	   public String greenall() {
	      return "kakaoMapJsp/map02_greenAll";
	   } //greenall   
	   
	   @GetMapping("/jeju")
	   public String jeju() {
	      return "kakaoMapJsp/map03_jeju";
	   } //jeju   
	   
	   @GetMapping("/gps")
	   public String gps() {
	      return "kakaoMapJsp/map04_GPS";
	   } //gps   




}//class

