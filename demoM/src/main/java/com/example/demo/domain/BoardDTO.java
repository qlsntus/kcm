package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	
	private int seq;
	private String id;
	private String title;
	private String content;
	private String regdate;
	private int cnt;
	private int root;
	private int step;
	private int indent;
	
	
	
	
	
} //class
