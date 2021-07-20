<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>고객문의 글 작성</title>
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
				<p>무엇이든 물어보세요. 친절하게 답변해드립니다.</p>
			</div>
			<form action="SupController.do?command=write" method = "post">
		        <div class="board_write_wrap">
		            <div class="board_write">
		                <div class="title">
		                    <dl>
		                        <dt>제목</dt>
		                        <dd><input type="text" name="sup_title" placeholder="제목 입력"></dd>
		                    </dl>
		                </div>
		                <div class="info">
		                    <dl>
		                        <dt>작성자</dt>
		                        <dd><input type="text" name="sup_writer" placeholder="작성자 입력" value="${user.user_id }" readonly="readonly"></dd>
		                    </dl>
		                </div>
		                <div class="cont">
		                    <textarea placeholder="내용 입력" name="sup_content"></textarea>
		                </div>
		            </div>
		        </div>
				<div class="bt_wrap">
					<input type="submit" class="on" value="등록">
					<input type="button" class="on" onclick="location.href='SupController.do?command=list'" value="취소">
				</div>
			</form>
	    </div>	
	</body>
</html>