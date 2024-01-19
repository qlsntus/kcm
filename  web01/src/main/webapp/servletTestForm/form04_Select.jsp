<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Select Test **</title>
</head>
<body>
<h2>** Select Test **</h2>
<form action="/web01/select" method="get">
<h2><span style="float: left; margin-right: 20px">
	<label for=jo>직 업 :</label>
	<select id="jo" name="job" size="1">
		<option value="">선택하세요</option>
		<option value="Student">학생</option>
		<option value="JavaProgrammer">Java개발자</option>
		<option value="공무원">공무원</option>
		<option value="디자이너">디자이너</option>
		<option value="교육자">교육자</option>
	</select>
</span>
	<label for=it style="float: left;">관심분야 : </label>
	<select id=it name="interest" size="5" multiple="multiple">
		<option value="카푸치노">카푸치노</option>
		<option value="에스프레소">에스프레소</option>
		<option value="아이스크림">아이스크림</option>
		<option value="마카롱">마카롱</option>
		<option value="다크쵸코">다크쵸코</option>
		<option value="딸기크림">딸기크림</option>
		<option value="바나나케잌">바나나케잌</option>
	</select><br>
	<input type="submit" value="전송"> &nbsp;
	<input type="reset" value="취소">
</h2>
</form>
</body>
</html>