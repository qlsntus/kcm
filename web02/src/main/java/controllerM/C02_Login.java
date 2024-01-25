package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;




@WebServlet("/login")
public class C02_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public C02_Login() {
        super();
       
    }

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.요청분석
		// => request 의 Parameter 처리
		// => id, password 처리
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id").trim();
		String password = request.getParameter("password").trim();		
		String uri = "home.jsp";
		
		
		// 2. 서비스 처리
		// => Service, DTO 객체생성
		MemberService service = new MemberService();
		MemberDTO dto = service.selectOne(id);

		if ( dto!=null && dto.getPassword().equals(password) ) {
			request.getSession().setAttribute("loginName", dto.getName());
			request.getSession().setAttribute("loginID", id);
			System.out.println("** 로그인 성공 **");
			System.out.println("** 로그인 Student => "+dto);
			response.sendRedirect(uri);
		}else {
			System.out.println("** 로그인 실패 **");
			request.setAttribute("message", "로그인 실패!!! 다시 하세요~~");
			uri="member/loginForm.jsp" ;
			request.getRequestDispatcher(uri).forward(request, response);
		}
		
		
		
		
		
		
		// 3. View (Response) : Forward
	
	}

}
