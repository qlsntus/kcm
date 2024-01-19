package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;


@WebServlet("/mupdate")
public class C05_mupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public C05_mupdate() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청분석
		//=> request 의 한글(post요청시 필수)Parameter처리
		//=> 성공: mdetail 유도 (memverDetail.jsp)
		//=> 실패: 재수정 유도 (updateForm.jsp)
		//=> 출력객체 (apple) 필요함 
		//  ->  1) redirect 또는 전달된 값들을 apple 에 보관
		
		String uri = "member/memberDetail.jsp"; //성공시
		request.setCharacterEncoding("UTF-8");
		
		
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPassword(request.getParameter("password"));
		dto.setName(request.getParameter("name"));
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setJno(Integer.parseInt(request.getParameter("jno")));
		dto.setInfo(request.getParameter("info"));
		dto.setPoint(Double.parseDouble(request.getParameter("point")));
		dto.setBirthday(request.getParameter("birthday"));
		dto.setRid(request.getParameter("rid"));
		
		// => 결과 출력을 위해 전달된 값들을 apple에 보관
		request.setAttribute("apple", dto);
		
		//2. 서비스 처리
				//=> Service, 객체생성&실행
		MemberService service = new MemberService();
		if (service.update(dto) > 0 ) {
			// => 성공 : session에 보관한 Name 수정
			request.getSession().setAttribute("loginName",dto.getName() );
			request.setAttribute("message","~~회원정보수정 성공!!" );
		}else {
			// => 실패 
			request.setAttribute("message","~~회원정보수정 실패!! 다시하세요~~" );
			uri="member/updateForm.jsp";
		}
		//3. View (Response) : Forward
		request.getRequestDispatcher(uri).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
