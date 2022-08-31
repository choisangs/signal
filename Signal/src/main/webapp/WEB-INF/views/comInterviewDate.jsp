<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="resources/css/common.css" type="text/css"/>
<link rel="stylesheet" href="resources/css/popup.css" type="text/css"/>
<style>
	body {
		width: 400px;
	}
</style>
</head>
<body>
<form action="comInterviewDate.do" method="get"  onsubmit="return comInterviewDate()">
	<table class="dto">
		<colgroup>
			<col width="150"></col>
			<col width="150"></col>
		</colgroup>
		<tr>
			<th>해당 면접일</th>
			<td> ${dto.inter_date} </td>
		</tr> 
        <tr>
			<th>변경 면접일</th>
			<td>
				<input type="hidden" name="inter_no" value="${dto.inter_no}">
				<input type="date" id="inter_date" name="inter_date" value=""
                    min="2022-01-01" max="2023-12-31">
			</td>
		</tr>
		<tr>
			<th colspan="2">
				 <input type="submit" value="변경"/>
		        <input type="button" value="닫기" onclick="pclose2()"/>
			</th>
		</tr>
    </table>
    
		
	</form>
</body>
<script>
function comInterviewDate()	{
	
	if($('#inter_date').val()==""){
		alert("변경 면접일이 선택되지 않았습니다.");
		return false;
	}
}

var pclose = "${pclose}";
if(pclose){
	   opener.location.reload();
	    window.close();
}

function pclose2(){
	opener.location.reload();
    window.close();
}


</script>
</html>