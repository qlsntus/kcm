<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
  <%@ page import="mvcTest.StudentService, mvcTest.StudentDTO" %>
  <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jsp StudentList_MVC01 **</title>
</head>
<body>
<h2>** Jsp StudentList_MVC01 **</h2>
<h3>=> Srtvice -> 결과 -> 출력</h3>
<%
StudentService Service = new StudentService();
List<StudentDTO> list = Service.selectList();
%>
<table border="1" style="width:100%">
	<tr bgcolor="Lime"><th>Sno</th><th>Name</th><th>Age</th>
		<th>Jno</th><th>Info</th><th>Point</th>
	</tr>
<% //List 출력구문
if (list !=null) {
	for (StudentDTO s:list) { %>
		 <tr>
		 <td><%=s.getSno() %></td><td><%=s.getName() %></td><td><%=s.getAge() %></td>
		 <td><%=s.getJno() %></td><td><%=s.getInfo() %></td><td><%=s.getPoint() %></td>
		 </tr>
<% 		}
	}else { %>
		<h3>출력할 자료가 없습니다.~~</h3>
<% 	}

%>






</table>

</body>
</html>