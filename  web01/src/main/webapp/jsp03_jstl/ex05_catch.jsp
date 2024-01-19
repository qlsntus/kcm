<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** catch Tag Test **</title>
</head>
<body>
<h2>** catch Tag Test **</h2>
<pre>
=> 예외상황을 처리해 주는 Tag 
   비정상적 종료(500 오류)를 예방하고 대응하기 위함. 
=> java 
	try {
		.....
	}catch(Exception e) {
		.....
	}finally {
		......
	}  
	
=> c:catch ~~ /c:catch 블럭에서 Exception 발생시 대응
=> 예외발생시 발생한 Exception 객체를 var의 값에 저장하고 catch블럭 밖으로 진행 
-----------------------------------------
<b>
** catch 블럭 밖 예외발생 비교 Test
Test1) catch 블럭 적용 전
=> by Zero -> 500 발생, 현재행에서 비정상 종료
=> Java 예외발생: 10/0 => <%=10/10%>

Test2) catch 블럭 적용
=> Exception 발생시 발생한 행에서 
   Exception객체를 catch Tag에서 정의한 변수에 저장하고
   catch 블럭 밖으로 진행됨 (그러므로 500은 발생하지않음 )
<c:catch var="e"> 
	** 예외발생 전
	=> Java 예외발생: 10/0 = <%=10/10%>
		-> by Zero Exception
		
	=> EL 예외발생 10/0 = ${10/0}
		-> EL 나누기연산은 실수형으로 처리
		   그러므로 by Zero 결과는 Exception 발생없고 Infinity(무한수)	
		-> EL % 연산1 : 10%0 = ${10%10} -> 정수형 연산 (500발생)  
		-> EL % 연산2 : 10%0.0 = ${10%0.0} -> 실수형 연산 (NaN, Not a Number)
		
	=> Java 실수형 연산에서 by Zero 결과 (Exception 발생없음)
		-> 10.0/0.0 = <%=10.0/0.0%>  
		-> 10.0%0.0 = <%=10.0%0.0%>
</c:catch> 
<!-- 
catch Tag 에 정의된 변수를 이용해서 오류를 확인할수있게해줌
=> null 이면 정상임을 의미
   아니면 오류발생을 의미 -->
<c:if test="${e!=null}">
	=> 연산 오류 발생: ${e}
</c:if>

** Page 정상종료 **
</b></pre>

</body>
</html>