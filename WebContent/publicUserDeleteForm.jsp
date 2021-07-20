<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>탈퇴 전 비밀번호 확인</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link rel="stylesheet" href="css/mypage.css">
		<style type="text/css">
			.board_title {
				text-align: center;
			}
			#join_frm .join_table td {
				width: 0px !important;
			}
			#submit, #cansle {
				background-color: black;
				color: white;
			}
		</style>
		<script src="https://kit.fontawesome.com/a076d05399.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script type="text/javascript">
			function pwChk1() {
				var pw = opener.document.getElementsByName("user_pw")[0].value;
				var pw1 = $('#user_pw').val();
				if(pw1 == pw) {
					var id = opener.document.getElementsByName("user_id")[0].value;
					document.getElementsByName("user_id")[0].value = id;
				} else {
					alert("기존 비밀번호와 일치하지 않습니다.");
				}
			}
			function pwChk2() {
				var pw1 = $('#user_pw').val();
				var pw2 = $('#user_pw2').val();
				
				if(pw1 == pw2) {
					alert("비밀번호가 일치합니다.");
					$('#submit').attr("disabled", false);
				} else {
					alert("비밀번호가 일치하지 않습니다.");
					$('#user_pw2').focus();
				}
			}
		</script>
	</head>
	<body>
		<div class="board_wrap">
			<div class="board_title" style="margin-top: 10%;">
	            <strong>탈퇴하시겠습니까?</strong>
	        </div>
	        <form action="PublicController.do?command=userdelete" method="post" id="form">
	        	<input type="hidden" name="user_id">
	        	<div class="board_list_wrap" id="join_frm" style="clear:both;">
	        		<table class="join_table" style="border-top: 1px solid #222;">
	        			<tbody>
	        				<tr>
			                    <th>비밀번호</th>
			                    <td>
			                    	<input type="password" name="user_pw" id="user_pw" onchange="pwChk1();">
			                    </td>
			                </tr>
			                <tr>
			                    <th>비밀번호 확인</th>
			                    <td>
			                    	<input type="password" name="user_pw2" id="user_pw2" onchange="pwChk2();">
			                    </td>
			                </tr>
			                <tr align="center">
			                	<td colspan="2">
			                		<input type="submit" id="submit" value="탈퇴" disabled>
			                		<input type="button" id="cansle" value="취소" onclick="self.close();">
			                	</td>
			                </tr>
	        			</tbody>
	        		</table>
	        	</div>
	        </form>
		</div>
	</body>
</html>