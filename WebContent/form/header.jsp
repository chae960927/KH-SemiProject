<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="javax.servlet.http.HttpSession"%>
<%@ page import = "com.login.dto.UserDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴바 헤더</title>
<%
String ses = (String) request.getSession().getAttribute("login");
System.out.println(ses); %>
<script type="text/javascript">
		function mypage(member){
			if(member=="개인"){
				location.href="PublicController.do?command=mypage&member=개인&user_id=${user.user_id}";
			}else if(member=="기업"){
				//location.href="calendar.do?command=calendar";
				location.href="PublicController.do?command=mypage&member=기업&user_id=${user.user_id}";
			}else if(member=="관리자"){
				location.href="PublicController.do?command=mypage&member=관리자";
			}
		}
		
		function logout(){
			location.href="login.do?command=logout";
		}
	</script>
</head>
<body>
	<nav>
    <div class="menu">
      <div class="logo">
        <a href="index.jsp">Leporem</a>
      </div>
      <ul>
        <li><a href="index.jsp">Home</a></li>
        <li><a href="InfoController.do?command=list">Board</a></li>
        <li><a href="NoticeController.do?command=list">Support</a></li>
        <% 
			if(ses!=null){
				UserDto user = (UserDto) request.getSession().getAttribute("user");
				String user_img = (String) request.getSession().getAttribute("user_img");
				if(user_img != null) {
					user.setUser_img(user_img);
				}
		%>
			<li>
				<a href="#"onclick="mypage('${user.member}')">
					<img src="user_img/${user.user_img}" width="30px" height="30px" style="border-radius: 30px; vertical-align: middle;">
					${user.nick_name }
				</a>
			</li>
			<li><a href="#" onclick="logout();">로그아웃</a></li>
			<%}else{%>
				  	<li><a href="login.jsp">Login</a></li>
        			<li><a href="join.jsp">Join</a></li>
			<%}%>

      </ul>
    </div>
  </nav>
</body>
</html>