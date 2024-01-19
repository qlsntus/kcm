package servlet03_flow;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/login")
public class Ex04_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex04_Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => 한글, request 의 Parameter 처리
		request.setCharacterEncoding("UTF-8");
		
//		String sno = request.getParameter("sno").trim();
		int sno = 0;
		if ( request.getParameter("sno").trim() !=null && request.getParameter("sno").trim().length()>0 ) {
			sno = Integer.parseInt(request.getParameter("sno").trim());
		}
		String name = request.getParameter("name").trim();		
		String uri = "home.jsp";
		
		// 2. Service 처리
		// => ~Service, ~DTO 의 인스턴스 
		// => Service 의 selectOne : sno 확인
		//    확인결과 성공이면 name 확인
		// => 성공: index.html
		// => 실패: ~LoginForm.jsp (재로그인 유도)
		StudentService service = new StudentService();
		StudentDTO dto = service.selectOne(sno);
		if ( dto!=null && dto.getName().equals(name) ) {
			request.getSession().setAttribute("loginName", name);
			request.getSession().setAttribute("loginID", sno);
			System.out.println("** 로그인 성공 **");
			System.out.println("** 로그인 Student => "+dto);
			response.sendRedirect(uri);
		}else {
			System.out.println("** 로그인 실패 **");
			request.setAttribute("message", "로그인 실패!!! 다시 하세요~~");
			uri="servletTestForm/flowEx04_LoginForm.jsp" ;
			request.getRequestDispatcher(uri).forward(request, response);
		}
		
		// 3. View (Response) : Forward or Redirect
		//request.getRequestDispatcher(uri).forward(request, response);
	
	} //doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} //class
