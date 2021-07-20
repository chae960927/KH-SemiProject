package com.cons.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cons.dto.ConDto;
import com.pagging.Paging;
import com.review.controller.ReviewController;
import com.review.dto.ReviewDto;
import com.cons.dao.ConDao;


@WebServlet("/ConController.do")
public class ConController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ConController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		int page = 1;
		
		ConDao dao = new ConDao();
		
		if(command.equals("list")) {
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int count = dao.getAllCount();
			
			Paging pg = new Paging();
			pg.setPage(page);
			pg.setTotalCount(count);
			
			List<ConDto> con_list = dao.con_selectAll(page);
			
			request.setAttribute("con_list", con_list);
			request.setAttribute("paging", pg);
			
			dispatch("conboard.jsp", request, response);
			
		} else if(command.equals("search")) {
			String search = request.getParameter("search");
			System.out.println("search: " + search);
			
			List<ConDto> search_list = dao.selectSearch(search);
			request.setAttribute("con_list", search_list);
			dispatch("conboard.jsp", request, response);
			
		} else if(command.equals("detail")) {
			int con_no = Integer.parseInt(request.getParameter("con_no"));
			ConDto con_dto = dao.con_viewDetail(con_no);
			
			ReviewController biz = new ReviewController();
			List<ReviewDto> list = biz.selectAll();
			
			if(con_dto == null) {
				jsResponse("상세보기 실패", "ConController.do?command=list", response);
				
			} else {
				//값이 null이 아니고 조회수 증가 값이 제대로 넘어왔을 경우
				request.setAttribute("con_dto", con_dto);
				request.setAttribute("review_list", list);
				dispatch("conboardview.jsp", request, response);
			}
		
		} else if(command.equals("writeform")) {
			response.sendRedirect("conboardwrite.jsp");
	
		} else if(command.equals("write")) {
			String con_writer = request.getParameter("con_writer");
			String con_title = request.getParameter("con_title");
			String con_content = request.getParameter("con_content");
			
			ConDto dto = new ConDto();
			dto.setcon_writer(con_writer);
			dto.setcon_title(con_title);
			dto.setcon_content(con_content);
			
			int res = dao.con_insert(dto);
			
			if(res > 0) {
				jsResponse("글을 작성했습니다.", "ConController.do?command=list", response);
			} else {
				dispatch("ConController.do?command=writeform", request, response);
			}
			
		} else if(command.equals("updateform")) {
			int con_no = Integer.parseInt(request.getParameter("con_no"));
			ConDto con_dto = dao.con_selectOne(con_no);
			request.setAttribute("con_dto", con_dto);
			dispatch("conboardedit.jsp", request, response);
			
		} else if(command.equals("update")) {
			String con_title =	request.getParameter("con_title");
			String con_content = request.getParameter("con_content");
			int con_no = Integer.parseInt(request.getParameter("con_no"));
			
			ConDto dto = new ConDto();
			dto.setcon_title(con_title);
			dto.setcon_content(con_content);
			dto.setcon_no(con_no);
			
			int res = dao.con_update(dto);
			
			if(res > 0) {
				jsResponse("글을 수정했습니다.", "ConController.do?command=detail&con_no=" + con_no, response);
			} else {
				jsResponse("글 수정 실패", "ConController.do?commnad=updateform&con_no=" + con_no, response );
			}
			
		} else if(command.equals("delete")) {
			int con_no = Integer.parseInt(request.getParameter("con_no"));
			
			int con_res = dao.con_delete(con_no);
			
			if(con_res > 0) {
				jsResponse("글을 삭제했습니다.", "ConController.do?command=list", response);
			} else {
				jsResponse("글 삭제 실패", "ConController.do?command=detail&con_no=" + con_no, response);
			}
		} else if(command.equals("myself")) {
			String user_id = request.getParameter("user_id");
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int count = dao.getAllCount();
			
			Paging pg = new Paging();
			pg.setPage(page);
			pg.setTotalCount(count);
			
			List<ConDto> con_list = dao.selectMyself(page, user_id);
			
			request.setAttribute("con_list", con_list);
			request.setAttribute("paging", pg);
			
			dispatch("conboard.jsp", request, response);
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
