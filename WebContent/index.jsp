<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Leporem</title>
	<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
	<link rel="stylesheet" href="css/index.css">
	<link rel="stylesheet" href="css/indexIntroduce.css">
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
	<div class="img">
		<img src="img/banner-img.jpg">
		<div class = "Leporem_Introduce">
			<div class = "Leporem_Introduce_top1">
				<p class = "Leporem_Introduce_title">Leporem</p>
				<p class = "Leporem_Introduce_subtitle">당신의 공간이 매력적이게 바뀌는 순간</p>
				<p class = "Leporem_Introduce_content">집은 가장 편해야 하는 공간으로 업계 최고 전문가와 Consulting을 통해 나만의 취향을 분석하여<br> 편하면서 가장 매력적이게 바꿔보세요 Leporem이 항상 당신의 옆에서 도움이 되겠습니다.</p>
			</div>
		</div>
	</div>

	<div class = "Leporem_Introduce">
		<div class = "Leporem_Introduce_top">
			<p class = "Leporem_Introduce_title">Leporem</p>
			<p class = "Leporem_Introduce_subtitle">당신의 공간이 매력적이게 바뀌는 순간</p>
			<p class = "Leporem_Introduce_content">집은 가장 편해야 하는 공간으로 업계 최고 전문가와 Consulting을 통해 나만의 취향을 분석하여<br> 편하면서 가장 매력적이게 바꿔보세요 Leporem이 항상 당신의 옆에서 도움이 되겠습니다.</p>
		</div>
		
		<div class = "Leporem_Introduce_center">
			<ul class = "Leporem_Introduce_keypoint">
				<li>
					<img alt = "service" src = "img/service_icon_off.png" onmouseover="this.src='img/service_icon_on.png'" onmouseout="this.src='img/service_icon_off.png'">
					<br>
					<span>꼼꼼한 서비스 제공</span>
				</li>
			
				<li>
					<img alt = "team" src = "img/team_icon_off.png" onmouseover="this.src='img/team_icon_on.png'" onmouseout="this.src='img/team_icon_off.png'">
					<br>
					<span>업계 최고 전문가와 연결</span>
				</li>
				
				<li>
					<img alt = "money" src = "img/money_icon_off.png" onmouseover="this.src='img/money_icon_on.png'" onmouseout="this.src='img/money_icon_off.png'">
					<br>
					<span>합리적인 가격</span>
				</li>
				
				<li>
					<img alt = "chat" src = "img/chat_icon_off.png" onmouseover = "this.src='img/chat_icon_on.png'" onmouseout = "this.src = 'img/chat_icon_off.png'">
					<br>
					<span>다양한 소통 가능</span>
				</li>
				
			</ul>
			
		</div>
		
		<div class = "Leporem_story">
			<div>
				<p class = "Leporem_story_title">Birth Story</p>
			</div>
			
			<div class = "Leporem_story_center" style="width: 100%; display: flex; flex-direction: row;">
				<div style="width: 50%; padding: 0; opacity: 100%;">
					<img src="img/leporem_log.jpg" style="width: 100%; height: 100%; padding: 0px;">
				</div>
				<div class = "Leporem_story_content" style="width: 50%; background-color: currentColor; display: flex; justify-content: center; align-items: center;">
					<span style="color: white;">
						Leporem은 1인 가구와 집에서 보내는 시간 증가로
						셀프 인테리어에 대한 관심도가 높아짐에 따라 서로의 인테리어 정보를 공유하거나
						전문적인 인테리어 업체가 컨설팅 도와주는 홈페이지로
						컨설팅 받은 후기를 회원들 간 공유하여 컨설팅 회사를 선택할 때 도움을 줄 수 있으며
						그 외에 다양한 정보들을 주고받으며 효율적이고 만족도 높은 인테리어를 위한 서비스를 제공을 하기 위해 만들어졌습니다.
					</span>
				</div>
			</div>
			
			
		</div>
		
		
		<div class = "Leporem_partners_top">
			<p class = "Leporem__partners_title"> Consulting review</p>
		</div>
		
		<div class="slider">
		    <input type="radio" name="slide" id="slide1" checked>
		    <input type="radio" name="slide" id="slide2">
		    <input type="radio" name="slide" id="slide3">
		    <input type="radio" name="slide" id="slide4">
		    <input type="radio" name="slide" id="slide5">
			    
		    <ul id="imgholder" class="imgs">
		        <li><img src="img/review1.jpg"/></li>
		        <li><img src="img/review2.jpg"/></li>
		        <li><img src="img/review3.jpg"/></li>
		        <li><img src="img/review4.jpg"/></li>
		        <li><img src="img/review5.jpg"/></li>
		    </ul>
		    
		    <div class="bullets">
		        <label for="slide1">&nbsp;</label>
		        <label for="slide2">&nbsp;</label>
		        <label for="slide3">&nbsp;</label>
		        <label for="slide4">&nbsp;</label>
		        <label for="slide5">&nbsp;</label>
		    </div>
		</div>
		
	</div>
	
	
</body>

</html>