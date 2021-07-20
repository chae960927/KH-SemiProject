<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>결제 정보 입력</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
		<%
			String pay_user = (String) request.getAttribute("pay_user");
			String pay_company = (String) request.getAttribute("pay_company");
		%>
		<form action="PayController.do?command=pay" method="post">
			<table>
				<tr>
					<td><label for="pay_user">결제자 정보: </label></td>
					<td><input type="text" name="pay_user" id="pay_user" value="<%=pay_user%>" readonly="readonly"></td>
				</tr>
				<tr>
					<td><label for="pay_company">기업 정보: </label></td>
					<td><input type="text" name="pay_company" id="pay_company" value="<%=pay_company%>" readonly="readonly"></td>
				</tr>
				<tr>
					<td><label for="pay_name">주문 정보: </label></td>
					<td><input type="text" name="pay_name" id="pay_name" value="<%=pay_company %>기업의 컨설팅" readonly="readonly"></td>
				</tr>
				<tr>
					<td><label for="pay_money">결제 금액: </label></td>
					<td>
						<select name="pay_money" id="pay_money">
							<option value="10000">1시간</option>
							<option value="20000">2시간</option>
							<option value="30000">3시간</option>
							<option value="100000">종일권</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label for="pay_info">결제 수단: </label></td>
					<td>
						<select name="pay_info" id="pay_info">
							<option value="card">카드결제</option>
							<option value="trans">실시간계좌이체</option>
							<option value="vbank">가상계좌</option>
							<option value="kakaopay">카카오페이</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="결제">
						<input type="button" value="취소" onclick="history.back();">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>