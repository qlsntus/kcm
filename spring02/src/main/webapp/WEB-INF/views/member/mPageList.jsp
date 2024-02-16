<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** Spring MVC2 MemberPageList **</title>
	<link rel="stylesheet" type="text/css" 
		  href="/spring02/resources/myLib/myStyle.css">
<script>
"use strict"
//1.검색조건 입력후 버튼클릭
//=> 입력값들을 서버로 전송 요청처리: location 

// ** self.location   
// 1) location 객체 직접사용 Test : url로 이동, 히스토리에 기록됨
// 2) location 객체의 메서드
// => href, replace('...'), reload() 

 
function searchDB() {
         self.location='mPageList'
                        + '?currPage=1&rowsPerPage=5'
                        +'&searchType='+document.getElementById('searchType').value
                        +'&keyword='+document.getElementById('keyword').value;
}


// 2. searchType 을 '전체' 로 변경하면 keyword는 clear 
function keywordClear() {
	if (document.getElementById('searchType').value=='all')
		document.getElementById('keyword').value='';
} 

//** Board Check_List***************
function checkClear () {
	//document.querySelectorAll('.clear').checked=false;
	//=> nodeList를 반환하기 떄문에 적용안됨
	let ck=document.querySelectorAll('.clear')
	for (let i=0; i<ck.length; i++) {
		ck[i].checked=false;
	}
	return false; // reret 의 기본이벤트 제거
	

</script>
</head>
<body>
<h2>** Spring MVC2 MemberPageList **</h2>
<hr>
<c:if test="${!empty requestScope.message}">
 ${requestScope.message}<br><hr>
</c:if>
<hr>
<div id="searchBar">
	<select name="searchType" id="searchType" onchange="keywordClear()">
		<option value="all" ${pageMaker.cri.searchType=='all' ? 'selected' : '' }        >전체</option>
		<option value="id" ${pageMaker.cri.searchType=='id' ? 'selected' : '' }>ID</option>
		<option value="name" ${pageMaker.cri.searchType=='name' ? 'selected' : '' }>Name</option>
		<option value="age" ${pageMaker.cri.searchType=='age' ? 'selected' : '' }>Age</option>
		<option value="birthday" ${pageMaker.cri.searchType=='birthday' ? 'selected' : '' }>Birthday</option>
		<option value="info" ${pageMaker.cri.searchType=='id' ? 'info' : '' }>Info</option>
		<option value="rid" ${pageMaker.cri.searchType=='rid' ? 'selected' : '' }>추천인</option>
		
	</select>
	<input type="text" name="keyword" id="keyword" value="${pageMaker.cri.keyword}">
	<button id="searchBtn" onclick="searchDB()">Search</button>
	<hr>
	<!--CheckBox Test -->
	<form action="mCheckList" method="get">
	<b>ID : </b>
	<!-- check 의 선택한 값 유지를 위한 코드 -->
      <c:set var="ck1" value="false" />
      <c:set var="ck2" value="false" />
      <c:set var="ck3" value="false" />
      <c:set var="ck4" value="false" />
      <c:set var="ck5" value="false" />
      <c:forEach  var="id" items="${pageMaker.cri.check}" >
         <c:if test="${jno=='1'}"> <c:set var="ck1" value="true" /> </c:if>
         <c:if test="${jno=='2'}"> <c:set var="ck2" value="true" /> </c:if>
         <c:if test="${jno=='3'}"> <c:set var="ck3" value="true" /> </c:if>
         <c:if test="${jno=='4'}"> <c:set var="ck4" value="true" /> </c:if>
         <c:if test="${jno=='7'}"> <c:set var="ck5" value="true" /> </c:if>
      </c:forEach>
      <!-- --------------------------------- -->
	
	<input type="checkbox" name="check" class="clear" value="1" ${ck1 ? 'checked':''}>Business&nbsp;
	<input type="checkbox" name="check" class="clear" value="2" ${ck2 ? 'checked':''}>static&nbsp;
	<input type="checkbox" name="check" class="clear" value="3" ${ck3 ? 'checked':''}>칭찬해조&nbsp;
	<input type="checkbox" name="check" class="clear" value="4" ${ck4 ? 'checked':''}>카톡으로&nbsp;
	<input type="checkbox" name="check" class="clear" value="7" ${ck5 ? 'checked':''}>칠면조&nbsp;
	<input type="submit" value="Search">&nbsp;
	<input type="reset" value="Clear" onclick="return checkClear()"><br>
	
	
	</form>
</div>
<table style="width:100%">
<tr bgcolor="Khaki" >
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
<td><img alt="myImage" src="/spring02/resources/uploadImages/${m.uploadfile}"
width="50" height="70" ></td>
			<%-- <td>${m.password}</td> --%>
		</tr>	
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana}">
	<tr><td colspan="9">~~ 출력자료가 1건도 없습니다. ~~ </td>
	</tr>
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
	

<c:if test="${empty requestScope.banana}">
	<tr><td colspan="5">~~ 출력자료가 1건도 없습니다. ~~ </td>
	</tr>
</c:if>
</table>
<hr>
<div align="center">
  <!--1)Prev, First -->
  <c:choose>

  <c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
 <!--  ver02: makeQuery 메서드 -> searchQuery 메서드로 변경 -->
  	<a href="${pageMaker.searchQuery(1)}">FP</a>&nbsp;
  	<a href="${pageMaker.searchQuery(pageMaker.spageNo-1)}">&LT;</a>&nbsp;&nbsp;
  </c:when>
  <c:otherwise>
  	<font color="Gray">FP&nbsp;&LT;&nbsp;</font>
  </c:otherwise>
  </c:choose>
<!--2) PageNo  -->
	
<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
	<c:if test="${i==pageMaker.cri.currPage}">
		<font color="Orange" size="5"><b>${i}</b></font>
	</c:if>
	<c:if test="${i!=pageMaker.cri.currPage}">
	<a href="${pageMaker.searchQuery(i)}">${i}</a>&nbsp;
	</c:if>
</c:forEach>

<!-- 3) Next, LastPage -->
 <c:choose>
 	<c:when test="${pageMaker.next && pageMaker.epageNo>0}">
 		&nbsp;<a href="${pageMaker.searchQuery(pageMaker.epageNo+1)}">&GT;</a>
 		&nbsp;<a href="${pageMaker.searchQuery(pageMaker.lastPageNo)}">LP</a>
 	
 	
 	
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