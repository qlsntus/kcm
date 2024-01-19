<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSTL if Test Form **</title>
</head>
<body>
<h2>** JSTL if Test Form **</h2>
<form action="ex02_ifMain.jsp" method="get">
	<select name="color">
		<option value="">color를 선택하세요</option>
		<option value="1">Red</option>
		<option value="2">Green</option>
		<option value="3">Blue</option>
		<option value="4">Yellow</option>
		<option value="5">Error</option>
	</select>
	<input type="submit" value="전송">
</form>
</body>
</html>