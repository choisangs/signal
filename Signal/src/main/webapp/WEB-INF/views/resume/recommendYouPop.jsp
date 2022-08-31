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

</style>
</head>
<body>	
	<form action="recommendUReg.do" method="post">
		<table>		
			<tr>
				<th>ID</th>
				<td>
					<input type="text" name="cl_id" value="${cl_id}" readonly>
					<input type="hidden" name="reco_no" value="${reco_no}"/>
					<input type="hidden" name="reco_state" value="${reco_state}"/>
				</td>
				<th>관계</th>
				<td><input type="text" name="reco_relation"/></td>				
				<th>추천내용</th>
				<td>
					<input type="text" name="reco_req_memo"/>
					<input type="hidden" name="reco_date"/>
				</td>
			</tr>
			<tr>
				<th colspan="6">
					<input type="submit" value="응답하기"/>
					<button onclick="window.close()">닫기</button>
				</th>
			</tr>
		</table>
	</form>	
</body>
<script>
	dateCall();
	function dateCall(){
		var now = new Date();
		console.log(now);
		var year = now.getFullYear();
		var month = now.getMonth()+1;
		if(month<10){
			month= "0"+month;
		}
		var date = now.getDate();
		if(date<10){
			date= "0"+date;
		}
		var nowDate = year+'-'+month+'-'+date;
		console.log(nowDate);
	$('input[name="reco_date"]').val(nowDate);
	}
	
	var msg = "${success}";
	if(msg){
		alert("응답이 완료되었습니다");
		window.opener.location.reload();
		window.close(); 
	} 
</script>
</html>