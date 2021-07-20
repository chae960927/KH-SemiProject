<%@page import="java.util.List"%>
<%@page import="com.review.controller.ReviewController"%>
<%@page import="com.review.dto.ReviewDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>리뷰 등록 컨트롤러</title>
	</head>
	<body>
		<%
			String command = request.getParameter("command");
			System.out.println("[command: " + command + "]");
			
			ReviewDto dto = new ReviewDto();
			ReviewController biz = new ReviewController();
			
			if(command.equals("insert")) {
				String review_writer = request.getParameter("review_writer");
				int review_num = Integer.parseInt(request.getParameter("score"));
				int cons_no = Integer.parseInt(request.getParameter("con_no"));

				dto.setView_writer(review_writer);
				dto.setView_num(review_num);
				dto.setCons_no(cons_no);
				
				int res = biz.insert_rev(dto);
				
				if(res > 0) {
					String msg = "리뷰를 등록했습니다.";
					request.setAttribute("result", msg);
					response.sendRedirect("ConController.do?command=detail&con_no=" + cons_no);
				} else {
					String msg = "리뷰를 등록하지 못했습니다.";
					request.setAttribute("result", msg);
					response.sendRedirect("ConController.do?command=detail&con_no=" + cons_no);
				}
			}
		%>
	</body>
</html>