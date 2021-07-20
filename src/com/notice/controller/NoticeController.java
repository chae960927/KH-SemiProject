package com.notice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.notice.dto.NoticeDto;
import com.pagging.Paging;
import com.cons.dto.ConDto;
import com.notice.dao.NoticeDao;


@WebServlet("/NoticeController.do")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		int page = 1;
		
		NoticeDao dao = new NoticeDao();
		
		if(command.equals("list")) {
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int count = dao.getAllCount();
			
			Paging pg = new Paging();
			pg.setPage(page);
			pg.setTotalCount(count);
			
			List<NoticeDto> notice_list = dao.notice_selectAll(page);
			
			request.setAttribute("notice_list", notice_list);
			request.setAttribute("paging", pg);
			
			dispatch("noticeboard.jsp", request, response);
			
		} else if(command.equals("search")) {
			String search = request.getParameter("search");
			System.out.println("search: " + search);
			
			List<NoticeDto> search_list = dao.selectSearch(search);
			request.setAttribute("notice_list", search_list);
			dispatch("noticeboard.jsp", request, response);
			
		} else if(command.equals("detail")) {
			int notice_no = Integer.parseInt(request.getParameter("notice_no"));
			NoticeDto notice_dto = dao.notice_viewDetail(notice_no);
			
			if(notice_dto == null) {
				jsResponse("상세보기 실패", "NoticeController.do?command=list", response);
				
			} else {
				//값이 null이 아니고 조회수 증가 값이 제대로 넘어왔을 경우
				request.setAttribute("notice_dto", notice_dto);
				dispatch("noticeboardview.jsp", request, response);
			}
		
		} else if(command.equals("writeform")) {
			response.sendRedirect("noticeboardwrite.jsp");
	
		} else if(command.equals("write")) {
			String notice_writer = request.getParameter("notice_writer");
			String notice_title = request.getParameter("notice_title");
			String notice_content = request.getParameter("notice_content");
			String notice_cat = request.getParameter("notice_cat");
			
			NoticeDto dto = new NoticeDto();
			dto.setNotice_writer(notice_writer);
			dto.setNotice_cat(notice_cat);
			dto.setNotice_title(notice_title);
			dto.setNotice_content(notice_content);
			
			int res = dao.notice_insert(dto);
			
			if(res > 0) {
				jsResponse("글을 작성했습니다.", "NoticeController.do?command=list", response);
			} else {
				dispatch("NoticeController.do?command=writeform", request, response);
			}
			
		} else if(command.equals("updateform")) {
			int notice_no = Integer.parseInt(request.getParameter("notice_no"));
			NoticeDto notice_dto = dao.notice_selectOne(notice_no);
			request.setAttribute("notice_dto", notice_dto);
			dispatch("noticeboardedit.jsp", request, response);
			
		} else if(command.equals("update")) {
			String notice_title =	request.getParameter("notice_title");
			String notice_content = request.getParameter("notice_content");
			int notice_no = Integer.parseInt(request.getParameter("notice_no"));
			
			NoticeDto dto = new NoticeDto();
			dto.setNotice_title(notice_title);
			dto.setNotice_content(notice_content);
			dto.setNotice_no(notice_no);
			
			int res = dao.notice_update(dto);
			
			if(res > 0) {
				jsResponse("글을 수정했습니다.", "NoticeController.do?command=detail&notice_no=" + notice_no, response);
			} else {
				jsResponse("글 수정 실패", "NoticeController.do?commnad=updateform&notice_no=" + notice_no, response );
			}
			
		} else if(command.equals("delete")) {
			int notice_no = Integer.parseInt(request.getParameter("notice_no"));
			
			int res = dao.notice_delete(notice_no);
			
			if(res > 0) {
				jsResponse("글을 삭제했습니다.", "ConController.do?command=list", response);
			} else {
				jsResponse("글 삭제 실패", "ConController.do?command=detail&con_no=" + notice_no, response);
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
