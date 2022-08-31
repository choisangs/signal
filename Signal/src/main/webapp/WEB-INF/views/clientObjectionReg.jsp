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
		width: 700px;
	}
	
	#button1 {
	    position: relative;
	    top: 20px;
	    left: 300px;
	    font-size: 15px;
	}

	#button2 {
	    position: relative;
	    top: 20px;
	    left: 310px;
	    font-size: 15px;
	}
</style>
</head>
<body>
<form action="clientDbjectionReg.do" method="get"  onsubmit="return clientDbjectionReg()" >
    <table class="dto">
        	<tr><th colspan="2" ><div style ="font-size:20pt">이의제기 등록</div></th></tr>
            <tr>
                <th>채용공고제목</th>
                <td align="left">${dto.jpo_title} </td>
            </tr>
            <tr>
                <th>기업명</th>
                <td align="left">${dto.com_name} </td>
            </tr>
            <tr>
                <th>면접일</th>
                <td align="left">${dto.inter_date}</td>
            </tr>
            <tr>
                <th>면접현황</th>
                <td align="left">${dto.inter_result}</td>
            </tr>
            <tr>
                <th>평점</th>
                <td align="left">${dto.inter_avg}</td>
            </tr>
            <tr>
                <th>이의제기</th>
                <td align="left">
               <div style="color:#da0000;">입력란에 이의제기할 내용을 작성해주세요</div>
                <textarea rows="10" cols="30" name="obj_cl_content" id="obj_cl_content"></textarea>
                	<input type="hidden" name="inter_no" value="${dto.inter_no}">
                	</td>
           </tr>
        </table>
        <div style =align:center;>
        <input type="submit" id="button1" value="등록" id="btn"/>
        <input type="button" id="button2" value="닫기" onclick="pclose2()"/>
        </div>
    </form>		
</body>
<script>




function clientDbjectionReg()	{
		
		
		if($('#obj_cl_content').val()==""){
			alert("입련란이 비었습니다 확인주세요.");
			$("#obj_cl_content").focus();
			return false;
		}
		
		
}
function pclose2(){
	opener.location.reload();
    window.close();
}

var pclose = "${pclose}";
if(pclose){
	   opener.location.reload();
	    window.close();
}




</script>
</html>