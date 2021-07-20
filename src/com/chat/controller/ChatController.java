package com.chat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.chat.dao.ChatDao;
import com.chat.dto.ChatDto;
import com.login.dto.UserDto;

@WebServlet("/chatcontroller.do")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println(command);
		
		ChatDao dao = new ChatDao();
		
		if(command.equals("chatForm")) {
			String chat_num = request.getParameter("chat_num");
			String from_user = request.getParameter("from_user");
			String to_user = request.getParameter("to_user");
			
			request.setAttribute("chat_num", chat_num);
			request.setAttribute("from_user", from_user);
			request.setAttribute("to_user", to_user);
			
			dispatch("chatbox.jsp", request, response);
		} else if(command.equals("start")) {
			String chat_num = request.getParameter("chat_num");
			String from_user = request.getParameter("from_user");
			String to_user = request.getParameter("to_user");
			
			ChatDto cd = new ChatDto();
			cd.setChat_num(chat_num);
			cd.setFrom_user(from_user);
			cd.setTo_user(to_user);
			
			List<ChatDto> list = dao.selectAll(cd);
		
			JSONObject total = new JSONObject();
			JSONArray arr = new JSONArray();
			JSONObject ob = new JSONObject();
			
			for(int i=0; i<list.size(); i++) {
				ob = new JSONObject();
				
				ob.put("user", list.get(i).getFrom_user());
				ob.put("content", list.get(i).getContent());
				arr.add(ob);
				System.out.println(arr);
			}
			
			total.put("chat",arr);
			String json = total.toJSONString();
			System.out.println(json);
			
			PrintWriter pw = response.getWriter();
			pw.print(json);
			
		}else if(command.equals("send")) {
			String chat_num = request.getParameter("chat_num");
			String from_user = request.getParameter("from_user");
			String to_user = request.getParameter("to_user");
			String content = request.getParameter("content");
			
			ChatDto cdto = new ChatDto();
			cdto.setChat_num(chat_num);
			cdto.setFrom_user(from_user);
			cdto.setTo_user(to_user);
			cdto.setContent(content);
			int res = dao.insert(cdto);
			
			PrintWriter pw = response.getWriter();
			pw.print(res);
		}
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
