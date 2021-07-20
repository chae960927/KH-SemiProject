<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>신고 게시물 내용 상세</title>
		<style type="text/css">
			img {
				max-width: 100%;
				max-height: 100%;
			}
		</style>
	</head>
	<body>
		<table border="1">
			<tr>
				<th>게시글 내용</th>
				<td>
					<div id="content">
						
					</div>
					<script type="text/javascript">
						var rep_content = opener.document.getElementById("rep_content").value;
						document.getElementById("content").innerHTML = rep_content;
					</script>
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="button" value="확인" onclick="self.close();">
				</td>
			</tr>
		</table>
	</body>
</html>