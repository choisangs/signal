<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header_jobPosting.jsp" %>
<style>
	
 	#wrap {overflow: hidden;}
 	div > button {
		border: none;
		color: black;
		padding: 15px 32px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 40px;
		margin: 0px 10px;
		cursor: pointer;
		border-radius: 10px;
		font-weight: bold;
 	}
 	.buttonclient {
 		width: 250px;
 		height: 250px;
 		background-color: #01A9DB;
 	}
 	.buttoncompany {
 		width: 250px;
 		height: 250px;
 		background-color: #5FB404;
 	}
 	
 	h {
 		positon: relative;
 	}
 	
 	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
</style>
<body>
	<div id="joinsection">
		<h1><strong>회원가입 - 회원선택</strong></h1><br><br>
		<div style=" text-align: center;">
			<button class="buttonclient" onclick="location.href='joinFormClient.go'">개인회원</button>
			<button class="buttoncompany" onclick="location.href='joinFormCompany.go'">기업회원</button>
		</div>
	</div>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<script>

</script>