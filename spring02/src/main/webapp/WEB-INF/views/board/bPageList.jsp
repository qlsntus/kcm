<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** Spring MVC2 BoardPageList **</title>
	<link rel="stylesheet" type="text/css" 
		  href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring MVC2 BoardPageList **</h2>
<hr>
<c:if test="${!empty requestScope.message}">
 ${requestScope.message}<br><hr>
</c:if>
<table style="width:100%">
<tr bgcolor="Khaki" >
	<th>Seq</th><th>Title</th><th>ID</th><th>RegDate</th><th>조회수</th>
</tr>
<c:if test="${!empty requestScope.banana}">
	<c:forEach var="b" items="${requestScope.banana}">
		<tr><td>${b.seq}</td>
			<td>
			<!-- 답글 등록 후 Title 출력전에 들여쓰기 추가 -->
			<c:if test="${b.indent>0}">
				<c:forEach begin="1" end="${b.indent}">
					<span>&nbsp;&nbsp;</span>
				</c:forEach>
				<span style="color:Chocolate;"><b>re..</b></span>
			</c:if>
			
			<!-- 로그인 한 경우에만 글내용 볼 수 있도록 -->  
			<c:if test="${!empty loginID}">
				<a href="detail?jCode=D&seq=${b.seq}">${b.title}</a>
			</c:if>
			<c:if test="${empty loginID}">
				${b.title}
			</c:if>
			</td>
			<td>${b.id}</td><td>${b.regdate}</td><td>${b.cnt}</td>
		</tr>	
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana}">
	<tr><td colspan="5">~~ 출력자료가 1건도 없습니다. ~~ </td>
	</tr>
</c:if>
</table>
<hr>
<div align="center">
<!-- ** Paging Block ** 
   => ver01: QueryString 수동 입력 -> 자동생성
     1) FirstPage, Prev  -->
  <c:choose>
  <c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
  	<a href="bPageList?currPage=1&rowsPerPage=5">FP</a>&nbsp;
  	<a href="bPageList?currPage=${pageMaker.spageNo-1}&rowsPerPage=5">&LT;</a>&nbsp;&nbsp;
  </c:when>
  <c:otherwise>
  	<font color="Gray">FP&nbsp;&LT;&nbsp;</font>
  </c:otherwise>
  </c:choose>
<!-- 2) Display PageNo
	=> currPage 제외한 PageNo 만 a Tag 적용 -->
	
<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
	<c:if test="${i==pageMaker.cri.currPage}">
		<font color="Orange" size="5"><b>${i}</b></font>
	</c:if>
	<c:if test="${i!=pageMaker.cri.currPage}">
	<a href="bPageList?currPage=${i}&rowsPerPage=5">${i}</a>&nbsp;
	</c:if>
</c:forEach>

<!-- 3) Next, LastPage -->
 <c:choose>
 	<c:when test="${pageMaker.next && pageMaker.epageNo>0}">
 		&nbsp;<a href="bPageList?currPage=${pageMaker.epageNo+1}&rowsPerPage=5">&GT;</a>
 		&nbsp;<a href="bPageList?currPage=${pageMaker.lastPageNo}&rowsPerPage=5">LP</a>
 	
 	
 	
 	</c:when>
 <c:otherwise>
    <font color="Gray">&nbsp;&GT;&nbsp;LP</font>
 </c:otherwise>
 </c:choose>

</div>
<hr>
<!-- 로그인 한 경우에만 새글등록 할 수 있도록 -->  
<c:if test="${not empty sessionScope.loginID}">
	&nbsp;<a href="boardInsert">새글등록</a>&nbsp;
</c:if>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>