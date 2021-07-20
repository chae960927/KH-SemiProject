<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>고객지원</title>
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
			        <strong>고객지원</strong>
			        <p>무엇이든 물어보세요. 친절하게 답변해드립니다.</p>
			    </div>

			    <div class="tab_list">					
			        <a href="NoticeController.do?command=list" class="btn-list" style="vertical-align: middle;">공지사항</a>
			        <a href="FaqController.do?command=list" class="btn-list" style="vertical-align: middle;">FAQ</a>
			        <a href="SupController.do?command=list" class="btn-list" style="vertical-align: middle;">고객지원</a>
			    </div>
			    
			    <div style="text-align: end; margin-bottom: 20px;">
			    	<form action="SupController.do?command=search" method="post">
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
			                <div class="count">조회수</div>
			            </div> 
			            <c:choose>
							<c:when test="${empty sup_list }">
								<div class="board_title">
							        <p>-----문의 글이 없습니다.-----</p>
							    </div>
							</c:when>
							<c:otherwise>
								<c:forEach items="${sup_list }" var="sup_list">
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
			        <div>
				        <jsp:include page="/paging.jsp">
	    					<jsp:param value="sup" name="conName"/>
	    					<jsp:param value="${paging.page}" name="page"/>
	    					<jsp:param value="${paging.beginPage}" name="beginPage"/>
	    					<jsp:param value="${paging.endPage}" name="endPage"/>
	    					<jsp:param value="${paging.prev}" name="prev"/>
	    					<jsp:param value="${paging.next}" name="next"/>
						</jsp:include>
					</div>
			        <div class="bt_wrap">
			            <c:choose>
			        		<c:when test="${user.user_id == null}">
			        		</c:when>
			        		<c:otherwise>
			        			<input type="button" onclick="location.href='SupController.do?command=writeform'" class="on" value="등록">
				            </c:otherwise>
			            </c:choose>
			        </div>
				</div>
			</div>
		</div>
	</body>
</html>