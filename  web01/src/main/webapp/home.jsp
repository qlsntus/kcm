<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** home **</title>
</head>
<body>
<h2>** Dynamic Web Project **</h2>
<%-- <%	if (session.getAttribute("loginName")!=null) { %>
		<h3><%=session.getAttribute("loginName")%>님 안녕하세요~~</h3>
	<%
	}else { %>
		<h3>로그인 후 이용 하세요 ~~</h3>
<%	} %> 
=> 아래 JSTL 과 비교
--%>
<c:if test="${!empty sessionScope.loginName}">
	<h3>${sessionScope.loginName}님 안녕하세요~~</h3>
</c:if>
<c:if test="${empty sessionScope.loginName}">
	<h3>로그인 후 이용 하세요 ~~</h3>
</c:if>
<hr>
<form action="getpost" method="get">
	<input type="text" name="id" value="banana" size="7">
	<input type="text" name="name" value="홍길동" size="7">
	<input type="text" name="password" size="7">
	<input type="submit" value="Test" >
</form>
<hr>
<!-- ** 경로표기 
 	=> 절대경로: / 로시작, 프로젝트명부터 전체경로 표기
 		->  /web01/images/letsgo.png
 		->  webapp 폴더는 생략됨  
 	=> 상대경로: 현재위치에서 시작, / 로시작하면 안됨
 		-> ./ : 현재위치, ../1단계 상위	
 		-> 	"./images/letsgo.png", "images/letsgo.png" 동일
 -->
 
<img alt="" src="/web01/images/letsgo.png" width="300" height="200">
<hr>
<c:if test="${not empty sessionScope.loginName}">
	&nbsp;<a href="/web01/MVCDetail">MyInfo</a>&nbsp;
	&nbsp;<a href="/web01/logout">Logout</a><br>
</c:if>
<c:if test="${empty sessionScope.loginName}">
	&nbsp;<a href="/web01/servletTestForm/flowEx04_LoginForm.jsp">Login</a>&nbsp;
	&nbsp;<a href="#">Join</a><br>
</c:if>

&nbsp;<a href="/web01/hello">HelloS</a>&nbsp;
&nbsp;<a href="/web01/list">M01ListS</a>&nbsp;
&nbsp;<a href="/web01/life">LifeCycle</a><br>
&nbsp;<a href="/web01/servletTestForm/form01_Adder.html">Adder</a>&nbsp;
&nbsp;<a href="/web01/servletTestForm/form02_Radio.jsp">Radio</a>&nbsp;
&nbsp;<a href="/web01/servletTestForm/form03_Check.jsp">Check</a>&nbsp;
&nbsp;<a href="/web01/servletTestForm/form04_Select.jsp">Select</a><br>
&nbsp;<a href="/web01/flow01">Flow01</a>&nbsp;
&nbsp;<a href="/web01/sessioni">SessionI</a><br>
&nbsp;<a href="/web01/jsp01/ex01_HelloJsp.jsp">HelloJ</a>&nbsp;
&nbsp;<a href="/web01/jsp01/ex02_mvc01List.jsp">M01ListJ</a>&nbsp;
&nbsp;<a href="/web01/list2">M02List</a>&nbsp;

<!--  
MVC2방식
home.jsp: 메뉴 MyInfo를 클릭

-> Controllet서블릿: 해당 Service 처리, 결과를 requestScopr에 담기, Forward
mvcTest/Ex02_MVC02Detail.java

-> View ~jsp: requestScope 의 결과를 확인 & 출력
	mvcTestJsp/ex03_MVC02Detail.jsp



-->
</body>
</html>