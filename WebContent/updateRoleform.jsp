<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
 
<%@ page import= "java.util.List" %>
<%@ page import= "com.login.dto.UserDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>changeRole</title>
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#submit").click(function(){
					var user_id = $("#user_id").val();
					var role = $("select option:selected").text();
					//alert(role);
					$.ajax({
						type: "POST",
						url:"reportController.do?command=updateRole&user_id="+user_id+"&role="+role,
						data : $("form").serialize(),
						async: false,
						success: function(data){
							if(data>0){
								alert("등급변경 성공!")
								close();
							}
						},
						error : function(){
							alert("실패");
							close();
			
						}
					});
				});
			});
		</script>
	</head>
	<body>
		<h1>등급변경 </h1>
		<% 
			String user_id = request.getParameter("user_id"); 
			String user_name = request.getParameter("user_name"); 
			String role= request.getParameter("role"); 
		
		%>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="updateRole">
			<input type="hidden" id="user_id" value="<%=user_id%>">
			<table border="1">
				<tr>
					<th>Id</th>
					<td><%=user_id %></td>
				
				</tr>
				<tr>
					<th>Name</th>
					<td><%=user_name %></td>
				</tr>
				<tr>
					<th>Role</th>
					<td>
					<select name="myrole">
							<option value="개인" <%=role.equals("개인")?"selected":"" %>>개인</option>
							<option value="관리자"<%=role.equals("관리자")?"selected":"" %>>관리자</option>
							<option value="기업"<%=role.equals("기업")?"selected":"" %>>기업</option>
	
					</select>
					
					</td>
				</tr>
				<tr>
					<td><input id="submit" type="button" value="변경완료"></td>
					<td><input id="cancel" type="button" value="취소" onclick="self.close()"></td>
				</tr>
			</table>
		</form>
	</body>
</html>