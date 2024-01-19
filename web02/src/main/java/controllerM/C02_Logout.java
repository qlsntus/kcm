package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/logout")
public class C02_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public C02_Logout() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.요청분석
		// => request 의 Parameter 처리
		// => id, password 처리
		String uri="home.jsp";
		
		// 2. 서비스 처리
		// => Service, DTO 객체생성
		// => id 확인 : Service 의 selectOne
		// => id확인되면 password 일치확인
		// => 성공: id, name을 session에 보관, home으로
		//    실패: loginForm으로, message출력, 재로그인 유도
		request.getSession().invalidate();
		System.out.println("** 로그아웃 되었습니다 **");
		
		// 3. View (Response) : Forward
		response.sendRedirect(uri);
	
	} //doGet

}
