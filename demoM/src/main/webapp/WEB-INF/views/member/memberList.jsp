<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** Spring MVC2 MemberList **</title>
	<link rel="stylesheet" type="text/css" 
		  href="/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring_Boot Mybatis MemberList **</h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table border="1" style="width:100%">
<tr bgcolor="#80c0ff" >
	<th>ID</th><th>Name</th><th>Age</th><th>Jno</th>
	<th>Info</th><th>Point</th><th>Birthday</th>
	<th>추천인</th><th>Image</th>
	<!-- <th>Password</th> -->
</tr>
<c:if test="${!empty requestScope.banana}">
	<c:forEach var="m" items="${requestScope.banana}">
		<tr><td>${m.id}</td><td>${m.name}</td><td>${m.age}</td><td>${m.jno}</td>
			<td>${m.info}</td><td>${m.point}</td><td>${m.birthday}</td><td>${m.rid}</td>
<td><img alt="myImage" src="/resources/uploadImages/${m.uploadfile}"
width="50" height="70" ></td>
			<%-- <td>${m.password}</td> --%>
		</tr>	
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana}">
	<tr><td colspan="9">~~ 출력자료가 1건도 없습니다. ~~ </td>
	</tr>
</c:if>
</table>
<hr>
&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>