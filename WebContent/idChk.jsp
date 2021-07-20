<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>아이디 중복 체크</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
	</head>
	<body>
		<%
			String idnotused = request.getParameter("idnotused");
			
		%>
		<script type="text/javascript">
			window.onload = function() {
				var id = opener.document.getElementsByName("user_id")[0].value;
				document.getElementsByName("id")[0].value = id;
			}
			
			function confirm(bool) {
				if(bool) {
					//사용가능한 아이디라면
					opener.document.getElementsByName("user_pw")[0].focus();
					opener.document.getElementsByName("user_id")[0].title = "y";
				} else {
					//중복된 아이디라면
					opener.document.getElementsByName("user_id")[0].focus();
				}
				//사용이 완료된 팝업창 닫기
				self.close();
			}
		</script>
		<table border="1">
			<tr>
				<td><input type="text" name="id" readonly="readonly"></td>
			</tr>
			<tr>
				<td><%=idnotused.equals("true")?"사용할 수 있는 아이디 입니다.":"중복된 아이디 입니다." %></td>
			</tr>
			<tr>
				<td><input type="button" value="확인" onclick="confirm(<%=idnotused%>);"></td>
			</tr>
		</table>
	</body>
</html>