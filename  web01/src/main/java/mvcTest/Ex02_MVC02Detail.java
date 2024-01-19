package mvcTest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MVCDetail")
public class Ex02_MVC02Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Ex02_MVC02Detail() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentService Service = new StudentService();
		StudentDTO dto = Service.selectOne((Integer)request.getSession().getAttribute("loginID"));
		
		request.setAttribute("myInfo",dto);
		request.getRequestDispatcher("mvcTestJsp/ex03_MVC02Detail.jsp").forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
