<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Make Cookie **</title>
</head>
<body>
<h2>** Make Cookies **</h2>
<pre>
=> Cookie 객체 생성 -> 생성자의 매개변수로 name, value 를 지정
=> response 에 담기
<%
	Cookie ck1 = new Cookie("MyCookie1","GreenComputer");
	response.addCookie(ck1);
	Cookie ck2 = new Cookie("MyCookie2","그린컴퓨터");
	// => value 에 space 포함되면("그린 컴퓨터") 예외발생 
	// => IllegalArgumentException: 쿠키 값에 유효하지 않은 문자 [18]이(가) ...
	response.addCookie(ck2);


%>
<hr>
=> <a href="ex01_viewCookies.jsp">ViewCookies</a>
=> <a href="ex03_upDelCookies.jsp">UpDelCookies</a> 
</pre>

</body>
</html>