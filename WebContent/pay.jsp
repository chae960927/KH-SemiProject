<%@page import="com.mypage_public.dto.MypagePublicDto"%>
<%@page import="com.mypage_public.dao.MypagePublicDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>결제화면</title>
		<link rel="icon" type="image/png" sizes="16x16" href="img/favicon-32x32.png">
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
		<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	</head>
	<body>
		<script>
			var IMP = window.IMP; // 생략 가능
			IMP.init('imp49350266');
			
			IMP.request_pay({
			    pg : 'html5_inicis', // 결제방식
			    pay_method : '${pay_list.pay_info }', //결제수단(pay_info)
			    merchant_uid : new Date().getTime(), //주문번호(pay_no)
			    name : '${pay_list.pay_name }', //주문명(pay_name)
			    amount : ${pay_list.pay_money }, //결제 금액(pay_money)
			    buyer_name : '${pay_list.pay_user }' //구매자(pay_user)
			}, function(rsp) {
			    if ( rsp.success ) {
			        var msg = '결제가 완료되었습니다.';
			     	// jQuery로 callback
			     	//debugger;
			        jQuery.ajax({
			            url: "PayController.do?command=pay_success",
			            method: "POST",
			            //imp_uid는 아임포트에서 자동으로 생성되는 인자
			            data: {
			            	"pay_no" : rsp.merchant_uid,
			            	"pay_user" : rsp.buyer_name,
			            	"pay_company" : ${pay_list.pay_company },
			            	"pay_name" : rsp.name,
			            	"pay_money" : rsp.paid_amount,
			            	"pay_info" : rsp.pay_method
			            },
			            success: function(result) {
		                    console.log("결제 성공");
		                    location.href="PayController.do?command=success&pay_user=" + rsp.buyer_name;
		                },
		                error : function(result) {
		                	console.log("결제 실패");
		                	alert('결제에 실패했습니다.');
		                	location.href='index.jsp';
		                }
			        })
	
			    } else {
			    	//alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
			    	alert("결제에 실패하였습니다.");
			    	location.href='index.jsp';
			    }
			});
		</script>
	</body>
</html>