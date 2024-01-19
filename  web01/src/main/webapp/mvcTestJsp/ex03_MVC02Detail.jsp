<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.List, mvcTest.StudentDTO"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** My Info</title>
</head>
<body>
<table border="1" style="width:100%">
	<tr bgcolor="pink"><th>Sno</th><th>Name</th><th>Age</th>
		<th>Jno</th><th>Info</th><th>Point</th>
	</tr>
	<c:if test="${! empty requestScope.myInfo}">
	<tr>
		<td> ${myInfo.sno} </td>
		<td> ${myInfo.name} </td>
		<td> ${myInfo.age}  </td>
		<td> ${myInfo.jno}  </td>
		<td> ${myInfo.info}	</td>
		<td> ${myInfo.point}</td>
		
	</tr>
	</c:if>
	<c:if test="${empty requestScope.myInfo}">
		<tr><td colspan="7"><h3>출력 데이터 없음</h3></td></tr>
	
	</c:if>
	</table>
	
</body>
</html>
