<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** Spring_Boot Axios MemberList **</title>
	<link rel="stylesheet" type="text/css" 
		  href="/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring_Boot Axios MemberList **</h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table border="1" style="width:100%">
<tr bgcolor="hotpink" >
	<th>ID</th><th>Name</th><th>Age</th><th>Jno</th>
	<th>Info</th><th>Point</th><th>Birthday</th>
	<th>추천인</th><th>Image</th><th>Delete</th>
	
</tr>
<c:if test="${!empty requestScope.banana}">
	<c:forEach var="m" items="${requestScope.banana}">
	<!--** idbList : id별 boardList  
	=> 선택된 id를 function 에 전달 ( 매개변수를 활용)
		idbList('banana')
	-->
<tr><td><span class="textlink" onclick="idbList('${m.id}')">${m.id}</span></td><td>${m.name}</td><td>${m.age}</td><td>${m.jno}</td>
<td>${m.info}</td><td>${m.point}</td><td>${m.birthday}</td><td>${m.rid}</td>
<td><img alt="myImage" src="/resources/uploadImages/${m.uploadfile}"
width="50" height="70" ></td>
<!--** Delete 기능 추가  
	=> 선택된 id를 function 에 전달 ( 매개변수를 활용)
	=> 결과는 성공/실패 여부만 전달: RESTController 로 
	=> 성공 : Deleted로 변경, onclick 해제 
		이를 위해 Delete Tag를 function에서 인식할수있어야함.
	-->
	
<td><span class="textlink" onclick="axiDelete('${m.id}')" id="${m.id}">Delete</span></td>
		</tr>	
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana}">
	<tr><td colspan="10">~~ 출력자료가 1건도 없습니다. ~~ </td>
	</tr>
</c:if>
</table>
<hr>

</body>
</html>