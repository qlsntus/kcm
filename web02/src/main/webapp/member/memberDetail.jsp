<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** memberDetail **</title>
</head>
<body>
<table border="1" style="width:100%">
	<tr bgcolor="pink"><th>ID</th><th>Password</th><th>Name</th><th>Jno</th>
		<th>Info</th><th>Point</th><th>Birthday</th><th>추천인</th>
	</tr>
	<c:if test="${! empty requestScope.apple}">
	<tr>
		<td> ${apple.id} </td>
		<td> ${apple.password} </td>
		<td> ${apple.name}  </td>
		<td> ${apple.jno}  </td>
		<td> ${apple.info}	</td>
		<td> ${apple.point}</td>
		<td> ${apple.birthday}</td>
		<td> ${apple.rid}</td>
		
	</tr>
	</c:if>
	<c:if test="${empty requestScope.apple}">
		<tr><td colspan="7"><h3>출력 데이터 없음</h3></td></tr>

	
	</c:if>
	</table>
		<hr>
&nbsp;<a href="/web02/home.jsp">Home</a>&nbsp;
&nbsp;<a href="javascript:history.back()">이전으로</a>&nbsp;