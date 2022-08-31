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
<form action="selfReg.do" method="post">
	<table>
		<thead>
			<tr>
				<th>질문</th>
				<th>점수</th>
			</tr>
		</thead>
		<tbody id="list">
			<c:forEach items="${list}" var="selfTest">
				<tr id="st_${selfTest.st_no}">
					<td>
						<input type="hidden" name="cl_id" id="cl_id" value="${cl_id}"/>
						<input type="hidden" name="st_no" id="st_no" value="${selfTest.st_no}"/>${selfTest.st_que}
					</td>
					<td id="hh">
						<select name="st_score" id="ss_${selfTest.st_no }">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
						</select>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<th colspan="2">셀프코멘트</th>				
			</tr>
			<tr>
				<td colspan="2"><textarea id="st_comment" name="st_comment"></textarea></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="button" onclick="getResult()" value="완료"/>
					<input type="button" onclick="window.close()" value="닫기"/>
				</th>				
			</tr>
		</tbody>
	</table>	
</form>
</body>
<script>
	/* $('select[name="st_score"]').change(function(){
		$tr = $(this).parent().parent();
		console.log($tr);			
		var cl_id = $tr.find('#cl_id').val();
		var st_no = $tr.find('#st_no').val();
		var st_score = this.value;
		console.log(cl_id + '/' + st_no + '/' + st_score);
		location.href="scoreReg.do?cl_id="+cl_id+"&&st_no="+st_no+"&&st_score="+st_score;
	}) */
	var arr1 = [];
	var arr2 = [];
	var arr3 = [];	
	
	function getResult(){		
		for (var i = 0; i < $('#list').find('input[name="st_no"]').length; i++) {			
			arr1[i] = $('#list').find('input[name="cl_id"]')[i].value
			arr2[i] = $('#list').find('input[name="st_no"]')[i].value
			arr3[i] = $('#list').find('select[name="st_score"]')[i].value			
		}
		var st_comment = $('#st_comment').val()
		var obj={};
		obj.cl_id=arr1;
		obj.st_no=arr2;
		obj.st_score=arr3;
		obj.st_comment=st_comment;
		var param ={"values":obj};		
		console.log(param);
		
		$.ajax({
			type:'post',
			url:'rest/stReg',
			data:JSON.stringify(param),
			dataType:'JSON',
			contentType:'application/json; charset=utf-8',
			success:function(data){
				console.log(data);
				alert("등록이 완료되었습니다");
				window.opener.location.reload();
				window.close();
			},
			error:function(e){
				console.log(e);
			}		
		});
		
		
	}
</script>
</html>