<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>답변 달기</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link rel="stylesheet" href="css/boardwrite.css">
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
		</script>
	</head>
	<body>
		<% String sup_no = String.valueOf(request.getAttribute("sup_no")); %>
		<div id = "header"></div>
	    <div class="board_wrap">
			<div class="board_title">
				<strong>고객문의</strong>
				<p><%=sup_no %>번 문의에 대해 답변을 작성합니다.</p>
			</div>
			<form action="SupController.do?command=answer" method = "post">
				<input type="hidden" name="sup_no" value="<%=sup_no %>">
		        <div class="board_write_wrap">
		            <div class="board_write">
		                <div class="title">
		                    <dl>
		                        <dt>제목</dt>
		                        <dd><input type="text" name="sup_title" placeholder="제목 입력"></dd>
		                    </dl>
		                </div>
		                <div class="info">
		                    <dl>
		                        <dt>작성자</dt>
		                        <dd><input type="text" name="sup_writer" placeholder="작성자 입력" value="${user.user_id }"></dd>
		                    </dl>
		                </div>
		                <div class="cont">
		                    <textarea placeholder="내용 입력" name="sup_content"></textarea>
		                </div>
		            </div>
		        </div>
				<div class="bt_wrap">
					<input type="submit" class="on" value="등록">
					<input type="button" class="on" onclick="location.href='SupController.do?command=list'" value="취소">
				</div>
			</form>
	    </div>
	</body>
</html>