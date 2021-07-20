<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>결제 내역 조회</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script type="text/javascript">
			function chatStart(url) {
				open(url, "채팅방", "width=500, height=700, location=no, resizable=no");
			}
		</script>
	</head>
	<body>
		<div class="center">
			<div class="board_wrap">
				<div class="board_title">
		        	<strong>결제내역</strong>
		    	</div>
		    	<div class="board_list_wrap">
		    		<table>
		    			<tr>
		    				<th>주문번호</th>
		    				<th>주문명</th>
		    				<th>주문자</th>
		    				<th>기업명</th>
		    				<th>결제금액</th>
		    				<th>결제수단</th>
		    				<th>결제일</th>
		    				<th>컨설팅</th>
		    			</tr>
		    			<c:choose>
							<c:when test="${empty pay_list }">
								<tr>
									<td colspan="8">-----결제내역이 없습니다.-----</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="pay_list" items="${pay_list }">
									<tr>
										<td class="num">${pay_list.pay_no }</td>
							            <td class="title">${pay_list.pay_name }</td>
							            <td class="writer">${pay_list.pay_user }</td>
							            <td class="company">${pay_list.pay_company }</td>
							            <td class="amount">${pay_list.pay_money }</td>
							            <td class="info">${pay_list.pay_info }</td>
							            <td class="date">${pay_list.pay_regdate }</td>
							            <td class="chat">
							            	<button id="chat_${pay_list.pay_no }" onclick="chatStart('chatcontroller.do?command=chatForm&chat_num=${pay_list.pay_no }&from_user=${pay_list.pay_user }&to_user=${pay_list.pay_company }');">채팅</button>
							            </td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
		    		</table>
		    	</div>
			</div>
		</div>
	</body>
</html>