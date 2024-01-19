package servlet03_flow;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessioni")
public class Ex03_SessionInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Ex03_SessionInfo() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // 1. Session 인스턴스 생성
	      // => Session 객체는 클라이언트가 접속과 동시에 서버에서 자동 생성됨
	      //    이 값을 코드내에서 사용하기위해 전달받음 
		HttpSession session = request.getSession();
		
		//2. Session Info 출력
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h2>** Session Info **</h2>");
		out.printf("<h3>* Session_ID: %s </h3>", session.getId());
		out.printf("<h3>* 현재시간: %s </h3>", formatter.format(now));
		// => session 생성시간
		now.setTime(session.getCreationTime());
		out.printf("<h3>* CreationTime : %s </h3>", formatter.format(now));
	// => 마지막 접근시간	
		now.setTime(session.getLastAccessedTime());
		out.printf("<h3>* LastAccessedTime : %s </h3>", formatter.format(now));
		
		//8BF5768AA3206A1D603314F0C7D93FC5
		//CreationTime : 2024-01-12 16:34:34
		
		//3. Session Time (제한시간) 설정
		// => 메서드: setMaxInactiveInterval(10), 단위는 초 ,1시간(60*60)
		// => 설정파일 (web.xml)
		//    Tag session-config 의 subTag session-timeout 
		
//		session.setMaxInactiveInterval(10); // 10초 
		//8BF5768AA3206A1D603314F0C7D93FC5
	
		// 4. Session 무효화(종료)
	      // => invalidate : 무효화
	      //    세션객체 자체를 소멸시키는것이 아니라, 세션을 초기화하고 무효화시킴.
	      //    session 이 null 이 아니고 session = ""
	      
	      // => 퀴리스트링으로 테스트 ( ~~/sessioni?jCode )
	      // => 주의: jCode 라는 Parameter 가 없는 경우 nu=Dll 을 return 
	      //         -> NullPointerException 예방 하도록 작성
		if("D".equals(request.getParameter("jCode")) ) {
		session.invalidate();
		System.out.println("** Session 무효화 성공");
		out.print("<h3>** Session 종료!! **</h3>");
		return;
		
		
		}
		out.print("<h3>** Session Info 정상종료 ** </h3>");
	} //doget



}//class
