package com.example.demo.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
//@Table(name = "guestbook") : 클래스명과 동일한 경우 생략가능
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Guestbook extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gno; // Auto_incerment
	
	@Column(length=100, nullable=false) //notnull
	private String title;
	
	@Column(length=2000, nullable=false)
	private String content;
	
	@Column(length=50, nullable=false)
	private String writer;
	
	public void changeTitle(String title) {
		this.title=title;
	}	
	public void changeContent(String content) {
			this.content=content;
	}
	

}
