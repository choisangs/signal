<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>입사제안 팝업창</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
table {
	width:100%;
	border:1px solid #787878;
	border-collapse:collapse;
}

table tr th {
	padding:10px;
	border:1px solid #787878;
	background-color:#efefef;
}
table tr td {
	padding:10px;
	border:1px solid #787878;
}
</style>
</head>
<body>
	<h3>팝업창 시도</h3>
	<form action="offer.do" name="jpoList" id="jpoList" method="get">
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>채용공고명</th>
				<th>입사제안</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${jobPostingList.size() >0}">
					<c:forEach items="${jobPostingList}" var="item" varStatus="status">
						<tr>
							<td><input id="jpo_no" type="hidden" name="jpo_no" value="${item.jpo_no}"/>${item.jpo_no}</td>
							<td>${item.jpo_title}</td>
							<td>
								<input type ="button" value="제안하기" onclick="goSubmit()"/>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
	     			<tr>
	     				<td colspan="10" style="text-align: center">등록된 공고가 없습니다.</td>
	     			</tr>
	     		</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	</form>
	
</body>
<script>

function goSubmit()
{
	var j = $("#jpo_no").val();
	var g = $('input[name="jpo_no"]').val();
	
	window.opener.name = "parentPage"; // 부모창의 이름 설정
    document.jpoList.target = "parentPage"; // 타켓을 부모창으로 설정
    document.jpoList.action = "offer.do?jpo_no="+g;
    document.jpoList.submit();
    self.close();
	/* 참고 : https://zero-gravity.tistory.com/247 */
	
	
	/* 참고 : https://serpiko.tistory.com/414 
	var form = document.popup;
	var title = "jpoList";
	var url = "/offer.do";	
	
	window.opener.name = title; //객체의 레퍼런스가 아닌 스트링을 사용한 이름을 지정해 주어야 함.
	form.target = title; 
	form.action = url;
	form.submit(); 
	self.close();
	*/
}



</script>
</html>