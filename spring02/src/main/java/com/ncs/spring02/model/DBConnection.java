package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	// ** DB 연결
	//=> Connection 객체가 DB 연결및 연결정보를 관리함
	//   즉, Connection 객체를 생성해야함

	//** Connection 객체 생성
	// => 일반적인 생성문 
//	    Connection cn = new Connection_구현클래스() -> XXX
	// => DB 연결정보를이용해서 생성후 그 생성값을 전달받음   

	//** Connection 생성과정
	// => Class.forName : JDBC 드라이버 로딩
	// => DriverManager
//	     getConnection() 메서드로 해당 JDBC 드라이버를 찾아 필요한 기본값으로 컨넥션을 생성해서 제공

	// ** JDBC 드라이버
	// => DBMS와 통신을 담당하는 자바 클래스
    //	  DBMS 별로 알맞은 JDBC 드라이버 필요
    //	  보통 jar 파일로 제공

	// ** DriverManager
	// => JDK(Java SE Development Kit)의 정적 클래스이며,
    //	  사용할 애플리케이션에 대해 사용가능한 JDBC(Java Database Connectivity) 드라이버 세트를 관리함.

	public static Connection getConnection() {
		   // ** Error Message
		   // => 드라이버 오류 : java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver1
		   // => portNO 오류 : Communications link failure
		   // => DBName 오류 : java.sql.SQLSyntaxErrorException: Unknown database 'mydb1'
		   // => 계정,PW 오류 : java.sql.SQLException: Access denied for user 'root1'@'localhost' (using password: YES)
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
		     // => allowPublicKeyRetrieval=true : local DB open 하지 않아도 connection 허용
	         // => localhost -> 동일값(ip주소) @127.0.0.1
			Connection cn = DriverManager.getConnection(url,"root","mysql");
			System.out.println("**DB Conection 성공**");
			return cn;
			
		} catch (Exception e) {
			System.out.println("**DB Conection Exception = >" +e.toString());
			return null;
		}//try
		
	}//getConnection
}

