<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="resources/css/common.css" type="text/css"/>
<style>
	body{
		width:800px;
	}

	.hidden {
		display : none;
	}
</style>
</head>
<body>	
	<table style="width:49.5%; float:left;">
		<thead>
			<tr>
				<th>	대분류	</th>				
			</tr>
		</thead>
		<tbody id="jobClass">
			<c:forEach items="${jpList}" var="jp">
				<tr>									
					<td><button onclick="jpClick(${jp.jp_no})">${jp.jp_name}</button></td>					
				</tr>
			</c:forEach>			
		</tbody>
	</table>
	<table style="width:49.5%; float:right;">
		<thead>
			<tr>
				<th>	중분류	</th>				
			</tr>
		</thead>
		<tbody id="jobClass">
			<c:forEach items="${jcList}" var="jc">
				<tr>									
					<td><button onclick="jcClick(${jc.jc_no})">${jc.jc_name}</button></td>					
				</tr>
			</c:forEach>			
		</tbody>
	</table>
	<table class="hidden">
		<c:forEach items="${finList}" var="fin">
			<tr id="fin_${fin.jc_no}">									
				<td id="jp_no">${fin.jp_no}</td>
				<td id="jp_name">${fin.jp_name}</td>
				<td id="jc_no">${fin.jc_no}</td>
				<td id="jc_name">${fin.jc_name}</td>
			</tr>
		</c:forEach>	
	</table>
</body>
<script>
	
	
	function jpClick(no){
		location.href="jcList.go?jp_no="+no;		
	}
	
	function jcClick(no){
		location.href="jcCheck.go?jc_no="+no;		
	}
	
	var success="${success}";
	if(success){
		alert("직무선택이 완료되었습니다");
		var jc_no=$("#jc_no").html();
		console.log(jc_no);
		$tr = $("#fin_"+jc_no);
		console.log($tr);
		
		opener.document.getElementById("jp_no").value = $tr.find('#jp_no').text();
		opener.document.getElementById("jp_name").value = $tr.find('#jp_name').text();
		opener.document.getElementById("jc_no").value = $tr.find('#jc_no').text();
		opener.document.getElementById("jc_name").value = $tr.find('#jc_name').text();
		
		window.close();
	}

</script>
</html>