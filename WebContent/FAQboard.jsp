<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>FAQ</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link rel="stylesheet" href="css/noticeboardFAQ.css">
		<style type="text/css">
			.on {
			    background: #000;
			    color: #fff;
			    display: inline-block;
			    min-width: 80px;
			    padding: 5px;
			    border: 1px solid #000;
			    border-radius: 2px;
			    font-size: 1.4rem;
			}
			form {
    			display: none;
			}
			#content {
				font-size: 15px;
				padding-top: 20px;
			    padding-bottom: 20px;
			    text-align: left;
			}
		</style>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		
		<script src="https://kit.fontawesome.com/a076d05399.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script type="text/javascript">
		      $(document).ready(function(){
		         $("#header").load("form/header.jsp");
		         $("#footer").load("form/footer.jsp");
		         
		         $('.accordion-content').hide();
		      });
		      
		      function faqDelete(faq_no) {
		    	  location.href = "FaqController.do?command=delete&faq_no=" + faq_no;
		      }
			  
			  function faqUpdateNo() {
				  $("#updateForm").attr("style", "display: none;");
			  }
			  
			  function insert() {
				  $("#insertForm").attr("style", "display: flex; flex-direction: column;");
			  }
			  
			  function insertNo() {
				  $("#insertForm").attr("style", "display: none;");
			  }
		</script>
	</head>
	<body>
		<div id = "header"></div>
		<div class="center">
			<div class="board_wrap">
			    <div class="board_title">
			        <strong>FAQ</strong>
			        <p>자주찾는 질문을 확인하세요.</p>
			    </div>
	    
			    <div class="tab_list">					
			        <a href="NoticeController.do?command=list" class="btn-list" style="vertical-align: middle;">공지사항</a>
			        <a href="FaqController.do?command=list" class="btn-list" style="vertical-align: middle;">FAQ</a>
			        <a href="SupController.do?command=list" class="btn-list" style="vertical-align: middle;">고객지원</a>
			    </div>
	    
			     <div class="accordion">
					<c:choose>
						<c:when test="${empty faq_list }">
							<div class="board_title">
						        <p>-----작성된 FAQ가 없습니다.-----</p>
						    </div>
						</c:when>
						<c:otherwise>
							<c:forEach var="faq_list" items="${faq_list }">
								<div class="accordion-item">
									<button class="accordion-button" id="accordion-button" onclick="$(this).next().toggle(); $(this).children('.icon').children().toggleClass('rotate');">
						                <span>
						                    ${faq_list.faq_no }. ${faq_list.faq_title }?
						                </span>
						                <span class="icon">
						                    <img src="./img/down-arrow.png" class="up" id="up" alt="">
						                </span>
						            </button>
						            <div class="accordion-content" id="accordion-content">
						                <p>
						                    ${faq_list.faq_no }번 FAQ 답변입니다.
						                    <hr>
						                    <p id="content">${faq_list.faq_content }</p>
						                </p>
						                <c:if test="${user.member == '관리자' }">
						                	<input type="button" id="faqUpdate" class="on" onclick="$(this).next().next().attr('style', 'display: flex; flex-direction: column;');" value="수정">
						                	<input type="button" id="faqDelete" class="on" onclick="faqDelete(${faq_list.faq_no });" value=" 삭제">
						                </c:if>
						                <form action="FaqController.do?command=update" method="post" id="updateForm">
						                	<input type="hidden" name="faq_no" value="${faq_list.faq_no }">
						            		<input type="text" name="faq_title" value="${faq_list.faq_title }">
						            		<textarea rows="10" cols="60" name="faq_content">${faq_list.faq_content }</textarea>
						            		
						            		<input type="submit" value="수정완료" class="on">
						            		<input type="button" onclick="$(this).parent().attr('style', 'display: none;');" value="수정취소" class="on">
						            	</form>
						            </div>
					            </div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
			        <div class="bt_wrap">
			            <c:choose>
			        		<c:when test="${user.user_id == null || user.member != '관리자' }">
			        		</c:when>
			        		<c:otherwise>
				            	<input type="button" onclick="insert();" class="on" value="등록">
				            </c:otherwise>
			            </c:choose>
			            <form action="FaqController.do?command=write" method="post" id="insertForm">
		            		<input type="text" name="faq_title">
		            		<textarea rows="10" cols="60" name="faq_content"></textarea>
		            		
		            		<input type="submit" value="등록" class="on">
		            		<input type="button" onclick="insertNo();" value="취소" class="on">
		            	</form>
			        </div>
				</div>
			</div>
		</div>
	</body>
</html>