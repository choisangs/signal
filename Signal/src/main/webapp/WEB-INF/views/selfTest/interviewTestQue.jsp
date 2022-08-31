<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../../resources/inc/header.jsp" %>
<style>
	#section {
		width : 800px;
		position: relative;
		top : -350px;
		left : 350px;
	}
	
	#hidden {
		display : none;
	}
	
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
	
	
</style>
<body>
	<div id="section">
	<h2>면접평가관리</h2>
	<table>
		<thead>
			<tr>	
				<th colspan="3">
					<input type="button" onclick="interviewQueReg()" value="등록"/>
					<div style="float:right;">
					<input type="button" onclick="listAll()" value="전체"> 
					<input type="button" onclick="listShow()" value="노출">
					<input type="button" onclick="listHide()" value="숨김">
					<input type="hidden" id="state" value="${state}">
					</div>
				</th>
			</tr>
			<tr>
				<th id="hidden"></th>
				<th>질문</th>
				<th>키워드</th>
				<th>노출상태</th>
			</tr>
		</thead>
		<tbody id="list">
			<c:forEach items="${list}" var="inter">
				<tr>
					<td class="it_no" id="hidden">${inter.it_no }</td>
					<td>${inter.it_que }</td>
					<td>${inter.it_keyword }</td>
					<td>
						<select name="it_hidden">										
							<option value="0" ${inter.it_hidden==0?'selected="selected"':''}>숨김</option>
							<option value="1" ${inter.it_hidden==1?'selected="selected"':''}>노출</option>
						</select>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
<script>
var state;

function listAll(){
	location.href="interviewTestQue.go";	
}

function listShow(){
	location.href="interviewTestQueB.go";	
}

function listHide(){
	location.href="interviewTestQueA.go";	
}

$('select[name="it_hidden"]').change(function(){
	var it_no = $(this).parent().parent().find('.it_no').html();
	var it_hidden = $(this).val();
	console.log($("#state").val());
	var state = $("#state").val(); 
	location.href="itHiddenUp.do?it_no="+it_no+"&&it_hidden="+it_hidden+"&&state="+state;
});


function interviewQueReg(){
	window.open("interviewTestReg.go","new","width=850, height=400, resizable=no, scrollbars=no, status=no, location=no, directories=no;");
}

</script>
</html>