<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<link rel="stylesheet" href="resources/css/popup.css" type="text/css"/>
<style></style>
</head>
<body>
<div style ="font-size:20pt" >이의제기처리 </div>
<hr>
<form action="comObjectionUpdate.do" method="get" onsubmit="return comObjectionUpdate()">
    <table class="dto">
        
           <tr>
				<th style="float:left">처리내용</th>
			</tr>
			<tr>
				<td>
					<textarea rows="10" cols="100" name="obj_content" id="obj_content"></textarea>
				</td>
			<tr>
            <tr>
                <th>
                	 <input type="hidden" name="obj_state" value="1">
   				     <input type="submit" value="처리하기" />
   				    <input type="button" value="닫기" onclick="pclose2()"/>
   					<input type="hidden" name="obj_no" value="${dto.obj_no}">
				</th>
  				</tr>
        </table>
    </form>		
</body>
<script>

function comObjectionUpdate()	{
		
		if($('#obj_content').val()==""){
			alert("이의제기 처리내용이 작성해주세요.");
			$("#obj_content").focus();
			return false;
		}else {
			alert("이의제기처리 완료되었습니다");
		}
}

var pclose = "${pclose}";
if(pclose){
	   opener.location.reload();
	    window.close();
}

function pclose2(){
	opener.location.reload();
    window.close();
}


</script>
</html>