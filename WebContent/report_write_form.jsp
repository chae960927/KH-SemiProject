<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>신고 작성</title>
		<style type="text/css">
			*{
			    margin-left: 10px;
			    margin-right: 10px;
				
			}
			#spam_object{
				 border-bottom : 1px solid gray; 
				 padding-bottom: 10px;"
			}
			#spam_object, #spam_content{
				display: grid;
				grid-template-columns: 1fr 5fr;
			}
			.spam_object_left div{
				font-weight : 700;
			}
			#spam_header{
				background-color: #212529;
				height:41px;
			}
			#header_div{
				 color : #fff;
				 font-weight : 700;
				 padding-top: 10px;
			}
			.content{
			    padding-top: 5px;
		    	padding-bottom: 5px;
			}
			.spam_content_left{
				font-weight : 700;
			}
			#spam_last{
				margin-left : 260px; 
				margin-top: 50px;
			}
			#spam_content{
		    	margin-top: 10px;
			}
			form{
				margin-top : 10px;
			}
			select{
			    padding-top: 5px;
			    padding-bottom: 5px;
			    padding-right: 5px;
			    padding-left: 5px;
			    height: 35px;
			}
			
			button{
				font-size: x-large;
				border-radius: 5px;
				font-weight : 700;
			}
			
			#btn_left{
				background-color : #e20d0ddb;
				padding-left: 10px;
		    	padding-right: 10px;
		    	color : white;
			}
			#btn_right{
				background-color : white;
				padding-left: 10px;
		    	padding-right: 10px;
			}
		</style>
		
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script type="text/javascript">
				$(function(){
					$("#submit").click(function(){
						var rep_name = opener.document.getElementById('rep_name').value;
						var rep_id = opener.document.getElementById('rep_id').value;
						var rep_content = opener.document.getElementById('rep_content').value;
						console.log(rep_content);
						
						var rep_reason= $("#reason option:selected").val();
						var board= $("#board").val();
						var rep_listnumber= $("#rep_listnumber").val();
						
						$.ajax({
							type: "POST",
							url:"reportController.do?command=insertReport&rep_name=" + rep_name
									+"&rep_id=" + rep_id
									+"&rep_reason=" + rep_reason
									+"&rep_listnumber=" + rep_listnumber
									+"&board=" + board,
							data : {"rep_content" : rep_content},
							async: false,
							success: function(data){
							   console.log(data);
								if(data>0){
									alert("신고 완료하였습니다.");
									close();
								}
							},
							error : function(){
								alert("실패");
							}
						});
					});
				});
		</script>
	</head>
	<body>
		<%  
			int board = Integer.parseInt(request.getParameter("board"));
			int rep_listnumber = Integer.parseInt(request.getParameter("rep_listnumber"));
		%>
		<div id ="spam_header"> 
	        <div id="header_div">게시물/댓글 신고</div>
	   </div>
		
		<div class= "spam_wrap">
			<div id="spam_content">
				<div class="spam_content_left">
					<div class="content">사유선택</div>
				</div>
				<div>
					<div class="content">여러 신고 사유에 해당될 경우, 상위 사유 1개를 선택</div>
				    <form>
				    	<select id="reason">
				    		<option id="reso1" value="광고 /영리추구">광고 /영리추구</option>
				    		<option id="reso2" value="욕설/비방">욕설/비방</option>
				    		<option id="reso3" value="악성코드/시스템 장애유도">악성코드/시스템 장애유도</option>
				    	</select>
				    	<input type="hidden" id="board" value="<%=board%>">
						<input type="hidden" id="rep_listnumber" value="<%=rep_listnumber%>">
				    </form>
				
				</div>
			</div>
			<div id="spam_last">
				<button type="button" id="submit">완료</button>
				<button type="button" id="cancel" onclick="self.close()">취소</button>
			</div>
		</div>	
	
	
	
	</body>
</html>