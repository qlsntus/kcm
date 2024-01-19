<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** View Cookies **</title>
</head>
<body>
<h2>** View Cookies **</h2>
<pre>
=> 웹 브라우져는 request의 header에 쿠키의 값을 담아보냄
=> request 객체에 담겨진 쿠키목록 확인
=> request.getCookies() : 배열타입이며 없으면 null
<hr><b>
=> Cookie List
<% 
	Cookie[] ck = request.getCookies();
	if (ck!=null && ck.length>0) {
		for (Cookie c:ck) {
			out.print("<br> * Name:"+c.getName());
			out.print(", Value:"+c.getValue());
} //for

}else {
	out.print("<br>** Cookie NotFound **");
}
%>
</b><hr>
=> <a href="ex02_makeCookies.jsp">MakeCookies</a>
=> <a href="ex03_upDelCookies.jsp">UpDelCookies</a>
</pre>



</body>
</html>