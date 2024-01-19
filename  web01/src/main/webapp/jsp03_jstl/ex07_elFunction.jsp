<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSTL이 제공하는 주요 EL 함수 **</title>
</head>
<body>
<h2>** JSTL이 제공하는 주요 EL 함수 **</h2>
<pre>
=> JSTL fn: 문자열 함수 제공
=> 그러나 fn은 JSP 태그가 아님, EL 내부에서 사용하도록 지원되는 함수
   Java 의 String 의 메소드를 함수로 제공 

** 실습
=> 문자열 정의
	<c:set var="message" value="~~ 너무너무 재미있는 Jstl 123 !!! ~~~" />
=> Test
* 길이 : ${fn:length(message)}
* 대문자 : ${fn:toUpperCase(message)}
* 소문자 : ${fn:toLowerCase(message)}
* 문자열대체: ${fn:replace(message, '!', '@')}
* split: 
	<c:forEach var="hashTag" items="${fn:split('#coffee#cake#빙수#Juice#아이스크림','#')}">
		${hashTag}
	</c:forEach>
</pre>

</body>
</html>