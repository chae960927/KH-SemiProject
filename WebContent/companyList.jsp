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
		    				<th>주문자</th>
		    				<th>기업명</th>
		    				<th>결제일</th>
		    				<th>컨설팅</th>
		    			</tr>
		    			<c:choose>
							<c:when test="${empty company_list }">
								<tr>
									<td colspan="8">-----컨설팅 내역이 없습니다.-----</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="company_list" items="${company_list }">
									<tr>
										<td class="num">${company_list.pay_no }</td>
							            <td class="writer">${company_list.pay_user }</td>
							            <td class="company">${company_list.pay_company }</td>
							            <td class="date">${company_list.pay_regdate }</td>
							            <td class="chat">
							            	<button id="chat_${company_list.pay_no }" onclick="chatStart('chatcontroller.do?command=chatForm&chat_num=${company_list.pay_no }&from_user=${company_list.pay_company }&to_user=${company_list.pay_user }');">채팅</button>
							            </td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
		    		</table>
				        
		    		</div>
		    	</div>
			</div>
		</div>
	</body>
</html>