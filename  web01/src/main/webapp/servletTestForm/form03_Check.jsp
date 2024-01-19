<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** CheckBox Test **</title>
</head>
<body>
<h2>** 원하는것을 마음껏 고르세요 ~~ **</h2>
<form action="/web01/check" method="get">
	<h3>
	<input type="checkbox" name=gift value='shoes'>구두
	<input type="checkbox" name=gift value="belt">벨트
	<input type="checkbox" name=gift value="hats">모자<br>
	<input type="checkbox" name=gift value="bag">가방
	<input type="checkbox" name=gift value="여행">여행
	<input type="checkbox" name=gift value="독서">독서<br>
	<input type="checkbox" name=gift value="낮잠">낮잠
	<input type="checkbox" name=gift value="영화">영화
	<input type="checkbox" name=gift value="음악">음악<br><br>
	<input type="submit" value="전송"> &nbsp;&nbsp;&nbsp;
	<input type="reset" value="취소">
	</h3>
</form>
</body>
</html>