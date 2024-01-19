<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>E R R O R 5 0 0</title>
<style type='text/css'>
A:link    {color:black;text-decoration:none;}  /* 아직 방문하지 않은경우 */
A:visited {color:black; text-decoration:none;} /* 한번 이상 방문한 링크 처리 */
A:active  {color:black; text-decoration:none;} /* 마우스로 클릭하는 순간 */
A:hover   {color:black; text-decoration:none;} /* 마우스 링크 위 올려 놓았을때 */
</style>
</head>
<body>
<div style = 'text-align:center'>
<font face = "돋움" size = 3><br>
<h2>E R R O R 5 0 0</h2>
<b>잠시 서비스에 문제가 발생했습니다.</b><br>
빠른 시간 내에 처리하겠습니다. <br>
=> <%=exception.toString()%><br><br>
<img src="/web01/jsp04_Exception/img/remon.gif" width="300" height="200"><br><br>
<a href="#" onclick="history.back()" >
	[돌아가기]</a></font></div>
</body>
</html>