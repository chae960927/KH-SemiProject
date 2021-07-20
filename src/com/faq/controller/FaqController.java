package com.faq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faq.dto.FaqDto;
import com.faq.dao.FaqDao;


@WebServlet("/FaqController.do")
public class FaqController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FaqController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		FaqDao dao = new FaqDao();
		
		if(command.equals("list")) {
			
			List<FaqDto> faq_list = dao.faq_selectAll();
			
			request.setAttribute("faq_list", faq_list);
			
			dispatch("FAQboard.jsp", request, response);
			
		} else if(command.equals("writeform")) {
			response.sendRedirect("faqboardwrite.jsp");
	
		} else if(command.equals("write")) {
			String faq_title = request.getParameter("faq_title");
			String faq_content = request.getParameter("faq_content");
			
			FaqDto dto = new FaqDto();
			dto.setFaq_title(faq_title);
			dto.setFaq_content(faq_content);
			
			int res = dao.faq_insert(dto);
			
			if(res > 0) {
				jsResponse("글을 작성했습니다.", "FaqController.do?command=list", response);
			} else {
				dispatch("FaqController.do?command=writeform", request, response);
			}
			
		} else if(command.equals("update")) {
			String faq_title =	request.getParameter("faq_title");
			String faq_content = request.getParameter("faq_content");
			int faq_no = Integer.parseInt(request.getParameter("faq_no"));
			
			FaqDto dto = new FaqDto();
			dto.setFaq_title(faq_title);
			dto.setFaq_content(faq_content);
			dto.setFaq_no(faq_no);
			
			int res = dao.faq_update(dto);
			
			if(res > 0) {
				jsResponse("글을 수정했습니다.", "FaqController.do?command=list", response);
			} else {
				jsResponse("글 수정 실패", "FaqController.do?commnad=list", response );
			}
			
		} else if(command.equals("delete")) {
			int faq_no = Integer.parseInt(request.getParameter("faq_no"));
			
			int res = dao.faq_delete(faq_no);
			
			if(res > 0) {
				jsResponse("글을 삭제했습니다.", "FaqController.do?command=list", response);
			} else {
				jsResponse("글 삭제 실패", "FaqController.do?command=list", response);
			}
			
		}
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type = 'text/javascript'>"
					+ "alert('" + msg + "');"
					+ "location.href='" + url + "';"
					+ "</script>";
		
		PrintWriter out = response.getWriter();
		out.print(s);
	}

	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
