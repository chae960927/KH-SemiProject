<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.login.dto.UserDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>일정관리</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<link href="./css/fullcalendar.min.css" rel="stylesheet"/>
		<link href="./css/fullcalendar.print.min.css" rel="stylesheet" media="print"/>
		<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css" rel="stylesheet" />
		<style type="text/css">
			.fc-sat { color:#0000FF !important; }     /* 토요일 */
    		.fc-sun { color:#FF0000 !important; }    /* 일요일 */
		</style>
		<script src="./js/moment.min.js"></script>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js" type = "text/javascript"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"></script>
		<script src="./js/fullcalendar.min.js"></script>
		<script src="./js/fullcalendar_ko.js"></script>
		<script src="./js/bPopup.js"></script>
		<script src="./js/calendar.js"></script>
	</head>
	<body>
		<h2 style="text-align: center;"><a href="index.jsp" style="text-decoration: none; color: black">Leporem</a></h2>
		<div style="max-width:900px; margin:0 auto; height:30px">
			<div style="float:right;">
				<button onclick="history.back();">마이페이지</button>
				<button onclick="addSchedule();">일정 등록</button>
			</div>
		</div>
		<div id="calendar" style="max-width:900px; margin:0 auto;">
		</div>
		<div class="box box-success" style="width:500px; display:none; background-color:white;" id="winAlert">
			<div class="box-header with-border" style="background-color: white; padding-left:15px;">
				<h3 class="box-title" id="alert_name" style="background-color:white"></h3>
			</div>
			<div class="box-body" id="alert_content" style="font-size: 15px; background-color:white">
			</div>
		</div>
	</body>
</html>