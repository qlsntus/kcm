package myDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

public class C02_mDetail implements MyController {
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//Member Detail
				MemberService service = new MemberService();
				request.setAttribute("apple", service.selectOne("banana"));
				return "member/memberDetail";
		
	}

}
