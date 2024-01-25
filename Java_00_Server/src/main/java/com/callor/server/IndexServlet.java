package com.callor.server;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  Servlet : Server Applet
 *  Java 코드를 사용하는 Server 어플리케이션 클래스
 *  HttpServlet 클래스를 상속받고, @WepServlet Annotation 을 부착한다
 *  doGet(), doPost() method 를 기본적으로 구현한다
 * 
 */
@WebServlet({ "/index" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public IndexServlet() {
        super();

    }

    // router.get("/",(req,res)=>{})
    // java 는 type 이중요하므로 위의것과 비슷하지만 풀네임으로 적는다
    // 화면에 이도구를 가져와라
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String view = "/WEB-INF/views/index.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
				dispatcher.forward(req,res); // forward 가 nodejs의 render
		
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath()); 
		// 사용자가 요청한 텍스트를 하나의 문자열로 만들어 응답하라
		// node.js 의 res.send 와 비슷하다
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
