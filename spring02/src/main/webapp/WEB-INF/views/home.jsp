<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" 
	href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Hello Spring_MCG02 !!!   </h2>
<P> *Home_time: ${serverTime}. </P>
</body>
<hr>
<c:if test="${!empty sessionScope.loginName}">
  ${sessionScope.loginName}님 안녕하세요~~<br>
</c:if>

<c:if test="${empty sessionScope.loginID}">
	<h4>로그인 후 이용 하세요 ~~</h4> <br>
</c:if>
<c:if test="${!empty requestScope.message}">
	<hr><h4>${requestScope.message }</h4> <br>

</c:if>
<img alt="mainImage" src="/spring02/resources/images/다운로드.png" width="300" height="200">
<hr>
<!-- Login 전-->
<c:if test="${empty sessionScope.loginID}">
&nbsp;<a href="member/loginForm">LoginF</a>&nbsp;
&nbsp;<a href="member/joinForm">JoinF</a>&nbsp;
</c:if>
<!--Login 후  -->
<c:if test="${!empty sessionScope.loginID}">
&nbsp;<a href="member/detail?jCode=D">내정보</a>&nbsp;
&nbsp;<a href="member/detail?jCode=U">내정보수정</a>&nbsp;
&nbsp;<a href="member/logout">Logout</a>&nbsp;
&nbsp;<a href="member/delete">탈퇴</a>&nbsp;

</c:if>
<br><hr>
&nbsp;<a href="member/memberList">MList</a>&nbsp;
&nbsp;<a href="jo/joList">JList</a>&nbsp;
&nbsp;<a href="board/boardList">BList</a>&nbsp;
&nbsp;<a href="jo/joInsert">JoInsert</a>&nbsp;
&nbsp;<a href="bcrypt">BCrypt</a>&nbsp;<br>
&nbsp;<a href="board/bPageList">BPage</a>&nbsp;








</html>
