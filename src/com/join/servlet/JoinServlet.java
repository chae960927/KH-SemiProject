package com.join.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.join.controller.JoinController;
import com.join.dto.JoinDto;

@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		JoinController biz = new JoinController();
		
		if(command.equals("join")) {
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			String user_name = request.getParameter("user_name");
			String nick_name = request.getParameter("nick_name");
			String user_phone = request.getParameter("user_phone");
			String user_email = request.getParameter("user_email");
			String user_addr = request.getParameter("user_addr");
			String user_addr_de = request.getParameter("user_addr_de");
			String member = request.getParameter("user_member");
			String biz_num = null;
			if(request.getParameter("biz_num") != null) {
				biz_num = request.getParameter("biz_num");
			}
			
			JoinDto dto = new JoinDto();
			dto.setUser_id(user_id);
			dto.setUser_pw(user_pw);
			dto.setUser_name(user_name);
			dto.setNick_name(nick_name);
			dto.setUser_phone(user_phone);
			dto.setUser_email(user_email);
			dto.setUser_addr(user_addr);
			dto.setUser_addr_de(user_addr_de);
			dto.setMember(member);
			dto.setBiz_num(biz_num);
			
			int res = biz.join(dto);
			
			if(res > 0) {
				String msg = "반갑습니다! 로그인 해주세요.";
				String url = "login.jsp?command=login";
				jsPesponce(msg, url, response);
			} else {
				String msg = "회원가입에 실패했습니다.";
				String url = "join.jsp";
				jsPesponce(msg, url, response);
			}
		}
	}
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	public void jsPesponce(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"
				+ "alert('" + msg + "');"
				+ "location.href='" + url + "';"
				+ "</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
