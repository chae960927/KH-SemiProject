package com.info.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.info.dto.InfoDto;
import com.pagging.Paging;
import com.cons.dto.ConDto;
import com.info.dao.InfoDao;


@WebServlet("/InfoController.do")
public class InfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public InfoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		int page = 1;
		
		InfoDao dao = new InfoDao();
		
		if(command.equals("list")) {
			
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int count = dao.getAllCount();
			Paging pg = new Paging();
			pg.setPage(page);
			pg.setTotalCount(count);
			
			List<InfoDto> info_list = dao.info_selectAll(page);
			
			request.setAttribute("info_list", info_list);
			request.setAttribute("paging", pg);
			
			dispatch("infoboard.jsp", request, response);
			
		} else if(command.equals("search")) {
			String search = request.getParameter("search");
			System.out.println("search: " + search);
			List<InfoDto> search_list = dao.selectSearch(search);
			request.setAttribute("info_list", search_list);
			dispatch("infoboard.jsp", request, response);
			
		} else if(command.equals("detail")) {
			int info_no = Integer.parseInt(request.getParameter("info_no"));
			
			InfoDto info_dto = dao.info_viewDetail(info_no);
			
			if(info_dto == null) {
				jsResponse("상세보기 실패", "InfoController.do?command=list", response);
				
			} else {
				//값이 null이 아니고 조회수 증가 값이 제대로 넘어왔을 경우
				request.setAttribute("info_dto", info_dto);
				dispatch("infoboardview.jsp", request, response);
			}
		
		} else if(command.equals("writeform")) {
			response.sendRedirect("infoboardwrite.jsp");
	
		} else if(command.equals("write")) {
			String info_writer = request.getParameter("info_writer");
			String info_title = request.getParameter("info_title");
			String info_content = request.getParameter("info_content");
			
			InfoDto dto = new InfoDto();
			dto.setInfo_writer(info_writer);
			dto.setInfo_title(info_title);
			dto.setInfo_content(info_content);
			
			int res = dao.info_insert(dto);
			
			if(res > 0) {
				jsResponse("글을 작성했습니다.", "InfoController.do?command=list", response);
			} else {
				dispatch("InfoController.do?command=writeform", request, response);
			}
			
		} else if(command.equals("updateform")) {
			int info_no = Integer.parseInt(request.getParameter("info_no"));
			InfoDto info_dto = dao.info_selectOne(info_no);
			request.setAttribute("info_dto", info_dto);
			dispatch("infoboardedit.jsp", request, response);
			
		} else if(command.equals("update")) {
			String info_title =	request.getParameter("info_title");
			String info_content = request.getParameter("info_content");
			int info_no = Integer.parseInt(request.getParameter("info_no"));
			
			InfoDto dto = new InfoDto();
			dto.setInfo_title(info_title);
			dto.setInfo_content(info_content);
			dto.setInfo_no(info_no);
			
			int res = dao.info_update(dto);
			
			if(res > 0) {
				jsResponse("글을 수정했습니다.", "InfoController.do?command=detail&info_no=" + info_no, response);
			} else {
				jsResponse("글 수정 실패", "InfoController.do?commnad=updateform&info_no=" + info_no, response);
			}
			
		} else if(command.equals("delete")) {
			int info_no = Integer.parseInt(request.getParameter("info_no"));
			
			int info_res = dao.info_delete(info_no);
			
			if(info_res > 0) {
				jsResponse("글을 삭제했습니다.", "InfoController.do?command=list", response);
			} else {
				jsResponse("글 삭제 실패", "InfoController.do?command=detail&info_no=" + info_no, response);
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
			
			List<InfoDto> info_list = dao.selectMyself(page, user_id);
			
			request.setAttribute("info_list", info_list);
			request.setAttribute("paging", pg);
			
			dispatch("infoboard.jsp", request, response);
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
