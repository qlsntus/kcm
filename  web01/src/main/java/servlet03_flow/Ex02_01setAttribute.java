package servlet03_flow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/01set")
public class Ex02_01setAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex02_01setAttribute() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. request 처리
		// => form 없이 쿼리스트링으로 Test
		//    ~~/01set?id=banana&name=홍길동&age=22
		// => 한글처리 (Post 요청시 필수)
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		//int age =Integer.parseInt(request.getParameter("age"));
		//=> get 처리오류 때문 불편
		String age=request.getParameter("age");
		
		System.out.println("** setAttribute Test **");
		System.out.printf("** Parameter id=%s, name=%s, age=%s \n",id,name,age);
		
		//2. setAttribute
		// => 보관가능한 객체, Scope: Page < Request < Session < Application
		//2.1) request
		request.setAttribute("rid", id);
		request.setAttribute("rname", name);
		request.setAttribute("rage", age);
		
		//2.2) session
		HttpSession session = request.getSession();
		session.setAttribute("sid", id);
		session.setAttribute("sname", name);
		session.setAttribute("sage", age);
		
		//3. 이동 후 getAttribute
		//=> Forward / Redirect
		String uri="02get";
		//3.1) Forward
//		request.getRequestDispatcher(uri).forward(request, response);
		
	    //3.2) Redirect
	    response.sendRedirect(uri);
	} //doGet

} //class
