package com.calender.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.calender.dao.CalendarDao;
import com.calender.dto.CalendarDto;
import com.google.gson.Gson;
import com.login.dto.UserDto;


@WebServlet("/calendar.do")
public class CalendarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CalendarController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		CalendarDao dao = new CalendarDao();
		System.out.println("command= "+command);
		
		if(command.equals("save")) {
			String cal = request.getParameter("cal_info");
			
			JSONParser jp = new JSONParser();
			JSONObject cal_info = null;
			try {
				cal_info = (JSONObject)jp.parse(cal);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String cal_title = (String) cal_info.get("title");
			String cal_start = (String) cal_info.get("start");
			String cal_end = (String) cal_info.get("end");
			System.out.println(cal_end);
			
			String[] start_date = cal_start.split("T");
			String start_res = start_date[0];
			String[] end_date = cal_end.split("T");
			String end_res = end_date[0];
			System.out.println("end_res : " + end_res);
			
			Date start = Date.valueOf(start_res);
			Date end = Date.valueOf(end_res);
			
			if(start.compareTo(end)<0||start.compareTo(end)==0) {//start가 end 보다 앞 날짜일때
				UserDto user = (UserDto) request.getSession().getAttribute("user");
				String cal_user = user.getUser_id();
				
				CalendarDto dto = new CalendarDto(cal_title, cal_start, cal_end, cal_user);
				
				int res = dao.insert(dto);
				
				PrintWriter pw = response.getWriter();
				pw.print(res);
			}else { //start가 end보다 뒷날짜일때
				int res=-1;
				PrintWriter pw = response.getWriter();
				pw.print(res);
			}
		}else if(command.equals("select")){
			
			List<CalendarDto> list = new ArrayList<CalendarDto>();
			UserDto user = (UserDto) request.getSession().getAttribute("user");
			String id = user.getUser_id();

			
			list = dao.select(id);
			
			String json = new Gson().toJson(list);
			
			PrintWriter pw = response.getWriter();
			pw.print(json);
			System.out.println(json);
			request.setAttribute("list", list);
			
		}else if(command.equals("update")) {
			
			String cal = request.getParameter("cal_info");
			String b_cal = request.getParameter("info");
			
			JSONParser jp = new JSONParser();
			JSONObject cal_info = null;
			JSONObject info = null;
			try {
				cal_info = (JSONObject)jp.parse(cal);
				info = (JSONObject)jp.parse(b_cal);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String b_title = (String) info.get("b_title");
			String b_start = (String) info.get("b_start");
			String b_end = (String) info.get("b_end");
			
			CalendarDto before = new CalendarDto(b_title, b_start,b_end);
			int num = dao.selectOne(before);
			
			String cal_title = (String) cal_info.get("title");
			String cal_start = (String) cal_info.get("start");
			String cal_end = (String) cal_info.get("end");
			System.out.println("cal_start: " + cal_start);
			
			CalendarDto dto = new CalendarDto(cal_title, cal_start, cal_end);
			
			String[] start_date = cal_start.split("T");
			String start_res = start_date[0];
			String[] end_date = cal_end.split("T");
			String end_res = end_date[0];
			System.out.println("end_res: " + end_res);
			
			Date start = Date.valueOf(start_res);
			Date end = Date.valueOf(end_res);
			
			if(start.compareTo(end)<0||start.compareTo(end)==0) {//start가 end 보다 앞 날짜일때
				int res = dao.update(dto,num);
			
				PrintWriter pw = response.getWriter();
				pw.print(res);
			}else {
				int res=-1;
				
				PrintWriter pw = response.getWriter();
				pw.print(res);
			}
			
		}else if(command.equals("delete")) {
			
			String b_cal = request.getParameter("info");
			
			JSONParser jp = new JSONParser();
			JSONObject info = null;
			try {
				info = (JSONObject)jp.parse(b_cal);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String b_title = (String) info.get("b_title");
			String b_start = (String) info.get("b_start");
			System.out.println("b_start : " + b_start);
			String b_end = (String) info.get("b_end");
			
			CalendarDto before = new CalendarDto(b_title, b_start, b_end);
			System.out.println(before.getCal_title());
			int res = dao.delete(before);
			System.out.println(res);
			
			PrintWriter pw = response.getWriter();
			pw.print(res);
		}else if(command.equals("calendar")) {
			response.sendRedirect("calendar.jsp");
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

















