package com.sup.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sup.dto.SupDto;
import com.notice.dto.NoticeDto;
import com.pagging.Paging;
import com.sup.dao.SupDao;


@WebServlet("/SupController.do")
public class SupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SupController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		int page = 1;
		
		SupDao dao = new SupDao();
		
		if(command.equals("list")) {
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int count = dao.getAllCount();
			
			Paging pg = new Paging();
			pg.setPage(page);
			pg.setTotalCount(count);
			
			List<SupDto> sup_list = dao.sup_selectAll(page);
			
			request.setAttribute("sup_list", sup_list);
			request.setAttribute("paging", pg);
			
			dispatch("supportboard.jsp", request, response);
			
		} else if(command.equals("search")) {
			String search = request.getParameter("search");
			System.out.println("search: " + search);
			
			List<SupDto> search_list = dao.selectSearch(search);
			request.setAttribute("sup_list", search_list);
			dispatch("supportboard.jsp", request, response);
			
		} else if(command.equals("detail")) {
			int sup_no = Integer.parseInt(request.getParameter("sup_no"));
			
			SupDto sup_dto = dao.sup_viewDetail(sup_no);
			
			if(sup_dto == null) {
				jsResponse("상세보기 실패", "SupController.do?command=list", response);
				
			} else {
				//값이 null이 아니고 조회수 증가 값이 제대로 넘어왔을 경우
				request.setAttribute("sup_dto", sup_dto);
				dispatch("supportboardview.jsp", request, response);
			}
		
		} else if(command.equals("writeform")) {
			response.sendRedirect("supportboardwrite.jsp");
	
		} else if(command.equals("write")) {
			String sup_writer = request.getParameter("sup_writer");
			String sup_title = request.getParameter("sup_title");
			String sup_content = request.getParameter("sup_content");
			
			SupDto dto = new SupDto();
			dto.setSup_writer(sup_writer);
			dto.setSup_title(sup_title);
			dto.setSup_content(sup_content);
			
			int res = dao.sup_insert(dto);
			
			if(res > 0) {
				jsResponse("글을 작성했습니다.", "SupController.do?command=list", response);
			} else {
				dispatch("Supcontroller.do?command=writeform", request, response);
			}
			
		} else if(command.equals("updateform")) {
			int sup_no = Integer.parseInt(request.getParameter("sup_no"));
			SupDto sup_dto = dao.sup_selectOne(sup_no);
			request.setAttribute("sup_dto", sup_dto);
			dispatch("supportboardedit.jsp", request, response);
			
		} else if(command.equals("update")) {
			String sup_title =	request.getParameter("sup_title");
			String sup_content = request.getParameter("sup_content");
			int sup_no = Integer.parseInt(request.getParameter("sup_no"));
			
			SupDto dto = new SupDto();
			dto.setSup_title(sup_title);
			dto.setSup_content(sup_content);
			dto.setSup_no(sup_no);
			
			int res = dao.sup_update(dto);
			
			if(res > 0) {
				jsResponse("글을 수정했습니다.", "SupController.do?command=detail&sup_no=" + sup_no, response);
			} else {
				jsResponse("글 수정 실패", "SupController.do?commnad=updateform&sup_no=" + sup_no, response );
			}
			
		} else if(command.equals("delete")) {
			int sup_no = Integer.parseInt(request.getParameter("sup_no"));
			int group_no = Integer.parseInt(request.getParameter("group_no"));
			
			int res = dao.sup_delete(group_no);
			
			if(res > 0) {
				jsResponse("글을 삭제했습니다.", "SupController.do?command=list", response);
			} else {
				jsResponse("글 삭제 실패", "SupController.do?command=detail&sup_no=" + sup_no, response);
			}
		} else if(command.equals("answerform")) {
			int sup_no = Integer.parseInt(request.getParameter("sup_no"));
			request.setAttribute("sup_no", sup_no);
			dispatch("answerwrite.jsp?sup_no=" + sup_no, request, response);
			
		} else if(command.equals("answer")) {
			int sup_no = Integer.parseInt(request.getParameter("sup_no"));
			SupDto parent = dao.sup_selectOne(sup_no);
			
			String sup_title = request.getParameter("sup_title");
			String sup_writer = request.getParameter("sup_writer");
			String sup_content = request.getParameter("sup_content");
			
			int parent_group_no = parent.getGroup_no();
			int parent_group_sq = parent.getGroup_sq();
			
			//글 등록 전 업데이트 : 새로운 답변을 달 경우 이전에 달린 답변보다 위에 위치해야하기 때문
			int updateRes = dao.updateAnswer(parent_group_no, parent_group_sq);
			
			if(updateRes > 0) {
				System.out.println("순서 변경 성공");
				
			} else {
				System.out.println("순서 변경 실패 or 변경할 글이 없음");
			}
			
			//insert
			SupDto child = new SupDto();
			child.setSup_writer(sup_writer);
			child.setSup_title(sup_title);
			child.setSup_content(sup_content);
			
			int res = dao.insertAnswer(parent, child);
			
			if(res > 0) {
				jsResponse("답변을 작성했습니다.", "SupController.do?command=list", response);
			} else {
				dispatch("Supcontroller.do?command=answerform", request, response);
			}
		} else if(command.equals("sup_list")) {
			String user_id = request.getParameter("user_id");
			List<SupDto> sup_list = dao.selectMyself(user_id);
			
			request.setAttribute("sup_list", sup_list);
			dispatch("supportList.jsp", request, response);
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
