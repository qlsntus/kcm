package servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/life")
public class Ex04_LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int cno=1; //생성자의 호출횟수
	int ino=1; // init 메서드의 호출횟수
	int gno=1; // doGet 메서드의 호출횟수
	int dno=1; // destroy 메서드의 호출횟수
	
	
	 // ** 생성자
	   // => 서버스타트 이후 첫요청 들어오면 WAS 가 자동생성
   
    public Ex04_LifeCycle() {
        super();
        System.out.println("** 생성자의 호출횟수 => "+cno++);
    }
    
    // ** init 메서드
    // => 인스턴스 생성직후 자동호출 
   public void init(ServletConfig config) throws ServletException {
      System.out.println("** init 메서드 호출 횟수 => "+ino++);
   }

   // ** destroy 메서드 : 메모리 소멸
   // => 서버종료시 (서버는 모든 자원을 close)  
   public void destroy() {
      System.out.println("** destroy 메서드 호출 횟수 => "+dno++);
   }
   // ** doGet 메서드
   // => 클라이언트로부터 Get방식 요청이 들어오면 자동호출  

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	      response.setContentType("text/html; charset=UTF-8");
	      PrintWriter out = response.getWriter();
	      out.print("<h2>** Servlet LifeCycle **</h2>");
	      out.print("<pre><h3>");
	      out.println("** 현재시간: "+new Date());
	      out.println("** doGet 메서드 호출 횟수: "+gno);
	      out.print("</h3></pre>");
	      
	      System.out.println("** doGet 메서드 호출 횟수 => "+gno++);
	   } //doGet
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}// doGet

}//class
