package com.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypage_public.dao.MypagePublicDao;
import com.mypage_public.dto.MypagePublicDto;
import com.pay.dao.PayDao;
import com.pay.dto.PayDto;

@WebServlet("/PayController.do")
public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PayController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		PayDao dao = new PayDao();
		
		if(command.equals("payForm")) {
			//결제 선택 화면으로 넘기기
			String pay_user = request.getParameter("pay_user");
			String pay_company = request.getParameter("pay_company");
			
			request.setAttribute("pay_user", pay_user);
			request.setAttribute("pay_company", pay_company);
			dispatch("payForm.jsp", request, response);
			
		} else if(command.equals("pay")) {
			String pay_user = request.getParameter("pay_user");
			String pay_company = request.getParameter("pay_company");
			String pay_name = request.getParameter("pay_name");
			int pay_money = Integer.parseInt(request.getParameter("pay_money"));
			String pay_info = request.getParameter("pay_info");
			
			//결제 화면으로 넘기기
			PayDto pay_list = new PayDto();
			pay_list.setPay_user(pay_user);
			pay_list.setPay_company(pay_company);
			pay_list.setPay_name(pay_name);
			pay_list.setPay_money(pay_money);
			pay_list.setPay_info(pay_info);
			
			request.setAttribute("pay_list", pay_list);
			dispatch("pay.jsp", request, response);
		} else if(command.equals("pay_success")) {
			String pay_no = request.getParameter("pay_no");
			String pay_user = request.getParameter("pay_user");
			String pay_company = request.getParameter("pay_company");
			String pay_name = request.getParameter("pay_name");
			int pay_money = Integer.parseInt(request.getParameter("pay_money"));
			String pay_info = request.getParameter("pay_info");
			System.out.println("pay_no: " + pay_no + ", pay_money : " + pay_money);
			
			PayDto pay_list = new PayDto();
			pay_list.setPay_no(pay_no);
			pay_list.setPay_user(pay_user);
			pay_list.setPay_company(pay_company);
			pay_list.setPay_name(pay_name);
			pay_list.setPay_money(pay_money);
			pay_list.setPay_info(pay_info);
			
			int res = dao.insert_pay(pay_list);
			
			if(res > 0) {
				System.out.println("결제정보 입력 성공");
			} else {
				System.out.println("결제정보 입력 실패");
			}
		} else if(command.equals("success")) {
			String pay_user = request.getParameter("pay_user");
			MypagePublicDao userdao = new MypagePublicDao();
			MypagePublicDto user = userdao.selectOne(pay_user);
			System.out.println(user.getMember());
			jsResponse("결제에 성공했습니다.", "PublicController.do?command=mypage&member=" + user.getMember() + "&user_id=" + user.getUser_id(), response);
		} else if(command.equals("pay_list")) {
			//결제 목록 출력하기(팝업으로 띄울거임. 일종의 채팅방 역할)
			String pay_user = request.getParameter("pay_user");
			System.out.println("pay_user: " + pay_user);
			List<PayDto> pay_list = dao.select_pay(pay_user);
			
			request.setAttribute("pay_list", pay_list);
			dispatch("payList.jsp", request, response);
		} else if(command.equals("pay_list_com")) {
			String pay_company = request.getParameter("pay_company");
			MypagePublicDao userdao = new MypagePublicDao();
			List<PayDto> company_list = userdao.selectCompany(pay_company);
			
			request.setAttribute("company_list", company_list);
			dispatch("companyList.jsp", request, response);
		}
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type = 'text/javascript'>"
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
