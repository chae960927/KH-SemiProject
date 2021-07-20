<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>회원목록</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link rel="stylesheet" href="css/conboard.css">
		<style type="text/css">
			.bt_wrap .on {
			    background: #000;
			    color: #fff;
			    display: inline-block;
			    min-width: 80px;
			    margin-left: 10px;
			    padding: 10px;
			    border: 1px solid #000;
			    border-radius: 2px;
			    font-size: 1.4rem;
			}
		</style>
		<script src="https://kit.fontawesome.com/a076d05399.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script type="text/javascript">
		      $(document).ready(function(){
		         $("#header").load("form/header.jsp");
		         $("#footer").load("form/footer.jsp");
		      });
		 	    
		 	  function openWin(user_id,user_name,role){
		 	  	var url ="updateRoleform.jsp?user_id="+user_id+"&user_name="+user_name+"&role="+role;
		 	    var title ="";
		 	    var prop = "top=200px, left=600px, width=200px, height=230px, resizble=no";
		 	    window.open(url,title,prop);
		 	  }
		      
		      function updateRole(user_id,user_name,role){
		    	  openWin(user_id,user_name,role);
			  }
		</script>
	</head>
	<body>
	  	<div id = "header"></div>
	  	<div class="center">
		  	<div class="board_wrap">
			    <div class="board_title">
			        <strong>관리자게시판</strong>
			        <p>회원목록조회</p>
			    </div>
		    
			    <div class="tab_list">					
			        <a href="reportController.do?command=reportList" class="btn-list" style="vertical-align: middle;">신고게시물</a>
			        <a href="reportController.do?command=userList" class="btn-list" style="vertical-align: middle;">회원 목록 조회</a>
			    </div>
		
			    <div class="board_list_wrap">
			        <div class="board_list">
			            <div class="top" style="width: 100%;">
			                <div class="num" style="width: 10%;">아이디</div>
			                <div class="count" style="width: 10%;">이름</div>
			                <div class="count" style="width: 10%;">닉네임</div>
			                <div class="num" style="width: 15%;">휴대폰번호</div>
			                <div class="num" style="width: 15%;">이메일</div>
			                <div class="count" style="width: 5%;">등급</div>
			                <div class="count" style="width: 10%;">등급변경</div>
			                <div class="count" style="width: 15%;">사업자등록번호</div>
			                <div class="count" style="width: 10%;">탈퇴</div>
			            </div> 
			            <c:choose>
							<c:when test="${empty userlist }">
								<div class="board_title">
							        <p>-----가입한 회원이 없습니다.-----</p>
							    </div>
							</c:when>
							<c:otherwise>
								<c:forEach var="userlist" items="${userlist }">
									<div>
										<div class="num" style="width: 10%;">${userlist.user_id }</div>
										<div class="count" style="width: 10%;">${userlist.user_name}</div>
										<div class="count" style="width: 10%;">${userlist.nick_name}</div>
										<div class="num" style="width: 15%;">${userlist.user_phone }</div>
										<div class="num" style="width: 15%;">${userlist.user_email }</div>
										<div class="count" style="width: 5%;">${userlist.member }</div>
										<div class="count" style="width: 10%;"><button onclick="updateRole('${userlist.user_id}','${userlist.user_name }','${userlist.member}')" >변경</button></div>
										<div class="count" style="width: 15%;">${userlist.biz_num }</div>
										<div class="count" style="width: 10%;"><button type="button" onclick="location.href='PublicController.do?command=userdelete&confirm=admin&user_id=${userlist.user_id}&user_pw=${userlist.user_pw}'">탈퇴</button></div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
			        </div>
			        <div class="board_page">
			            <a href="#" class="bt first"><<</a>
			            <a href="#" class="bt prev"><</a>
			            <a href="#" class="num on">1</a>
			            <a href="#" class="num">2</a>
			            <a href="#" class="num">3</a>
			            <a href="#" class="num">4</a>
			            <a href="#" class="num">5</a>
			            <a href="#" class="bt next">></a>
			            <a href="#" class="bt last">>></a>
			        </div>
				</div>
			</div>
		</div>
	</body>
</html>