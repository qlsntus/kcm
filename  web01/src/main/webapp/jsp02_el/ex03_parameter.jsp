<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Parameter 활용 **</title>
</head>
<body>
<h2>** Parameter 활용**</h2>
<pre><b>
=> 동짌헝 비교 , null확인 (값의 존재) 확인
1. 동질성 비교
* I D : ${param.id}
* Password : ${param.password}

\${param.id=='admin'} => ${param.id=='admin'}
\${param.password=='12345'} => ${param.password=='12345'}

2. null(값의 존재) 확인 : empty, ==
=> 쿼리스트링으로 비교 Test
	~/ex03_parameter.jsp?id=admin&password=12345!
	~/ex03_parameter.jsp?id=admin&password=
	~/ex03_parameter.jsp?id=admin
	
2.1) ==null 
=>=> 해당하는 Parameter가 존재하지않으면 true, 존재하지만 값이 없는 경우에는 false
\${param.id==null} => ${param.id==null}
\${param.password==null} => ${param.password==null}

2.2) empty
=> => 해당하는 Parameter가 존재하지않거나, 존재하지만 값이 없는 경우 모두 true 
\${empty_param.id} => ${empty param.id}
\${empty_param.password} => ${empty param.password}

3.pageContext
=> Jsp 페이지에 대한 정보를 저장하는 객체(pageScope)
=> 기본객체를 return 하는 메서드를 제공
* 요청 URL: ${pageContext.request.requestURL}
* 요청 URI: ${pageContext.request.requestURI}

 














</b>









</pre>

</body>
</html>