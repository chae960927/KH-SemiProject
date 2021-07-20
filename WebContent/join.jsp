<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link rel="stylesheet" href="css/join.css">
		
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="js/join.js"></script>
	</head>
	<body>
		<section class="join-form">
			<h1><a href="index.jsp" style="text-decoration: none; color: white;">Leporem</a></h1>
			<form action="join.do?command=join" method="post">
				<div class="int-area">
					<input type="text" name="user_id" id="user_id" title="n" autocomplete="off" required>
					<label for="user_id">아이디</label>
					<span class="cnum-btn-wrap">
	               		<button id="cnum-btn" style="float:right" onclick="idChk();">중복확인</button>
	                </span>
				</div>
				<div class="int-area">
					<input type="password" name="user_pw" id="user_pw" autocomplete="off" title="n" onclick="idChkConfirm();" required>
					<label for="user_pw">비밀번호</label>
				</div>
				<div class="int-area">
					<input type="password" name="user_pw2" id="user_pw2" autocomplete="off" onclick="idChkConfirm();" onchange="pwChk();" required>
					<label for="user_pw2">비밀번호 재확인</label>
				</div>
				<div class="int-area">
					<input type="text" name="user_name" id="user_name" autocomplete="off" onclick="idChkConfirm();" required>
					<label for="user_name">이름</label>
				</div>
				<div class="int-area">
					<input type="text" name="nick_name" id="nick_name" autocomplete="off" onclick="idChkConfirm();" required>
					<label for="nick_name">닉네임</label>
				</div>
				<div class="int-area">
					<input type="text" name="user_phone" id="user_phone" autocomplete="off" onclick="idChkConfirm();" required>
					<label for="user_phone">전화번호</label>
				</div>
				<div class="int-area">
					<input type="text" name="user_email" id="user_email" autocomplete="off" title="n" onclick="idChkConfirm();" required>
					<label for="user_email">본인 확인 이메일</label>
					<span class="cnum-btn-wrap">
	               		<button id="cnum-btn" style="float:right" onclick="emailChk();">인증번호</button>
	                </span>
				</div>
				<div class="int-area">
					<input type="text" name="user_addr" id="user_addr" autocomplete="off" onclick="idChkConfirm();" required>
					<label for="user_addr">주소</label>
					<span class="cnum-btn-wrap">
	               		<button id="cnum-btn" style="float:right" onclick="findAddr();">우편번호</button>
	                </span>
	                <div class="int-area">
						<input type="text" name="user_addr_de" id="user_addr_de" autocomplete="off" onclick="idChkConfirm();" required>
						<label for="user_addr_de">상세주소</label>
					</div>
				</div>
				<div class="int-area">
					<input type="text" name="user_member" id="user_member" autocomplete="off" onclick="idChkConfirm();" onchange="bizChk();" required>
					<label for="user_member">가입구분</label>
					<input type="hidden" name="public" value="개인">
					<input type="hidden" name="company" value="기업">
				</div>
				
				<div class="int-area" id="bizChk">
					<input type="text" name="biz_num" id="biz_num" autocomplete="off" onclick="idChkConfirm();" required>
					<label for="biz_num">사업자번호</label>
				</div>
				<div class="btn-area">
					<button type="submit" onclick="joinChk();">JOIN</button>
				</div>
			</form>
		</section>
	</body>
</html>