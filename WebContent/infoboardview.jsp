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
		<title>정보게시판 글 상세</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link rel="stylesheet" href="css/boardedit.css">
		<style type="text/css">
			.board_wrap {
				top: 70%;
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
			
			p img {
				max-width: 100% !important;
				height: 400px !important;
			}
		</style>
		<script src="https://kit.fontawesome.com/a076d05399.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script type="text/javascript">
		      $(document).ready(function(){
		         $("#header").load("form/header.jsp");
		         $("#footer").load("form/footer.jsp");
		      });
		      
		      function selectReason(info_no){
		    	  openWin(1,info_no);
		      }
		 	    
		      function openWin(board,info_no){
		 	  	var url ="report_write_form.jsp?rep_listnumber=" + info_no + "&board=" + board;
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
		        <strong>정보 게시판</strong>
		        <p>${info_dto.info_writer }님의 글 입니다.</p>
		    </div>
		    <div class="board_write_wrap">
		        <div class="board_write">
		            <div class="title">
		                <dl>
		                    <dt>제목</dt>
		                    <dd><c:out value="${info_dto.info_title }" /></dd>
		                </dl>
		            </div>
		            <div class="info">
		                <dl>
		                    <dt>작성자</dt>
		                    <dd><c:out value="${info_dto.info_writer }" /></dd>
		                </dl>
		            </div>
		            <div class="cont">
		                ${info_dto.info_content }
		            </div>
		        </div>
		    </div>
		    <div class="bt_wrap">
		    	<c:choose>
		    		<c:when test="${user.user_id == null || user.user_id != info_dto.info_writer }">
		            	<c:if test="${user.user_id != null }">
		            		<input type="button" class="on" value="신고" onclick="selectReason(${info_dto.info_no});">
			            	<input type="hidden" id="rep_name" value="${user.user_name}">
			            	<input type="hidden" id="rep_id" value="${info_dto.info_writer}">
			            	<input type="hidden" id="rep_content" value='${info_dto.info_content}'>
		            	</c:if>
					</c:when>
					<c:otherwise>
			            <input type="button" class="on" onclick="location.href='InfoController.do?command=updateform&info_no=${info_dto.info_no }'" value="수정">
			            <input type="button" class="on" onclick="location.href='InfoController.do?command=delete&info_no=${info_dto.info_no }'" value="삭제">
		            </c:otherwise>
		        </c:choose> 
				<input type="button" class="on" onclick="location.href='InfoController.do?command=list'" value="목록">
			</div>
			<div id="disqus_thread"></div>
			<script type="text/javascript">
				/**
				*  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
				*  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables    */
				/*
				var disqus_config = function () {
				this.page.url = PAGE_URL;  // Replace PAGE_URL with your page's canonical URL variable
				this.page.identifier = PAGE_IDENTIFIER; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
				};
				*/
				(function() { // DON'T EDIT BELOW THIS LINE
				var d = document, s = d.createElement('script');
				s.src = 'https://leporem.disqus.com/embed.js';
				s.setAttribute('data-timestamp', +new Date());
				(d.head || d.body).appendChild(s);
				})();
			</script>
			<noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>
		</div>
	</body>
</html>