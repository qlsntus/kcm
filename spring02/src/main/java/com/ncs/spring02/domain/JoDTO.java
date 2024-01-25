package com.ncs.spring02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoDTO {
	// ** private 으로 맴버변수 정의
	private int jno;
	private String jname;
	private String captain;
	private String project;
	private String slogan;
	
	// ** 생성자
	// => default 생성자, 모든값을 초기화하는 생성자
	
	// ** setter/getter
	
	// ** toString
	
} //class
