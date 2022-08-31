<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../../resources/inc/header.jsp" %>
<style>
	#section {
		width : 800px;
		position: relative;
		top : -380px;
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
		<h2>셀프평가관리</h2>
		<table>
		<thead>
			<tr>	
				<th colspan="3">
					<input type="button" onclick="selfQueReg()" value="등록"/>
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
			<c:forEach items="${list}" var="self">
				<tr id="st_${self.st_no}">
					<td class="st_no" id="hidden">${self.st_no}</td>
					<td>${self.st_que }</td>
					<td>${self.st_keyword }</td>					
					<td>					
					<select name="st_hidden">										
						<option value="0" ${self.st_hidden==0?'selected="selected"':''}>숨김</option>
						<option value="1" ${self.st_hidden==1?'selected="selected"':''}>노출</option>
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
	location.href="selfTestQue.go";	
}

function listShow(){
	location.href="selfTestQueB.go";	
}

function listHide(){
	location.href="selfTestQueA.go";	
}

$('select[name="st_hidden"]').change(function(){
	var st_no = $(this).parent().parent().find('.st_no').html();
	var st_hidden = $(this).val();
	console.log($("#state").val());
	var state = $("#state").val(); 
	location.href="stHiddenUp.do?st_no="+st_no+"&&st_hidden="+st_hidden+"&&state="+state;
});


function selfQueReg(){
	window.open("selfTestReg.go","new","width=850, height=400, resizable=no, scrollbars=no, status=no, location=no, directories=no;");
}

</script>
</html>