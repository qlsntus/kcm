package controllerF;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

public class Ex05_mList implements Ex04_Controller {

	@Override
	public String doUser(HttpServletRequest request, HttpServletResponse response) {
		MemberService service = new MemberService();
		request.setAttribute("banana", service.selectList());
		return "member/memberList.jsp";
		
		
	}

	
}
