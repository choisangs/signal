<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

	textarea {
	   	width:100%;
	   	resize: none;
	   	height: 500px;
   }
   	table,h2,h6{
   		width: 60%;
   		margin: 0 auto;
   }
   	table,th,td{
	   	border: 1px solid gray;
	   	border-collapse: collapse;
   }
   	th{
   		font-size: 15px;
   		font-weight: bold;
   		height: 30px;
   		width: 20%;
   	}
   	input[type="text"],[type="url"]{
   		width: 100%;
   		padding: 3px;
   	}
	h2{
		margin-bottom: 10px;
		text-align: center;
	}
	.find-btn{
		text-align: center;
		margin-top: 10px;
	}
	.find-btn1,.find-btn2{
		display :inline-block;
		color: #fff;
		border-radius: 5px;
		width: 100px;
		height: 30px;
		
	}
	.find-btn1{
		background-color: #1f3864ff;
	}
	.find-btn2{
		background-color: #7f7f7fff;
	}
	
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
	
	#comTitle {
    position: relative;
    top: -330px;
    left: 200px;
    width: 500px;
	}
	
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
	
	#write {
    position: relative;
    top: -280px;
    left: 180px;
    width: 1200px;
	}

</style>
</head>
<body>
<section>
    <div id="jp-list">
    		<div id="comTitle">
       		<h2>기업정보 등록하기</h2>
	        </div>	
	        	<form action="/companyInfoWrite.do" method="post" enctype="multipart/form-data" id="write">
			        	<table id="infoWrite">
							<input type="hidden" name="com_id" value="${dto.com_id}"/></td>
				    		<tr>
								<th>기업명</th>
								<td><input type="text" name="com_name" value="${dto.com_name}" readonly/></td>
							</tr>
				    		<tr>
								<th>대표자명</th>
								<td><input type="text" name="ci_ceo" required /></td>
							</tr>
				   			<tr>
								<th>직원수</th>
								<td><input type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" name="ci_emp" required /></td>
							</tr>
				    		<tr>
								<th>홈페이지 주소</th>
								<td><input type="url" name="ci_web" required/></td>
							</tr>
				   			<tr>
								<th>기업로고</th>
								<td>
									<input type="file" name="ci_photo" multiple="multiple" />
								</td>
							</tr>
				    		<tr>
								<th>기업소개</th>
								<td><input type="text" name="ci_intro" required/></td>
							</tr>
				    		<tr>
								<th colspan="2">합격자소서</th>
							</tr>
							<tr>
								<td colspan="2">
									<textarea name="ci_pass_intro" maxlength="1000" required></textarea>
								</td>
							</tr>
			        	</table>
			        	<div class="find-btn">
		        			<input class="find-btn1" type="submit" value="등록하기"/>
		        			<input class="find-btn2" type="button" value="돌아가기" onclick="location.href='/companyInfo.go'"/>
						</div>
	        	</form>
        </div>
   </section>
</body>
<script>

</script>
</html>