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
	
	button {
   padding:5px 10px;
   font-size:12px;
   outline:none;
   border:none;
   color:#fff;
   background-color:#333;
	}
</style>
</head>
<body>	
	<table style="width:790px;">
		<tr>
			<th>요청할 ID</th>
			<td>
				<input type="text" id="reco_cl_id" placeholder="정확한 ID를 입력하세요"/>
				<input type="hidden" id="cl_id" value="${cl_id}"/>
			</td>
			<td><button onclick="idOverlay()">확인</button></td>
			<th>요청메모</th>
			<td><input type="text" id="reco_content"/></td>
		</tr>
		<tr>
			<th colspan="5">
				<button onclick="recommendReg()" >요청하기</button>
				<button onclick="window.close()">닫기</button>
			</th>
		</tr>
	</table>	
</body>
<script>
	var overChk = false;
	
	function idOverlay(){
		var $reco_cl_id = $('#reco_cl_id').val();
		
		$.ajax({
			type:'get',
			url:'overlay.ajax',
			data:{chkId:$reco_cl_id},
			dataType:'JSON',
			success:function(data){
				if(data.overlay){
					alert("요청가능한 아이디입니다.");
					overChk = true;
				} else {
					alert("아이디를 확인해주세요.");					
				}
			},
			error:function(e){
				console.log(e);
			}			
		});
	}

	function recommendReg(){
		var $reco_cl_id = $('#reco_cl_id').val();
		var $cl_id = $('#cl_id').val();
		var $reco_content = $('#reco_content').val();
		
		console.log($reco_cl_id+'/'+$cl_id+'/'+$reco_content);
		
		if($reco_cl_id==""){
			 alert('요청할 ID를 입력해주세요!');
			 $reco_cl_id.focus();
		 }else if(!overChk){
			 alert('ID 중복을 확인해주세요!');
			 $reco_cl_id.focus();	 
		 }else if($reco_content ==""){
			 alert('요청메모를 입력해주세요');
			 $reco_content.focus();
		 }else{
			 $.ajax({
				 type:"post",
				 url:"recommendReg.ajax",
				 data:{
					 reco_cl_id:$reco_cl_id,
					 cl_id:$cl_id,
					 reco_content:$reco_content
				 },
				 dataType:'JSON',
				 success:function(data){
					 if(data.success){
							alert("추천요청이 완료되었습니다!");
							window.opener.location.reload();
							window.close();
						} else {
							alert("추천요청에 실패했습니다.");							
						}
				 },
				 error:function(e){
					 console.log(e);
				 }
			 });
		 }
		
	}

</script>
</html>