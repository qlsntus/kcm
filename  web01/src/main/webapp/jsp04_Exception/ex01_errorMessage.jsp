<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Error Message Page **</title>
</head>
<body>
<pre><h3>
** Error Message Page **
=> exception객체
   - 전달받은 예외객체를 표현하는 객체
   - page 디렉티브의 isErrorPage="true" 일때만 사용 가능

=> 서비스 처리과정에서 <%=exception.getClass().getSimpleName() %>
   오류가 발생 했습니다. 
   잠시후 다시 해주세요 ~~
   
=> Exception Type: <%=exception.getClass().getName()%>
=> Exception toString: <%=exception.toString()%>
=> Exception getMessage: <%=exception.getMessage()%>







</h3></pre>
</body>
</html>