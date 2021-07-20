<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>프로필 이미지 업로더</title>
	</head>
	<body>
		<div>프로필로 지정할 이미지를 선택하세요.</div>
		<div>
			<form action="PublicController.do?command=imgUpload" method="post" enctype="multipart/form-data">
				<input type="hidden" name="user_id" value="<%=request.getAttribute("user_id")%>">
				<input type="file" name="user_img">
				<input type="submit" value="선택" >
			</form>
		</div>
	</body>
</html>
