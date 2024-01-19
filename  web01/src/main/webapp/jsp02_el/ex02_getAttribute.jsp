<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** EL Scope & Attribute **</title>
<%
// 1) 동일한 속성(Attribute)명을 모든 영역에 정의
// => 호출, 우선순위 
   pageContext.setAttribute("name", "pageContext Value1");
   request.setAttribute("name", "request Value1");
   session.setAttribute("name", "session Value1");
   application.setAttribute("name", "application Value1");
// 2) 서로 다른 속성(Attribute)명을 모든 영역에 정의   
   pageContext.setAttribute("pname", "pageContext Value2");
   request.setAttribute("rname", "request Value2");
   session.setAttribute("sname", "session Value2");
   application.setAttribute("aname", "application Value2");

// 3) 연산자 Test 
// => request 영역에 속성(Attribute) 2개 만들고 활용 Test
   request.setAttribute("num1", 123);
   request.setAttribute("num2", 456);
%>
</head>
<body>
<h2>** EL Scope & Attribute **</h2>
<pre>
1)동일한 속성(Attribute)명을 모든 영역에 정의
   => 호출, 우선순위 
   => el 내부에 변수명이 오면 JSTL의 변수명, 속성(Attribute)의 이름으로 인식
   <b>
   \${name} => ${name}
   => 가까운곳부터 찾아감
   => 동일한 속성명을 사용한 경우에 모두 구별해서 출력하려면
   		속성명을 붙여적용 (앞에 영역_Scope객체가 생략된것임)
   	\${pageScope_name} => ${pageScope.name}
   	\${requestScope_name} => ${requestScope.name}
   	\${sessionScope_name} => ${sessionScope.name}
   	\${applicationScope_name} => ${applicationScope.name}
<hr>
2) 서로 다른 속성(Attribute)명을 모든 영역에 정의
	=> 속성명만 사용하여 출력가능
	=> 그러나 영역(Scope)을 붙여주는것이 효율적 (direct로 인식)
	\${pname} => ${pname}
	\${rname} => ${rname}
	\${sname} => ${sname}
	\${aname} => ${aname}
<hr>
3) 연산자 Test 
=> request 영역에 속성(Attribute) 2개 만들고 활용 Test
3.1) Java 
<% int n1= (Integer)request.getAttribute("num1");
   int n2= (int)request.getAttribute("num2");%>
<%=n1%>+<%=n2%>=<%=n1+n2%>

3.2) EL
${requestScope.num1}+${requestScope.num2}=${num1+num2}

3.3) Parameter Test: Java
=> 쿼리스트링으로 Test : ~/jsp02_el/ex02_getAttribute.jsp?num1=200&num2=789
<% n1 = Integer.parseInt(request.getParameter("num1"));
   n2 = Integer.parseInt(request.getParameter("num2")); %>
<%=n1%>+<%=n2%>=<%=n1+n2%>

3.4) Parameter Test: EL
${param.num1}+${param.num2}=${param.num1+param.num2}







   	
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   </b>






</pre>

</body>
</html>