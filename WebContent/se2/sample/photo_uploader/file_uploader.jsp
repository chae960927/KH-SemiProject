<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>싱글 이미지 업로더</title>
	</head>
	<body>
		<%
			// 로컬경로에 파일 저장하기 ============================================
			String return1 = "";
			String return2 = "";
			String return3 = "";
			String name = "";
			String contextPath = request.getServletContext().getContextPath();
			// multipart로 전송되었는가 체크
			if(ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
				// UTF-8 인코딩 설정
				uploadHandler.setHeaderEncoding("UTF-8");
				List<FileItem> items = uploadHandler.parseRequest(request);
				// 각 필드태그들을 FOR문을 이용하여 비교를 합니다.
				for(FileItem item : items) { 
					if(item.getFieldName().equals("callback")) {
						return1 = item.getString("UTF-8");
					} else if(item.getFieldName().equals("callback_func")) {
						return2 = "?callback_func="+item.getString("UTF-8");
					} else if(item.getFieldName().equals("Filedata")) {
						// FILE 태그가 1개이상일 경우
						System.out.println(item.getFieldName() + " = " + item.getSize());
						if(item.getSize() > 0) {
							// 확장자
							String ext = item.getName().substring(item.getName().lastIndexOf(".")+1);
							// 파일 기본경로
							String defaultPath = request.getServletContext().getRealPath("/");
							// 파일 기본경로 _ 상세경로
							String path = defaultPath + "upload" + File.separator;
							File file = new File(path);
							// 디렉토리 존재하지 않을경우 디렉토리 생성
							if(!file.exists()) {
								file.mkdirs();
							}
							// 서버에 업로드 할 파일명(한글문제로 인해 원본파일은 올리지 않는것이 좋음)
							String realname = UUID.randomUUID().toString() + "." + ext;
							///////////////// 서버에 파일쓰기 /////////////////
							InputStream is = item.getInputStream();
							OutputStream os=new FileOutputStream(path + realname);
							int numRead;
							byte b[] = new byte[(int)item.getSize()];
							while((numRead = is.read(b,0,b.length)) != -1) {
								os.write(b,0,numRead);
							}
							if(is != null) is.close();
							os.flush(); os.close();
							System.out.println("path : "+path);
							System.out.println("realname : "+realname);
							path = URLEncoder.encode(path.replace("\\", File.separator), "utf-8");
							///////////////// 서버에 파일쓰기 /////////////////
							System.out.println("filename = " + (contextPath + "/upload/" + realname));
							return3 += "&bNewLine=true&sFileName="+name+"&sFileURL=" + contextPath + "/upload/" + realname;
							
						} else {
							//return3 += "&errstr=error";
						}
					}
				}
				System.out.println("return1 = " + return1);
				System.out.println("return2 = " + return2);
				System.out.println("return3 = " + return3);
			}
			String returnURL = return1+return2+return3;
			if (returnURL.indexOf(contextPath) != 0) {
				returnURL = contextPath + returnURL;
			}
			response.sendRedirect(returnURL);
			// ./로컬경로에 파일 저장하기 ============================================
		%>
		<script>
		
		</script>
	</body>
</html>
