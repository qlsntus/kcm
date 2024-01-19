package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;


@WebServlet("/mlist")
public class C01_mList extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public C01_mList() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청분석
		MemberService service = new MemberService();
		
		// 2) Service 처리, 처리, 처리결과 보관
		
		request.setAttribute("banana", service.selectList());
		
		// 3) View 처리
		request.getRequestDispatcher("member/memberList.jsp").forward(request, response);
		
	}// doGet

	
	
}//class

