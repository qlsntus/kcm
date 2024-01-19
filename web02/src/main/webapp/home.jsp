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
<h2>** Web02_MVC02 **</h2>
<c:if test="${!empty sessionScope.loginName}">
	<h3>${sessionScope.loginName}님 안녕하세요~~</h3>
</c:if>
<c:if test="${empty sessionScope.loginName}">
	<h3>로그인 후 이용 하세요 ~~</h3>
</c:if>
<hr>
<img alt="" src="/web02/images/letsgo.png" width="300" height="200">
<hr>
<c:if test="${not empty sessionScope.loginName}">
	&nbsp;<a href="/web02/mdetail">MyInfo</a>&nbsp;
	&nbsp;<a href="/web02/logout">Logout</a><br>
	&nbsp;<a href="/web02/mdetail?jCode=U">내정보수정</a>&nbsp;
	&nbsp;<a href="/web02/mdelete">탈퇴</a><br>
	
</c:if>
<c:if test="${empty sessionScope.loginName}">
	&nbsp;<a href="member/loginForm.jsp">Login</a>&nbsp;
	&nbsp;<a href="member/joinForm.jsp">Join</a><br>
</c:if>

&nbsp;<a href="/web02/mlist">MList</a>&nbsp;

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