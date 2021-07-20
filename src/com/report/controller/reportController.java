package com.report.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cons.dao.ConDao;
import com.info.dao.InfoDao;
import com.login.dto.UserDto;
import com.report.dao.reportDao;
import com.report.dto.reportDto;


@WebServlet("/reportController.do")
public class reportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public reportController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		reportDao dao = new reportDao();
			
		if(command.equals("insertReport")) {
			String rep_name= request.getParameter("rep_name");//신고자이름
			String rep_id = request.getParameter("rep_id");//신고대상 아이디
			String rep_content = request.getParameter("rep_content");//신고 게시물 내용
			System.out.println("rep_id controller: " + rep_id);
			System.out.println("rep_content controller: " + rep_content);
			String rep_reason = request.getParameter("rep_reason"); //신고 사유
			System.out.println("rep_reason controller: " + rep_reason);
			int rep_listnumber = Integer.parseInt(request.getParameter("rep_listnumber"));
			int board = Integer.parseInt(request.getParameter("board"));
			
			UserDto rep_user = dao.getRepUser(rep_id);
			String rep_userMember = rep_user.getMember(); //신고대상 가입구분
			System.out.println(rep_userMember);
			
			reportDto dto = new reportDto();
			dto.setRep_content(rep_content);
			dto.setRep_id(rep_id);
			dto.setRep_member(rep_userMember);
			dto.setRep_name(rep_name);
			dto.setRep_reason(rep_reason);
			dto.setRep_listnumber(rep_listnumber);
			dto.setBoard(board);
			
			int res = dao.insertRep(dto);
			PrintWriter out =response.getWriter();
			out.println(res);
		
			
		} else if(command.equals("reportList")) {
			List<reportDto> reportList = dao.selectAll();
			request.setAttribute("reportList", reportList);
			RequestDispatcher dispatch = request.getRequestDispatcher("adminboard.jsp");
			dispatch.forward(request, response);

		} else if(command.equals("userList")) {
			List<UserDto> userlist = dao.userAllList();
			request.setAttribute("userlist", userlist);
			RequestDispatcher dispatch = request.getRequestDispatcher("userlistAll.jsp");
			dispatch.forward(request, response);
		} else if(command.equals("updateRole")) {
			String user_id = request.getParameter("user_id");
			String role = request.getParameter("role");
			int res = dao.updateRole(user_id,role);
			PrintWriter out = response.getWriter();
			out.println(res);
		} else if(command.equals("deleteRep")) {
			int rep_no = Integer.parseInt(request.getParameter("rep_no"));
			int rep_listnumber = Integer.parseInt(request.getParameter("rep_listnumber"));
			int board = Integer.parseInt(request.getParameter("board"));

			int res2 = dao.report_delete(rep_no);
			int res =0;
			
			if(board==1) {
				InfoDao dao1 = new InfoDao();
				res = dao1.info_delete(rep_listnumber);
			}else {
				ConDao dao3 = new ConDao();
				res  = dao3.con_delete(rep_listnumber);
			}
			
			if(res+res2==2) {
				String s = "<script type='text/javascript'>"
						+"alert('삭제성공!');"
						+"location.href='reportController.do?command=reportList'"
						+"</script>";
				PrintWriter out = response.getWriter();
				out.println(s);
			}else {
				String s = "<script type='text/javascript'>"
							+"alert('삭제실패!')"
							+"location.href='reportController.do?command=reportList'"
							+"</script>";
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
