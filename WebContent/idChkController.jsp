<%@page import="com.join.controller.JoinController"%>
<%@page import="com.join.dao.JoinDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>아이디 중복체트 컨트롤러</title>
	</head>
	<body>
		<%
			String command = request.getParameter("command");
			System.out.println("[command: " + command + "]");
			JoinDao dao = new JoinDao();
			JoinController biz = new JoinController();
			
			if(command.equals("idChk")) {
				String user_id = request.getParameter("user_id");
				String res = biz.idChk(user_id);
				boolean idnotused = true;
				
				if(res != null) {
					idnotused = false;
				}
				response.sendRedirect("idChk.jsp?idnotused=" + idnotused);
			}
		%>
	</body>
</html>