<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** LoginForm **</title>
	<link rel="stylesheet" type="text/css" 
		  href="/resources/myLib/myStyle.css" >
<script src="/resources/myLib/inCheck.js"></script>
<script>
"use strict"
let iCheck=false;
let pCheck=false;
onload=function() {
	  document.getElementById('id').focus();
	  document.getElementById('id').addEventListener('keydown',
		      (e)=>{
		         if (e.which==13) {
		            e.preventDefault();
		            document.getElementById('password').focus();
		         }//if
		      });
	  // -> 무결성 점검 
	   document.getElementById('id').addEventListener('focusout', ()=>{ iCheck=idCheck(); });
	  
	   // => Password
		  document.getElementById('password').addEventListener('keydown',
			      (e)=>{
			         if (e.which==13) {
			          e.preventDefault();
			          document.getElementById('submitTag').focus();
		               // => password 에서 입력후 Enter_Key 누르면 바로 submit 진행 되도록~~
		               //     type="submit" 을 사용하는경우 정확하게 적용하기 어려워 적용하지 않음    
		               //if (!iCheck) iCheck=idCheck();
		               //else if (!pCheck) pCheck=pwCheck();
		               //else document.getElementById('myForm').submit();
			         }//if
			      });
		  document.getElementById('password').addEventListener('focusout', ()=>{ pCheck=pwCheck(); });
	   
	   
   }//onload
   function inCheck() {
	   
	   if (!iCheck) { document.getElementById('iMessage').innerHTML=' 필수입력, id 를 확인하세요~~'; }
	   if (!pCheck) { document.getElementById('pMessage').innerHTML=' 필수입력, password 를 확인하세요~~'; }
	   if (iCheck && pCheck) {
		   return true;
	   }else {
		   return false
	   }  
	   } //Check_조건
	   
	//inCheck

</script>		  
		  
</head>
<body>
<h2>** Spring_MVC2 LoginForm **</h2>
<form action="login" method="post" id="myForm">
<table>
	<tr height="40">
		<td bgcolor="Gold"><label for="id">I D</label></td>
		<td><input type="text" name="id" id="id" size="20">
		<br><span id="iMessage" class="eMessage"></span>
		</td>
	</tr>
	<tr height="40">
		<td bgcolor="Gold"><label for="password">Password</label></td>
		<td><input type="password" name="password" id="password" size="20">
		<br><span id="pMessage" class="eMessage"></span>
		</td>
	</tr>
	<tr><td></td>
		<td><input type="submit"  id="submitTag" value="로그인"
		onclick="return inCheck()">&nbsp;&nbsp; 
		<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
<br><hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br>
</c:if>
<hr>
&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>