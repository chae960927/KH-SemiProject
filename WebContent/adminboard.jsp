<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>관리자게시판</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link rel="stylesheet" href="css/noticeboard.css">
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
		<div class="center">
			<div class="board_wrap">
			    <div class="board_title">
			        <strong>관리자게시판</strong>
			        <p>신고된 게시글</p>
			        
			    </div>
			    <div class="tab_list">					
			        <a href="reportController.do?command=reportList" class="btn-list" style="vertical-align: middle;">신고게시물</a>
			        <a href="reportController.do?command=userList" class="btn-list" style="vertical-align: middle;">회원 목록 조회</a>
			    </div>
		
			    <div class="board_list_wrap">
			        <div class="board_list">
			            <div class="top" style="width: 100%;">
			                <div class="num" style="width: 5%;">번호</div>
			                <div class="writer" style="width: 10%;">신고자</div>
			                <div class="writer" style="width: 10%;">신고아이디</div>
			                <div class="writer" style="width: 20%;">신고이유</div>
			                <div class="writer" style="width: 10%;">신고일</div>
			                <div class="num" style="width:10%">게시판 유형</div>
			                <div class="num" style="width: 25%;">게시글 내용</div>
			                <div class="num" style="width:10%">삭제</div>
			            </div> 
			            <c:choose>
							<c:when test="${empty reportList }">
								<div class="board_title">
							        <p>-----작성된 글이 없습니다.-----</p>
							    </div>
							</c:when>
							<c:otherwise>
								<c:forEach var="report" items="${reportList }">
									<div style="width: 100%;">
										<div class="num" style="width: 5%;">${report.rep_no }</div>
										<div class="writer" style="width: 10%;">${report.rep_name}</div>
										<div class="writer" style="width: 10%;">${report.rep_id}</div>
										<div class="writer" style="width: 20%;">${report.rep_reason}</div>
										<div class="writer" style="width: 10%;">${report.rep_regdate}</div>
										<div class="num" style="width:10%">${report.board eq 1?'정보':'컨설팅' }</div>
										<div class="num" style="width: 25%;">
											<a href='rep_detail.jsp' onclick="window.open(this.href, '', 'width=800, height=600'); return false;">게시글 내용 상세</a>
											<input type="hidden" id="rep_content" value='${report.rep_content}'>
										</div>
										<div class="num" style="width:10%">
											<button onclick="location.href='reportController.do?command=deleteRep&rep_no=${report.rep_no}&rep_listnumber=${report.rep_listnumber}&board=${report.board}'">삭제 </button>
										</div>
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