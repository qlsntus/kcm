<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSTL if(조건분기) Test Main **</title>
</head>
<body>
<h2>** JSTL if(조건분기) Test Main **</h2>
<pre>
=> if, choose (java 의 switch ~ case)

1) if
=> test 속성에 EL 을 사용해서 조건지정
=> else 구문 없음

2) choose
=> if 문에 else 구문 없음을 보완 
=> java 의 switch ~ case

3) 특징 
=> test 속성에 EL을 이용해서 조건지정
=> EL 관계식 활용 : 모든 Type 에 "==" 사용가능
</pre>
<hr><b>
=> Java Code 와 비교<br>
<%	String color=request.getParameter("color");
	if (color.equals("1")) { %>
		<span style="color:red;">Red 선택</span>
<%	}else if (color.equals("2")) { %>
		<span style="color:green;">Green 선택</span>
<%	}else if (color.equals("3")) { %>
		<span style="color:blue;">Blue 선택</span>
<%	}else if (color.equals("4")) { %>
		<span style="color:yellow;">Yellow 선택</span>
<%	}else { %>
		<span style="color:purple;">Color 선택오류</span>
<% 	} %>
<br>
=> if Tag<br>
<c:if test="${param.color==1}" >
	<span style="color:red;">Red 선택</span>
</c:if>
<c:if test="${param.color==2}" >
	<span style="color:green;">Green 선택</span>
</c:if>
<c:if test="${param.color==3}" >
	<span style="color:blue;">Blue 선택</span>
</c:if>
<c:if test="${param.color==4}" >
	<span style="color:yellow;">Yellow 선택</span>
</c:if>
<c:if test="${param.color==5 || empty param.color}" >
	<span style="color:purple;">Color 선택오류</span>
</c:if>


=> choose Tag<br>
<c:choose>
	<c:when test="${param.color==1}">
	<span style="color:red;">Red 선택</span>
	</c:when>
		<c:when test="${param.color==2}">
	<span style="color:green;">Green 선택</span>
	</c:when>
		<c:when test="${param.color==3}">
	<span style="color:blue;">Blue 선택</span>
	</c:when>
		<c:when test="${param.color==4}">
	<span style="color:yellow;">Yellow 선택</span>
	</c:when>
	<c:otherwise>
	    <span style="color:purple;">Color 선택오류</span>
	</c:otherwise>

</c:choose>

</b>

</body>
</html>