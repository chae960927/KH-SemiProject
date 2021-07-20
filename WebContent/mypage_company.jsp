<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>마이페이지</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link rel="stylesheet" href="css/mypage.css">
		<script src="https://kit.fontawesome.com/a076d05399.js"></script>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script type="text/javascript">
		      $(document).ready(function(){
		         $("#header").load("form/header.jsp");
		         $("#footer").load("form/footer.jsp");
		         
		         $('#updatebtn').hide();
		         $('#pwbtn').hide();
		      });
		      
		      function user_info() {
		    	  var user_name = $('#user_name').prop("disabled");
		    	  var nick_name = $('#nick_name').prop("disabled");
		    	  var user_phone = $('#user_phone').prop("disabled");
		    	  var user_email = $('#user_email').prop("disabled");
		    	  var user_addr = $('#user_addr').prop("disabled");
		    	  var user_addr_de = $('#user_addr_de').prop("disabled");
		    	  var updatebtn = $('#updatebtn').prop("disabled");
		    	  
		    	  if(user_name == true && nick_name == true && user_phone == true && user_email == true && user_addr == true && user_addr_de == true && updatebtn == true) {
		    		  $('#user_name').attr("disabled", false);
			    	  $('#nick_name').attr("disabled", false);
			    	  $('#user_phone').attr("disabled", false);
			    	  $('#user_email').attr("disabled", false);
			    	  $('#user_addr').attr("disabled", false);
			    	  $('#user_addr_de').attr("disabled", false);
			    	  $('#updatebtn').attr("disabled", false);
			    	  $('#updatebtn').show();
		    	  } else {
		    		  $('#user_name').attr("disabled", true);
			    	  $('#nick_name').attr("disabled", true);
			    	  $('#user_phone').attr("disabled", true);
			    	  $('#user_email').attr("disabled", true);
			    	  $('#user_addr').attr("disabled", true);
			    	  $('#user_addr_de').attr("disabled", true);
			    	  $('#updatebtn').attr("disabled", true);
			    	  $('#updatebtn').hide();
		    	  }
		      }
		      
		      function submit() {
		    	  var user_id = $('#user_id').val();
		    	  var user_pw = $('#user_pw').val();
		    	  var user_name = $('#user_name').val();
		    	  var nick_name = $('#nick_name').val();
		    	  var member = $('#member').val();
		    	  var user_phone = $('#user_phone').val();
		    	  var user_email = $('#user_email').val();
		    	  var user_addr = $('#user_addr').val();
		    	  var user_addr_de = $('#user_addr_de').val();
		    	  
		    	  location.href="PublicController.do?command=userupdate&user_id=" + user_id + "&user_pw=" + user_pw +
		    			  "&member=" + member + "&user_name=" + user_name + "&nick_name=" + nick_name +
		    			  "&user_phone=" + user_phone + "&user_email=" + user_email + "&user_addr=" + user_addr + "&user_addr_de=" + user_addr_de;
		      }
		      
		      function userDeleteForm() {
		    	  var target = "publicUserDeleteForm.jsp";
		    	  open(target, "", "width=630, height=430");
		      }
		      
		      function pwUpdate() {
		    	  var user_pw = $('#user_pw').prop("disabled");
		    	  var user_pw2 = $('#user_pw2').prop("disabled");
		    	  
		    	  if(user_pw == true && user_pw2 == true) {
		    		  $('#user_pw').attr("disabled", false);
			    	  $('#user_pw2').attr("disabled", false);
		    	  } else {
		    		  $('#user_pw').attr("disabled", true);
			    	  $('#user_pw2').attr("disabled", true);
		    	  }
		      }
		      
		      function pwChk() {
	    	  		var pw = $('#pw').val(); //원본 비밀번호
					var pw1 = $('#user_pw').val();
					if(pw1 == pw) {
						alert("기존 비밀번호와 동일한 비밀번호는 사용할 수 없습니다.");
						$('#user_pw').focus();
					} else {
						
					}
		      }
		      
		      function pwUpdate2() {
					var pw1 = $('#user_pw').val();
					var pw2 = $('#user_pw2').val();
					
					if(pw1 == pw2) {
						alert("비밀번호가 일치합니다.");
						$('#pwbtn').show();
					} else {
						alert("비밀번호가 일치하지 않습니다.");
						$('#user_pw2').focus();
					}
			  }
		      
		      function submit2() {
		    	  var user_id = $('#user_id').val();
		    	  var user_pw = $('#user_pw').val();
		    	  
		    	  location.href="PublicController.do?command=pwupdate&user_id=" + user_id + "&user_pw=" + user_pw;
		      }
		      
		      function open_win(url) {
		    	  open(url, "", "width=700, height=300");
		      }
		      
		      function open_win2(url) {
		    	  open(url, "", "width=1000, height=230");
		      }
		      
		      function imgUpload(url) {
		    	  open(url, "", "width=700, height=200");
		      }
		</script>
	</head>
	<body>
		<div id = "header"></div>
		<div class="board_wrap" style="top: 50%;">
	        <div class="board_title">
	            <strong>회원 마이페이지</strong>
	        </div>
	        
	        <div class="tab_list">
	            <button class="btn-list" style="vertical-align: middle;" onclick="user_info();">개인정보수정</button>
	            <button class="btn-list" style="vertical-align: middle;" onclick="imgUpload('PublicController.do?command=imgUploadForm&user_id=${user_list.user_id }');">프로필등록</button>
	            <button class="btn-list" style="vertical-align: middle;" onclick="pwUpdate();">비밀번호 변경</button>
	            <button class="btn-list" style="vertical-align: middle;" onclick="userDeleteForm();">회원 탈퇴</button>
	            <button class="btn-list" style="vertical-align: middle;" onclick="location.href='ConController.do?command=myself&user_id=${user_list.user_id }'">작성한 글 확인</button>
	            <button class="btn-list" style="vertical-align: middle;" onclick="open_win2('SupController.do?command=sup_list&user_id=${user_list.user_id }');">문의내역</button>
	            <button class="btn-list" style="vertical-align: middle;" onclick="open_win('PayController.do?command=pay_list_com&pay_company=${user_list.user_id }');">컨설팅내역</button>
	            <button class="btn-list" style="vertical-align: middle;" onclick="location.href='calendar.do?command=calendar&user_id=${user_list.user_id }'">일정관리</button>
	            <button class="btn-list" id="updatebtn" style="vertical-align: middle;" onclick="submit();">수정완료</button>
	            <button class="btn-list" id="pwbtn" style="vertical-align: middle;" onclick="submit2();">수정완료</button>
	        </div>
	        
	        <div id="login" style="width:1130px;">
		        <div class="board_list_wrap" id="join_frm" style="width:1130px; clear:both;">
		            <table class="join_table" cellpadding="0" cellspacing="0" style="border-top: 1px solid #222;">
			            <tbody>
			                <tr>
			                    <th>회원구분*</th>
			                    <td>
			                    	${user.member }회원
			                    	<input type="hidden" name="member" value="${user.member }">
			                    </td>
			                </tr>
			                <tr>
			                    <th>아이디*</th>
			                    <td>
			                        <input type="text" id="user_id" name="user_id" value="${user_list.user_id }" disabled>
			                    </td>
			                </tr>
				            <tr>
				                <th>비밀번호*</th>
				                <td>
				                    <input type="password" id="user_pw" name="user_pw" value="${user_list.user_pw }" disabled onchange="pwChk();">
				                    <input type="hidden" id="pw" name="pw" value="${user_list.user_pw }">
				                </td>
				            </tr>
				            <tr>
				                <th>비밀번호확인*</th>
				                <td>
				                    <input type="password" id="user_pw2" name="user_pw2" disabled onchange="pwUpdate2();">
				                </td>
				            </tr>
				            <tr>
				                <th>이름*</th>
				                <td><input type="text" id="user_name" name="user_name" value="${user_list.user_name }" disabled>
				                </td>
				            </tr>
				            <tr>
				                <th>닉네임*</th>
				                <td><input type="text" id="nick_name" name="nick_name" value="${user_list.nick_name }" disabled>
				                </td>
				            </tr>
				            <tr>
				                <th>휴대폰*</th>
				                <td>
			                        <input type="text" id="user_phone" name="user_phone"  class="email_cont" value="${user_list.user_phone }" disabled>
				                </td>
				            </tr>
				            <tr>
				                <th>이메일*</th>
				                <td>
			                        <input type="text" class="email_cont" id="user_email" name="user_email" value="${user_list.user_email }" disabled>
				                </td>
				            </tr>
				            <tr class="onlyStdt">
				                <th>주소*</th>
				                <td>
				                    <input type="text" id="user_addr" name="user_addr" value="${user_list.user_addr }" disabled>
				                    <button type="button" id="cnum-btn" style=" width: 71px; height: 33px; position: absolute; top: 20px; line-height: 33px; left: 367px; font-size: 13px; background: #2a2a4e; border-radius: 50px; border:none; color:#fff" onclick="findAddr();">주소검색</button><br>
				                    <input type="text" id="user_addr_de" value="${user_list.user_addr_de }" disabled>
				                </td>
				            </tr>
				            <tr>
				                <th>사업자 번호*</th>
				                <td>
			                        <input type="text" class="biz_num" id="biz_num" name="biz_num" value="${user_list.biz_num }" disabled>
				                </td>
				            </tr>
				        </tbody>
	       	 		</table>
	        	</div>
	    	</div>
		</div>
		<script type="text/javascript" src="js/join.js"></script>
	</body>
</html>