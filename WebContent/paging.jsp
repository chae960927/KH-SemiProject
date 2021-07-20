<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>페이징 처리</title>
	</head>
	<body>
		<div id="paging" style="margin-top: 2%;">
		<!-- 1~10까지 있는 페이지의 페이징 -->
		<c:choose>
			<c:when test="${param.conName eq 'notice'}"><c:url var="action" value="/NoticeController.do"/></c:when>
			<c:when test="${param.conName eq 'info' }"><c:url var="action" value="/InfoController.do"/></c:when>
			<c:when test="${param.conName eq 'sup' }"><c:url var="action" value="/SupController.do"/></c:when>
			<c:when test="${param.conName eq 'con' }"><c:url var="action" value="/ConController.do"/></c:when>
		</c:choose>
		<c:if test="${param.prev}">
	    	<a href="${action}?command=list&page=${param.beginPage-1}" style="font-size:20px; margin-right: 5px;">prev</a>
		</c:if>
		<c:forEach begin="${param.beginPage}" end="${param.endPage}" step="1" var="index">
	    	<c:choose>
	        	<c:when test="${param.page==index}">
	            	<span style="font-size: 20px; color: gray; margin: 5px;">${index}</span>
	        	</c:when>
	        	<c:otherwise>
	            	<a href="${action}?command=list&page=${index}" style="font-size:20px; margin:5px;">${index}</a>
	        	</c:otherwise>
	    	</c:choose>
		</c:forEach>
		<c:if test="${param.next}">
	   		<a href="${action}?command=list&page=${param.endPage+1}" style="font-size:20px; margin-left: 5px;">next</a>
		</c:if>
		</div>
	</body>
</html>