<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ page import ="javax.servlet.http.HttpSession"%>
<%@ page import = "com.login.dto.UserDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅</title>
<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		var chat_num = $('#chat_num').val();
		var to_user = $("#to_user").val();
		var from_user = $("#from_user").val();
		
		$.ajax({
			type: "post",
			url: "chatcontroller.do?command=start",
			data:{ chat_num: chat_num, from_user: from_user, to_user: to_user },
			success: function(json){
				var info = JSON.parse(json);
				
		 		for(var i in info.chat){
		 			var user = info.chat[i].user;
		 			console.log(user);
		 			
		 			if(user === from_user){//세션이랑 맞을때
					 	$(".view").append('<div style="float: right; clear: both;">'+
								'<b style="float: right;">'+user+//세션 닉네임
								'</b><br>'+
								'<textarea class="viewbox" readonly="readonly" onclick="resize(this);scroll();" style="resize:none; overflow:visible;">'+
								info.chat[i].content+
								'</textarea>'+
								'</div>');
						$(".viewbox").trigger("click");  
						scroll();
		 			}else if(user !== from_user){
						 $(".view").append('<div style="float: left; clear: both;">'+
								'<b>'+ info.chat[i].user+
								'</b><br>'+
								'<textarea class="viewbox" readonly="readonly" onclick="resize(this);scroll();" style="resize:none; overflow:visible;">'+
								info.chat[i].content+
								'</textarea>'+
								'</div>');
						$(".viewbox").trigger("click");  
						scroll();
		 			}
				}
			}
		});
		
	});
	
	function scroll(){
		$('#scrollDiv').scrollTop($('#scrollDiv').prop('scrollHeight'));
	}
	
	function send(){
		var chat_num = $('#chat_num').val();
		var content = $("#textbox").val();
		var from_user = $("#from_user").val();
		var to_user = $("#to_user").val();
		
		$.ajax({
			type: "post",
			url: "chatcontroller.do?command=send",
			data:{chat_num: chat_num, from_user: from_user, content: content, to_user: to_user},
			success: function(res){
				if(res>0){//성공
					$(".view").append('<div style="float: right; clear: both;">'+
							'<b style="float: right;">' + from_user +
							'</b><br>'+
							'<textarea class="viewbox" readonly="readonly" onclick="resize(this);scroll();" style="resize:none; overflow:visible;">'+content+
							'</textarea>'+
							'</div>');
					$(".viewbox").trigger("click");
					scroll();
					$("#textbox").val("");
				}				
			}
		})
		
	}
	
	function resize(obj) {
	    obj.style.height = '1px';
	    obj.style.height = (12 + obj.scrollHeight) + 'px';
	}
</script>
</head>

	<body>
	<%	
		UserDto user = (UserDto)request.getSession().getAttribute("user");
		String chat_num = (String) request.getParameter("chat_num");
		String from_user = (String) request.getParameter("from_user");
		String to_user = (String) request.getParameter("to_user");
	%>
		<input type="hidden" id="chat_num" value="<%=chat_num%>">
		<input type="hidden" id="from_user" value="<%=from_user%>">
		<input type="hidden" id="to_user" value="<%=to_user%>">
		<div>
			<div class="view" id = "scrollDiv" style="width:100%; height:600px; overflow-y: auto;">
		
			</div>
			<div class="chat" style="position: fixed; bottom:0px; left:0px; width:100%; height:180px; background-color: lightgray;">
				<textarea id = "textbox" style="width:80%; height: 150px; resize: none; margin-top: 10px; margin-left: 10px;"></textarea>
				<button onclick="send();">전송</button>
			</div>
		</div>
	</body>
</html>