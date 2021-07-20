package com.mypage_public.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.login.dao.UserDao;
import com.login.dto.UserDto;
import com.mypage_public.dao.MypagePublicDao;
import com.mypage_public.dto.MypagePublicDto;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/PublicController.do")
public class MypagePublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MypagePublicController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		MypagePublicDao dao = new MypagePublicDao();
		
		if(command.equals("mypage")) {
			String member = request.getParameter("member");
			if(member.equals("개인")) {
				String user_id = request.getParameter("user_id");
				
				MypagePublicDto dto = dao.selectOne(user_id);
				
				request.setAttribute("user_list", dto);
				dispatch("mypage_public.jsp", request, response);
			} else if(member.equals("기업")) {
				String user_id = request.getParameter("user_id");
				
				MypagePublicDto dto = dao.selectOne(user_id);
				
				request.setAttribute("user_list", dto);
				dispatch("mypage_company.jsp", request, response);
			} else if(member.equals("관리자")) {
				response.sendRedirect("reportController.do?command=reportList");
			}
		} else if(command.equals("userupdate")) {
			String member = request.getParameter("member");
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			String user_name = request.getParameter("user_name");
			String nick_name = request.getParameter("nick_name");
			String user_phone = request.getParameter("user_phone");
			String user_email = request.getParameter("user_email");
			String user_addr = request.getParameter("user_addr");
			String user_addr_de = request.getParameter("user_addr_de");
			
			MypagePublicDto dto = new MypagePublicDto();
			dto.setUser_id(user_id);
			dto.setUser_name(user_name);
			dto.setNick_name(nick_name);
			dto.setUser_phone(user_phone);
			dto.setUser_email(user_email);
			dto.setUser_addr(user_addr);
			dto.setUser_addr_de(user_addr_de);
			
			int res = dao.user_update(dto);
			
			if(res > 0) {
				dto = dao.selectOne(user_id);
				jsResponse("개인정보를 수정했습니다.", "PublicController.do?command=mypage&member=" + dto.getMember() + "&user_id=" + user_id, response);
			} else {
				dispatch("PublicController.do?command=mypage&member=" + member + "&user_id=" + user_id, request, response);
			}
		} else if(command.equals("userdelete")) {
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			System.out.println(user_id + ", " + user_pw);
			int res = dao.deleteUser(user_id, user_pw);
			
			if(res > 0) {
				jsResponse2("성공적으로 탈퇴했습니다.", "index.jsp", request, response);
			} else {
				jsResponse("탈퇴 실패", "index.jsp", response);
			}
		} else if(command.equals("pwupdate")) {
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			System.out.println(user_id + ", " + user_pw);
			
			int res = dao.updatePw(user_id, user_pw);
			if(res > 0) {
				jsResponse("성공적으로 변경했습니다. 다시 로그인해주세요.", "login.do?command=logout", response);
			} else {
				jsResponse("비밀번호 변경 실패", "index.jsp", response);
			}
		} else if(command.equals("imgUploadForm")) {
			String user_id = request.getParameter("user_id");
			request.setAttribute("user_id", user_id);
			dispatch("imgUpload.jsp", request, response);
		} else if(command.equals("imgUpload")) {
			int fileSize = 1024*1024*3; //3mb로 파일 크기를 제한
			ServletContext context = getServletContext();
			String uploadPath = context.getRealPath("/user_img");
			System.out.println("프로필 업로드 경로: " + uploadPath);
			
			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8",new DefaultFileRenamePolicy());
			String user_id = multi.getParameter("user_id");
			String user_img = multi.getFilesystemName("user_img");
			System.out.println("user_img: " + user_img);
			String fullPath = uploadPath + File.separator + user_img;
			System.out.println("user_id: " + user_id + ", fullPath: " + fullPath);
			
			int res = dao.imgUpload(user_id, user_img);
			if(res > 0) {
				HttpSession user = request.getSession();
				user.setAttribute("user_img", user_img);
				user.setAttribute("login","login");
				jsResponse3("프로필을 업로드 했습니다.", "PublicController.do?command=mypage&member=개인&user_id=" + user_id, response);
			} else {
				jsResponse3("프로필을 업로드하지 못했습니다.", "PublicController.do?command=mypage&member=개인&user_id=" + user_id, response);
			}
		} else if(command.equals("userdelete")) {
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			System.out.println(user_id + ", " + user_pw);
			int res = dao.deleteUser(user_id, user_pw);
			String confirm = request.getParameter("confirm");
			
			if(confirm.equals("admin")) {
				if(res > 0) {
					jsResponse("탈퇴완료","reportController.do?command=userList",response);		
				}else {
					jsResponse("탈퇴 실패", "reportController.do?command=userList", response);

				}
				
			};
			
			if(res > 0) {
				jsResponse2("성공적으로 탈퇴했습니다.", "index.jsp", request, response);
			} else {
				jsResponse("탈퇴 실패", "index.jsp", response);
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
	
	private void jsResponse2(String msg, String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession user = request.getSession();
		user.invalidate();
		String s = "<script type = 'text/javascript'>"
					+ "alert('" + msg + "');"
					+ "opener.document.location.href='" + url + "';"
					+ "self.close();"   // 창닫기
					+ "</script>";
		
		PrintWriter out = response.getWriter();
		out.print(s);
	}
	
	private void jsResponse3(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type = 'text/javascript'>"
					+ "alert('" + msg + "');"
					+ "opener.document.location.href='" + url + "';"
					+ "self.close();"   // 창닫기
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
