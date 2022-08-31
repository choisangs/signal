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
   		width: 70%;
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
		width: 500px;
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
	img.comLogo{
		 width: 100%;
	}
	
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
	
	#jp-list {
	    position: relative;
	    top: -350px;
	    left: 155px;
	    width: 1200px;
	}
	
	table {
		width: 900px;
	}

</style>
</head>
<body>
<section>
    <div id="jp-list">
	        	<h2>기업정보 수정하기</h2>	
	        	<form action="/companyInfoUpdate.do" method="post" enctype="multipart/form-data">
		        	<div>
			        	<table id="comUpdate">
				    		<tr>
								<th>기업명</th>
								<td><input type="text" name="com_name" value="${dto.com_name}" required /></td>
							</tr>
				    		<tr>
								<th>대표자명</th>
								<td><input type="text" name="ci_ceo" value="${dto.ci_ceo}" required /></td>
							</tr>
				   			<tr>
								<th>직원수</th>
								<td><input type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" name="ci_emp" value="${dto.ci_emp}" required /></td>
							</tr>
				    		<tr>
								<th>홈페이지 주소</th>
								<td><input type="url" name="ci_web" value="${dto.ci_web}" required/></td>
							</tr>
				   			<tr>
								<th rowspan="2">기업로고</th>
								<td>
									<input type="file" name="ci_photo" multiple="multiple" src="/photo/${dto.ci_photo}" value="${dto.ci_photo}" width="200"/>
								</td>
							</tr>
								<tr>
									<td>
										<p><img class="comLogo" src="/photo/${dto.ci_photo}" alt="기업로고" ></p>
									</td>
							</tr>
				    		<tr>
								<th>기업소개</th>
								<td><input type="text" name="ci_intro" value="${dto.ci_intro}" required/></td>
							</tr>
				    		<tr>
								<th colspan="2">합격자소서</th>
							</tr>
							<tr>
								<td colspan="2">
									<textarea name="ci_pass_intro" maxlength="100%" required>${dto.ci_pass_intro}</textarea>
								</td>
							</tr>
			        	</table>
			        	<div class="find-btn">
		        			<input class="find-btn1" type="submit" value="수정하기"/>
		        			<input class="find-btn2" type="button" value="돌아가기" onclick="location.href='/companyInfo.go'"/>
						</div>
	        		</div>
	        	</form>
        </div>
   </section>
</body>
<script>

</script>
</html>