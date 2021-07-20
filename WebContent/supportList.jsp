<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>문의 내역</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link rel="stylesheet" href="css/mypagecomboard.css">
		<script src="https://kit.fontawesome.com/a076d05399.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	</head>
	<body>
		<div class="center">
			<div class="board_wrap">
				<div class="board_title">
		        	<strong>문의 내역</strong>
		    	</div>
		    	<div class="board_list_wrap">
		    		<div class="board_list">
		    			<div class="top">
		                    <div class="num">문의번호</div>
		                    <div class="title">제목</div>
		                    <div class="writer">작성자</div>
		                    <div class="date">작성일</div>
		                    <div class="count">조회</div>
		                </div>
		                <c:choose>
							<c:when test="${empty sup_list }">
								<div>
									-----문의내역이 없습니다.-----
								</div>
							</c:when>
							<c:otherwise>
								<c:forEach var="sup_list" items="${sup_list }">
									<div>
										<div class="num">${sup_list.sup_no }</div>
					                    <div class="title">
					                    	<c:forEach begin="1" end="${sup_list.title_tab }">↳</c:forEach>
											<a href="SupController.do?command=detail&sup_no=${sup_list.sup_no }">${sup_list.sup_title }</a>
					                    </div>
					                    <div class="writer">${sup_list.sup_writer }</div>
					                    <div class="date">${sup_list.sup_regdate }</div>
					                    <div class="count">${sup_list.sup_push }</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
		    		</div>
		    	</div>
			</div>
		</div>
	</body>
</html>