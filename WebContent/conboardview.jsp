<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>컨설팅 게시판 글 상세</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link rel="stylesheet" href="css/boardview.css">
		<link rel="stylesheet" href="css/jquery.raty.css">
		<style type="text/css">
			p img {
				max-width: 100% !important;
				height: 400px !important;
			}
			
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
			
			.review {
				background: #000;
			    color: #fff;
			    display: inline-block;
			    min-width: 50px;
			    padding: 5px;
			    border: 1px solid #000;
			    border-radius: 2px;
			    font-size: 1.4rem;
			}
			
			.board_wrap {
				top: 50% !important;
			}
			table {
				font-size: 15px;
			}
		</style>
		<script src="https://kit.fontawesome.com/a076d05399.js"></script>
	    <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	    <script type="text/javascript" src="js/jquery.raty.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#header").load("form/header.jsp");
				$("#footer").load("form/footer.jsp");
				
				if($('#con_writer').val() == $('#write_user').val()) {
					$('#reviewwrite').hide();
				} else {
					if($('#write_user').val() == $('#list_user').val()) {
					    $('#reviewwrite').hide();
					} else {
						
					}
				}
		        
				$("div#star").raty();
		        
		        var view_input = document.getElementsByName("view_num").length;
		        var view_num;
		        var view_writer;
		        for(var i = 0; i < view_input; i++) {
		        	view_writer = document.getElementsByName("view_writer")[i].value;
		        	view_num = document.getElementsByName("view_num")[i].value;
		        	$("div#star_"+view_writer).raty({ readOnly: true, score: view_num });
		        }
			});
			
			function selectReason(con_no){
	    		openWin(2,con_no);
	      	}
			
			function openWin(board,con_no){
		 		var url ="report_write_form.jsp?rep_listnumber=" + con_no + "&board=" + board;
		 	    var title ="";
		 	    var prop = "top=200px, left=600px, width=710px, height=260px, resizble=no";
		 	    window.open(url,title,prop);
		 	}
		</script>
	</head>
	<body>
	  <div id = "header"></div>
	  <div class="board_wrap">
			<div class="board_title">
				<strong>컨설팅 게시판</strong>
				<p>${con_dto.con_writer } 기업의 소개글입니다.</p>
			</div>
			<div class="board_list_wrap">
				<div class="board_view">
					<div class="title">
						${con_dto.con_title }
					</div>
					<div class="info">
						<dl>
							<dt>번호</dt>
							<dd>${con_dto.con_no }</dd>
						</dl>
						<dl>
							<dt>작성자</dt>
							<dd>
								${con_dto.con_writer }
								<input type="hidden" id="con_writer" value="${con_dto.con_writer }">
							</dd>
						</dl>
						<dl>
							<dt>작성일</dt>
							<dd>${con_dto.con_regdate }</dd>
						</dl>
						<dl>
							<dt>조회</dt>
							<dd>${con_dto.con_push }</dd>
						</dl>
					</div>
					<div class="cont">
						${con_dto.con_content }
					</div>
				</div> 		
				<div class="bt_wrap">
					<c:choose>
			    		<c:when test="${user.user_id == null || user.user_id != con_dto.con_writer }">
			    			<c:if test="${user.user_id != null }">
				            	<input type="button" class="on" value="컨설팅" onclick="location.href='PayController.do?command=payForm&pay_user=${user.user_id }&pay_company=${con_dto.con_writer }'">
				            	<input type="button" class="on" value="신고" onclick="selectReason(${con_dto.con_no});">
				            	<input type="hidden" id="rep_name" value="${user.user_name}">
				            	<input type="hidden" id="rep_id" value="${con_dto.con_writer}">
				            	<input type="hidden" id="rep_content" value='${con_dto.con_content}'>
			            	</c:if>
						</c:when>
						<c:otherwise>
				            <input type="button" class="on" onclick="location.href='ConController.do?command=updateform&con_no=${con_dto.con_no }'" value="수정">
				            <input type="button" class="on" onclick="location.href='ConController.do?command=delete&con_no=${con_dto.con_no }'" value="삭제">
			            </c:otherwise>
			        </c:choose> 
					<input type="button" class="on" onclick="location.href='ConController.do?command=list'" value="목록">
				</div>
			</div>
			<div class="board_list_wrap" style="margin-top: 10%;">
				<c:if test="${user.user_id != null }">
					<form action="reviewController.jsp" method="get" style="padding-top: 3%; padding-bottom: 1%;">
						<input type="hidden" name="command" value="insert">
						<input type="hidden" name="review_writer" value="${user.user_id }">
						<input type="hidden" name="con_no" value="${con_dto.con_no }">
						<table style="text-align: center;" id="reviewwrite">
							<col width="100px"><col width="100px"><col width="600px">
							<tr>
								<th>작성자: </th>
								<td>
									${user.user_id }
									<input type="hidden" id="write_user" value="${user.user_id }">
								</td>
								<td><div id="star"></div></td>
								<td><input type="submit" class="review" value="리뷰달기"></td>
							</tr>
							
						</table>
					</form>
				</c:if>
				<table class="board_view" style="text-align: center;" id="reviewlist">
					<tr>
						<th>작성자</th>
						<th>별점</th>
						<th>작성일</th>
					</tr>
					<c:if test="${empty review_list }">
						<tr>
							<td colspan="3">----리뷰가 존재하지 않습니다.----</td>
						</tr>
					</c:if>
					<c:if test="${!empty review_list }">
						<c:forEach var="dto" items="${review_list }">
							<c:if test="${dto.cons_no == con_dto.con_no }">
								<tr>
									<td>
										${dto.view_writer }
										<input type="hidden" id="list_user" value="${dto.view_writer }">
									</td>
									<td>
										<div id="star_${dto.view_writer }"></div>
										<input type="hidden" name="view_num" value="${dto.view_num }">
										<input type="hidden" name="view_writer" value="${dto.view_writer }">
									</td>
									<td>${dto.view_regdate }</td>
									<td><input type="button" class="review" name="update_review" value="수정"></td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
	</body>
</html>