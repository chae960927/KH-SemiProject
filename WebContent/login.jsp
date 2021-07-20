<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>로그인</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link rel="stylesheet" href="css/login.css" />
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
		<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
		<script type="text/javascript">
			let id = $('#id');
			let pw = $('#pw');
			let btn = $('#btn');
		
			$(btn).on('click', function(){
				if($(id).val() == ""){
					$(id).next('label').addClass('warning');
					setTimeout(function(){
						$('label').removeClass('warning');
					},1500)
				}
				else if($(pw).val() == ""){
					$(pw).next('label').addClass('warning');
					setTimeout(function(){
						$('label').removeClass('warning');
					},1500)
				}
			});
			window.Kakao.init("af7a2e7f6ceba3c183ab67340616e8d1");
			
			function kakaoLogin(){ //카카오 로그인
				
				window.Kakao.Auth.loginForm({
					success: function(authObj){
						
						window.Kakao.API.request({
							url:'/v2/user/me',
							success: function(res){
						 			var nickName = res.kakao_account.email;
									location.href="login.do?command=socialLogin&nickName="+nickName;
							}
						});
					}
				});
			}
		</script>
	</head>
	<body>
		<section class="login-form">
			<h1><a href="index.jsp" style="text-decoration: none; color: white;">Leporem</a></h1>
			<form action="login.do" method="post">
				<input type="hidden" name="command" value="login">
				<div class="int-area">
					<input type="text" name="user_id" id="user_id" autocomplete="off" required>
					<label for="user_id">USER NAME</label>
				</div>
				<div class="int-area">
					<input type="password" name="user_pw" id="user_pw" autocomplete="off" required>
					<label for="user_pw">PASSWORD</label>
				</div>
				<div class="btn-area">
					<button type="submit">LOGIN</button>
				</div>
				<div class="btn-area-kakao">
					<a href="#"onclick="kakaoLogin();"><img src = "img/kakao.png"></a>
				</div>
		        <div class="btn-area-naver" id="naver_id_login"  ><img src = "img/naver.png" ></div>
			    	<script type="text/javascript">
	 				var naver_id_login = new naver_id_login("QxNCJBgb0AEDEzELTxsd", "http://localhost:8448/Leporem/naverLogin.jsp");
	 				var state = naver_id_login.getUniqState();
	  				naver_id_login.setDomain("./index.jsp");
	 				naver_id_login.setState(state);
	  				//naver_id_login.setPopup("false");
	  				naver_id_login.init_naver_id_login();
	 				</script>
			</form>
			<div class="caption">
				<a href="#">Forget Password?</a>
			</div>
		</section>
	</body>
</html>



