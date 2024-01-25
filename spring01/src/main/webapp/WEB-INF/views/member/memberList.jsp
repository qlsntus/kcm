<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web02_MVC02 MemberList**</title>
</head>
<body>
<h2>** Web02_MVC02 MemberList**</h2>
<hr>
<c:if test="${!empty requestScope.message}">
= ${requestScope.message}<br>
</c:if>
<table border="1" style="width:100%">
<tr bgcolor="DeepSkyBlue">
   <th>ID</th><th>Password</th><th>Name</th><th>Age></th><th>Jno</th>
   <th>Info</th><th>Point</th><th>Birthday</th><th>추천인</th>
  
</tr> 
<c:if test="${!empty requestScope.banana}">
      <c:choose>
         <c:when test ="${!empty requestScope.banana}">
            <c:forEach var="s" items="${requestScope.banana}">
               <tr>
                  <td>${s.id}</td><td>${s.password}</td><td>${s.name}</td>
                  <td>${s.jno}</td><td>${s.info}</td><td>${s.point}</td><td>${s.birthday}</td><td>추천인</td>
                  <td style = "text-align:left;padding-left:10px;"></td>
                
               </tr>
               </c:forEach>
         </c:when>
         <c:otherwise>
               <tr><td colspan = "9" style = "text-align:center;">
               selectList 결과가 1건도 없음
            </td></tr>
            </c:otherwise>
      </c:choose>
</c:if>
<c:if test="${empty requestScope.banana }">
	<tr><td colspan="9">~~출력자료가 1건도 없습니다.~~</td>
	</tr>

</c:if>
</table>
<hr>
&nbsp;<a href="/web02/home.jsp">Home</a>&nbsp;
&nbsp;<a href="javascript:history.back()">이전으로</a>&nbsp;
</body>
</html>