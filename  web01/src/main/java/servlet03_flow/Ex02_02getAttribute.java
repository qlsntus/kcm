package servlet03_flow;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// ** getAttribute
// => 전달된 Attribute 확인 출력

@WebServlet("/02get")
public class Ex02_02getAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex02_02getAttribute() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Attribute 확인 
		// => getAttribute
		// => request
		String rid=(String)request.getAttribute("rid");
		String rname=(String)request.getAttribute("rname");
		//int rage =Integer.parseInt((String)request.getAttribute("rage"));
		String rage =(String)request.getAttribute("rage");
		
		
		// => session
		HttpSession session = request.getSession();
		String sid=(String)session.getAttribute("sid");
		String sname=(String)session.getAttribute("sname");
		String sage =(String)session.getAttribute("sage");
		
		// 2. View 처리
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h2>** 1) Parameter 값 확인 **</h2>");
		out.print("<h3>=> request객체에 담겨있는 Parameter값이 유지되고 있는지 확인</h3>");
		out.printf("<h3> I D : %s </h3>", request.getParameter("id"));
		out.printf("<h3> Name : %s </h3>", request.getParameter("name"));
		out.printf("<h3> Age : %s </h3>", request.getParameter("age"));
		out.print("<h2>** 2) request Attribute 값 확인 **</h2>"); 
		out.printf("<h3> RID : %s </h3>", rid);
		out.printf("<h3> RName : %s </h3>", rname);
		out.printf("<h3> RAge : %s </h3>", rage);
		out.print("<h2>** 3) session Attribute 값 확인 **</h2>"); 
		out.printf("<h3> SID : %s </h3>", sid);
		out.printf("<h3> SName : %s </h3>", sname);
		out.printf("<h3> SAge : %s </h3>", sage);
	
	} //doGet

} //class
