package mvcTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcTest.StudentDTO;
import mvcTest.StudentService;


@WebServlet("/list2")
public class Ex01_MVC02List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Ex01_MVC02List() {
        super();

    }
    //** MVC 패턴2 StudentList 출력하기
    // => 요청 Service 처리
    // => 결과 출력 (Java 스크립트)

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//=> 요청 Service 처리
		StudentService Service = new StudentService();
		List<StudentDTO> list = Service.selectList();
	
		
		//=> 결과 출력 : Jsp,Java 스크립트
		//=> Service 결과물인 List를 Jsp가 출력 도리수 있도록 Attribute에 보관
		// -> request.setAttribute(....)
		request.setAttribute("myList",list);
		
		//=> Forward
		request.getRequestDispatcher("mvcTestJsp/ex02_MVC02List.jsp").forward(request, response);
	
	} //doGet

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
