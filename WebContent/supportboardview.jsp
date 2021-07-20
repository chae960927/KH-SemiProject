<%@page import="com.info.dto.InfoDto"%>
<%@page import="com.login.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>고객문의 글 상세</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link rel="stylesheet" href="css/boardedit.css">
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
		<div id = "header"></div>
	  	<div class="board_wrap">
		    <div class="board_title">
		        <strong>고객지원</strong>
		        <p>${sup_dto.sup_writer }님의 문의 입니다.</p>
		    </div>
		    <div class="board_write_wrap">
		        <div class="board_write">
		            <div class="title">
		                <dl>
		                    <dt>제목</dt>
		                    <dd><input type="text" placeholder="제목 입력" value="${sup_dto.sup_title }" readonly="readonly"></dd>
		                </dl>
		            </div>
		            <div class="info">
		                <dl>
		                    <dt>작성자</dt>
		                    <dd><input type="text" placeholder="작성자 입력" value="${sup_dto.sup_writer }" readonly="readonly"></dd>
		                </dl>
		                <dl>
		                    <dt>비밀번호</dt>
		                    <dd><input type="text" placeholder="비밀번호 입력" value="1234" readonly="readonly"></dd>
		                </dl>
		            </div>
		            <div class="cont">
		                <textarea placeholder="내용 입력" readonly="readonly">${sup_dto.sup_content }</textarea>
		            </div>
		        </div>
		    </div>
		    <div class="bt_wrap">
		    	<c:choose>
		    		<c:when test="${user.user_id == null || user.user_id != sup_dto.sup_writer }">
						<input type="button" class="on" value="수정" disabled>
		            	<input type="button" class="on" value="삭제" disabled>
		            	<c:if test="${user.member == '관리자' }">
			            	<input type="button" class="on" onclick="location.href='SupController.do?command=answerform&sup_no=${sup_dto.sup_no }'" value="답변">
			            </c:if>
					</c:when>
					<c:otherwise>
			            <input type="button" class="on" onclick="location.href='SupController.do?command=updateform&sup_no=${sup_dto.sup_no }'" value="수정">
			            <input type="button" class="on" onclick="location.href='SupController.do?command=delete&group_no=${sup_dto.group_no }&sup_no=${sup_dto.sup_no }'" value="삭제">
		            </c:otherwise>
		        </c:choose> 
				<input type="button" class="on" onclick="location.href='SupController.do?command=list'" value="목록">
			</div>
		</div>
	</body>
</html>