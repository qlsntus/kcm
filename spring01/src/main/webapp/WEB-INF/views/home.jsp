<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h2>** Hello Spring !!! **</h2>  
<P>  The time on the server is ${serverTime}. </P>
<hr>
<c:if test="${!empty sessionScope.loginName}">
	<h3>${sessionScope.loginName}님 안녕하세요~~</h3>
</c:if>
<c:if test="${empty sessionScope.loginName}">
	<h4>로그인 후 이용 하세요 ~~</h4>
</c:if>
<c:if test="${!empty requestScope.message}">
	<hr><h4>${requestScope.message }</h4>

</c:if>
<img alt="" src="resources/images/tulips.png" width="300" height="200">
<hr>
<c:if test="${not empty sessionScope.loginName}">
	&nbsp;<a href="/web02/mdetail">MyInfo</a>&nbsp;
	&nbsp;<a href="/web02/logout">Logout</a><br>
	&nbsp;<a href="/web02/mdetail?jCode=U">내정보수정</a>&nbsp;
	&nbsp;<a href="/web02/mdelete">탈퇴</a><br>
</c:if>
<c:if test="${empty sessionScope.loginName}">
	&nbsp;<a href="/web02/member/loginForm.jsp">Login</a>&nbsp;
	&nbsp;<a href="/web02/member/joinForm.jsp">Join</a><br>
	
</c:if>

&nbsp;<a href="mlist">MList</a>&nbsp;
&nbsp;<a href="mdetail">MDetail</a>&nbsp;
&nbsp;<a href="mlistsp">MListsp</a>&nbsp;
&nbsp;<a href="mdetailsp">MDetailsp</a>&nbsp;





</body>
</html>
