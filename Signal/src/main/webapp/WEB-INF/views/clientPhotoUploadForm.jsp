<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<form action="clientPhotoUpload.do" method="post"  enctype="multipart/form-data">
		<input type="file" name="file" onchange="fileUpload()"/>
	</form>
</body>
<script>
	//파일 업로드하기
	function fileUpload(){
		$("form").submit();
	}
	
	var path = "${path}";
	
	if(path != ""){
		var content = '<a href="#" id="${path}" onclick="del(this)">';
		content += '<img src="'+path+'" height="150"/>';
		content += '</a>'
		opener.document.getElementById("editable").innerHTML += content;
		self.close();
	}
	
</script>
</html>