<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>컨설팅 게시판</title>
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
		</script>
	</head>
	<body>
	  	<div id = "header"></div>
	  	<div class="center">
		  	<div class="board_wrap">
			    <div class="board_title">
			        <strong>컨설팅게시판</strong>
			        <p>원하는 기업에서 컨설팅을 받아보세요.</p>
			    </div>
		    
			    <div class="tab_list">					
			        <a href="InfoController.do?command=list" class="btn-list" style="vertical-align: middle;">정보게시판</a>
			        <a href="ConController.do?command=list" class="btn-list" style="vertical-align: middle;">컨설팅게시판</a>
			    </div>
			    
			    <div style="text-align: end; margin-bottom: 20px;">
			    	<form action="ConController.do?command=search" method="post">
			        	<input type="search" name="search" style="margin-top: 1%;">
			        	<input type="submit" value="검색">
			        </form>
			    </div>
		
			    <div class="board_list_wrap">
			        <div class="board_list">
			            <div class="top">
			                <div class="num">번호</div>
			                <div class="title">제목</div>
			                <div class="writer">작성자</div>
			                <div class="date">작성일</div>
			                <div class="count">조회</div>
			            </div> 
			            <c:choose>
							<c:when test="${empty con_list }">
								<div class="board_title">
							        <p>-----작성된 글이 없습니다.-----</p>
							    </div>
							</c:when>
							<c:otherwise>
								<c:forEach var="con_list" items="${con_list }">
									<div>
										<div class="num">${con_list.con_no }</div>
										<div class="title"><a href="ConController.do?command=detail&con_no=${con_list.con_no }">${con_list.con_title }</a></div>
										<div class="writer">${con_list.con_writer }</div>
										<div class="date">${con_list.con_regdate }</div>
										<div class="count">${con_list.con_push }</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
			        </div>
			        <div>
				        <jsp:include page="/paging.jsp">
	    					<jsp:param value="con" name="conName"/>
	    					<jsp:param value="${paging.page}" name="page"/>
	    					<jsp:param value="${paging.beginPage}" name="beginPage"/>
	    					<jsp:param value="${paging.endPage}" name="endPage"/>
	    					<jsp:param value="${paging.prev}" name="prev"/>
	    					<jsp:param value="${paging.next}" name="next"/>
						</jsp:include>
					</div>
			        <div class="bt_wrap">
			            <c:choose>
			        		<c:when test="${user.user_id == null || user.member != '기업' }">
			        			<input type="button" onclick="location.href='ConController.do?command=writeform'" class="on" value="등록" disabled>
			        		</c:when>
			        		<c:otherwise>
				            	<input type="button" onclick="location.href='ConController.do?command=writeform'" class="on" value="등록">
				            </c:otherwise>
			            </c:choose>
			        </div>
				</div>
			</div>
		</div>
	</body>
</html>