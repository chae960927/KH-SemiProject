<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>컨설팅 게시판 글 작성</title>
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
				     elPlaceHolder: "con_content",
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
			         oEditors.getById["con_content"].exec("UPDATE_CONTENTS_FIELD", []);
			         $("#frm").submit();
			     });
				 
			  	 // textArea에 이미지 첨부
				 function pasteHTML(filepath){
					 var sHTML = '<img src="<%=request.getContextPath()%>/<%=request.getSession().getServletContext().getRealPath("/")%>"/upload"/' + filepath + '">';
				     oEditors.getById["con_content"].exec("PASTE_HTML", [sHTML]);
				 }
		      });
		</script>
	</head>
	<body>
		<div id = "header"></div>  
	    <div class="board_wrap">
			<div class="board_title">
				<strong>컨설팅게시판</strong>
				<p>원하는 기업에서 컨설팅을 받아보세요.</p>
			</div>
			<form action="ConController.do?command=write" method = "post" id="frm">
		        <div class="board_write_wrap">
		            <div class="board_write">
		                <div class="title">
		                    <dl>
		                        <dt>제목</dt>
		                        <dd><input type="text" name="con_title" placeholder="제목 입력"></dd>
		                    </dl>
		                </div>
		                <div class="info">
		                    <dl>
		                        <dt>작성자</dt>
		                        <dd><input type="text" name="con_writer" placeholder="작성자 입력" value="${user.user_id }"></dd>
		                    </dl>
		                </div>
		                <div class="cont">
		                    <textarea placeholder="내용 입력" name="con_content" id="con_content"></textarea>
		                </div>
		            </div>
		        </div>
				<div class="bt_wrap">
					<input type="submit" id="save" class="on" value="등록">
					<input type="button" class="on" onclick="location.href='ConController.do?command=list'" value="취소">
				</div>
			</form>
	    </div>	
	</body>
</html>