package controllerF;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

public class Ex05_mDetail implements Ex04_Controller {

	@Override
	public String doUser(HttpServletRequest request, HttpServletResponse response) {
		MemberService service = new MemberService();
		// ** Member Detail : Login 후 Test
		String id = (String)request.getSession().getAttribute("loginID");
		request.setAttribute("apple", service.selectOne(id));
		return "member/memberDetail.jsp";
		
	} // doUser

}
