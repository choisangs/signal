<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="resources/css/common.css" type="text/css"/>
<link rel="stylesheet" href="resources/css/popup.css" type="text/css"/>
<style>
body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
body {
	width: 500px;
}
</style>
</head>
<body>
	<h3>면접상태</h3>
	<form action="interviewSave.do" id="save" method="get" onsubmit="return interviewSave()">
	<table>
		<thead>
		  <tr>
		    <th>면접날짜</th>
		    <td>
		    	<input type="date" name="inter_date" id="inter_date"  value="${interResult.inter_date}"/>
		    	<input type="hidden" name="inter_no" value="${interResult.inter_no}"/>
		    </td>
		  </tr>
		</thead>
		<tbody>
		  <tr>
		    <th>면접상태</th>
		    <td>
		    	<select name="inter_result" id="inter_result">
		    		<option ${interResult.inter_result == '면접예정'? 'selected="selected"' : ''}>면접예정</option>
		    		<option ${interResult.inter_result == '서류탈락'? 'selected="selected"' : ''}>서류탈락</option>
		    		<option ${interResult.inter_result == '면접합격'? 'selected="selected"' : ''}>면접합격</option>
		    		<option ${interResult.inter_result == '최종합격'? 'selected="selected"' : ''}>최종합격</option>
		    		<option ${interResult.inter_result == '불합격'? 'selected="selected"' : ''}>불합격</option>
		    	</select>
		    </td>
		  </tr>
		</tbody>
		<tr>
			<th colspan="2">
				<input type="submit" value="등록하기"/>
				<input type="button" value="닫기" onclick="pclose2()"/>
			</th>
		</tr>
	</table>
	</form>
</body>
<script> 
	 function interviewSave(){
			if($('#inter_date').val()==""){
				alert("변경 면접일이 선택되지 않았습니다.");
				return false;
			}else if(confirm("정말로 등록하시겠습니까?")==false){
				
				return false;
			}
	 }
	 
	// by태섭, 팝업창 닫기
	var popupClose = "${popupClose}";
	if(popupClose){
	// by태섭, 부모창 새로고침, reload 대신 href를 사용하면 내가 가고싶은 주소로 이동
	opener.document.location.reload();
	window.close();
	}
 
	

	function pclose2(){
		opener.location.reload();
	    window.close();
	}
	
	
	
	
	
	
	
</script>
</html>



















