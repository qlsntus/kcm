package controllerM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;


@WebServlet("/mdelete")
public class C06_mDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public C06_mDelete() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청분석
		//=> request 의 한글(post요청시 필수)Parameter처리
		//=> 성공: mdetail 유도 (memverDetail.jsp)
		//=> 실패: 재수정 유도 (updateForm.jsp)
		//=> 출력객체 (apple) 필요함 
		//  ->  1) redirect 또는 전달된 값들을 apple 에 보관
		
		String id = (String)request.getSession().getAttribute("loginID");
		String uri="home.jsp"; // 성공/ 실패 모두
		
		
		//2. 서비스 처리
				//=> Service, 객체생성&실행
		MemberService service = new MemberService();
		if (service.delete(id)> 0) {
			request.setAttribute("message", id+"님 탈퇴 성공, 1개월후 재가입 가능해요.");
			request.getSession().invalidate();
		}else {
			//=> 실패
		}
			request.setAttribute("message", id+"님 탈퇴 실패!! 관리자에게 문의 하세요.");
			
		
	
	
		
		//3. View (Response) : Forward
		request.getRequestDispatcher(uri).forward(request, response);
	} //doGet

	
} // class
