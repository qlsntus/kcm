package controllerF;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

//** FrontController 패턴 1.
//=> 대표 컨트롤러 1개만 서블릿으로 작성
// 모든 요청을 이 대표컨트롤러(FrontController) 가 받도록 함.
// 나머지 컨트롤러는 일반 클래스로 작성 (2단계, Factory 패턴 적용)

//=> 요청에 대한 서비스를 if문으로 서블릿내에서 모두 처리
// 코드가독성, 모듈의 재사용성 떨어짐 (1단계) 





@WebServlet(urlPatterns = {"*.do"})
public class Ex01_FrontBasic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Ex01_FrontBasic() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청 분석
		// => url 분석후 요청명 확인
		// => 한글처리 
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		// ~URL => /web02/test.do
		// ~URL => http://localhost:8080/web02/test.do
		uri=uri.substring(uri.lastIndexOf("/"));
		// uri => /test.do
		
		System.out.println("** ~URL => "+request.getRequestURL());
		System.out.println("** ~URI => "+request.getRequestURI());
		System.out.println("** ~uri => "+uri);
		
		// 2) Service 실행
		MemberService service = new MemberService();
		MemberDTO dto = new MemberDTO();
		
		if ("/mlist.do".equals(uri)) {
			// ** Member LIst
			request.setAttribute("banana", service.selectList());
			uri="member/memberList.jsp";
		}else if ("/mdetail.do".equals(uri)) {
			// ** Member Detail : Login 후 Test
			String id = (String)request.getSession().getAttribute("loginID");
			request.setAttribute("apple", service.selectOne(id));
			uri="member/memberDetail.jsp";
		}else {
			request.setAttribute("message", "요청에 해당하는 서비스는 없습니다.");
			uri="home.jsp";
		}
		
		// 3) View처리
		request.getRequestDispatcher(uri).forward(request, response);
		
	
		
		
	} //doGet

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	} // doPost

}// class
