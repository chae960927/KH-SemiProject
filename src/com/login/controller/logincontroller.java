package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.UserDao;
import com.login.dto.UserDto;


@WebServlet("/login.do")
public class logincontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public logincontroller() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		UserDao dao = new UserDao();
		String command = request.getParameter("command");
		
		if(command.equals("login")) {//로그인
			
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
		
			UserDto user_info = dao.login(user_id, user_pw);
			
			if(user_info.getUser_id()==null) {//로그인 실패
				
				PrintWriter pw = response.getWriter();
				pw.print("<script type='text/javascript'>"
						+ "alert('ID/PW가 틀렸습니다');"
						+ "location.href='login.jsp';"
						+ "</script>");
				
				
			}else {//로그인 성공
				HttpSession user = request.getSession();
				user.setAttribute("user", user_info);
				user.setAttribute("login","login");
				response.sendRedirect("index.jsp");
				
			}
			
		}else if(command.equals("mypage")) {//마이페이지
			
			String member = request.getParameter("member");
			if(member.equals("개인")) {
				response.sendRedirect("mypage_solo.jsp");
				
			}else if(member.equals("기업")) {
				response.sendRedirect("mypage_company.jsp");
				
			}else if(member.equals("관리자")) {
				response.sendRedirect("");//관리자 페이지
			}
			
		}else if(command.equals("logout")) {//로그아웃
			
			HttpSession user = request.getSession();
			user.invalidate();
			response.sendRedirect("index.jsp");
			
		}else if(command.equals("socialLogin")) {//소셜 로그인
			String nickName = request.getParameter("nickName"); 
			
			String[] name = nickName.split("@");
			nickName = name[0];
			
			UserDto user_info = new UserDto();
			user_info.setNick_name(nickName);
			user_info.setUser_img("./images/logo.jpeg");
			user_info.setMember("개인");//소셜 로그인은 무조건 개인
			System.out.println(user_info.getNick_name());
			HttpSession user = request.getSession();
			user.setAttribute("user", user_info);
			user.setAttribute("login","login");
			response.sendRedirect("index.jsp");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
