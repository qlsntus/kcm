package com.ncs.spring02.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
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
}
}//bcrypt
