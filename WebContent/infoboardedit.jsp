<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>정보 게시판 글 수정</title>
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
		<!-- Smart Editor -->
		<script type="text/javascript" src="se2/js/HuskyEZCreator.js" charset="utf-8"></script>
		<script type="text/javascript">
		      $(document).ready(function(){
		         $("#header").load("form/header.jsp");
		         $("#footer").load("form/footer.jsp");
		      });
		      
		      $(function(){
		    	 //Smart Editor
				 var oEditors = [];
				 nhn.husky.EZCreator.createInIFrame({
				     oAppRef: oEditors,
				     elPlaceHolder: "info_content",
				     sSkinURI: "se2/SmartEditor2Skin.html",
				     htParams : {
		             	// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		             	bUseToolbar : true,
		             	// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		             	bUseVerticalResizer : true,
		             	// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		             	bUseModeChanger : true
	             	 },
				     fCreator: "createSEditor2"
				 });
				 
				 //저장버튼 클릭시 form 전송
			     $("#save").click(function(){
			         oEditors.getById["info_content"].exec("UPDATE_CONTENTS_FIELD", []);
			         $("#frm").submit();
			     });
				 
			  	 // textArea에 이미지 첨부
				 function pasteHTML(filepath){
					 var sHTML = '<img src="<%=request.getContextPath()%>/<%=request.getSession().getServletContext().getRealPath("/")%>"/upload"/' + filepath + '">';
				     oEditors.getById["info_content"].exec("PASTE_HTML", [sHTML]);
				 }
		      });
		      
		      $(function(){
		 	   	 $("#spam:eq(0)").click(function(){
		 	   	 	openWin();
		 	   	 })
		 	  });
		 	    
		 	  function openWin(){
		 	  	var url ="spam.jsp";
		 	    var title ="";
		 	    var prop = "top=200px, left=600px, width=500px, height=500px, resizble=no";
		 	    window.open(url,title,prop);
		 	  }
		</script>
	</head>
	<body>
	  <div id = "header"></div>
	  <div class="board_wrap">
	    <div class="board_title">
	        <strong>정보 게시판</strong>
	        <p>${info_dto.info_writer }님의 글을 수정합니다.</p>
	    </div>
	    <form action="InfoController.do?command=update&info_no=${info_dto.info_no }" method = "post" id="frm">
		    <div class="board_write_wrap">
		        <div class="board_write">
		            <div class="title">
		                <dl>
		                    <dt>제목</dt>
		                    <dd><input type="text" placeholder="제목 입력" name="info_title" value="${info_dto.info_title }"></dd>
		                </dl>
		            </div>
		            <div class="info">
		                <dl>
		                    <dt>작성자</dt>
		                    <dd><input type="text" placeholder="작성자 입력" value="${info_dto.info_writer }" readonly="readonly"></dd>
		                </dl>
		                <dl>
		                    <dt>비밀번호</dt>
		                    <dd><input type="text" placeholder="비밀번호 입력" value="1234" readonly="readonly"></dd>
		                </dl>
		            </div>
		            <div class="cont">
		                <textarea id="info_content" placeholder="내용 입력" name="info_content">${info_dto.info_content }</textarea>
		            </div>
		        </div>
		    </div>
		    <div class="bt_wrap">
		            <input type="submit" id="save" class="on" value="수정">
		            <input type="button" class="on" onclick="location.href='InfoController.do?command=detail&info_no=${info_dto.info_no}'" value="취소">
					<input type="button" class="on" onclick="location.href='InfoController.do?command=list'" value="목록">
			</div>
		</form>
		</div>
	</body>
</html>