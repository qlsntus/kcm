<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** RadioButton Test **</title>
</head>
<body>
<h2>** RadioButton Test (get/post test) **<br><br>
<form action="/web01/radio" method="get">
	성 별 : 	<input type="radio" name="gender" id="fe" value="여성" checked>
			<label for="fe">여성</label>&nbsp;&nbsp;
		  	<input type="radio" name="gender" id="me" value="남성">
			<label for="me">남성</label><br>
			
	메일수신:	<input type="radio" name="mailcheck" id="yes" value="Yes" checked>
			<label for="yes">예</label>&nbsp;&nbsp;	
			<input type="radio" name="mailcheck" id="no" value="No">
			<label for="no">아니오</label><br><br>
			<hr>
	<label for="con">가입인사:</label><br>
	<textarea rows="5" cols="50" name="content" id="con"></textarea>
	<br><br>
	<input type="submit" value="전송">&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="reset" value="취소">			
</form>
</h2>
</body>
</html>