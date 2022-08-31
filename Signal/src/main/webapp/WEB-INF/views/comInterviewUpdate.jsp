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
		width: 800px;
	}
	/* 왼쪽 버튼 */
	#button3 {
		position: relative;
		left: 330px;
	}
	
	/* 오른쪽 버튼 */
	#button4 {
		position: relative;
		left: 350px;
}
</style>
</head>
<body>
<form action="comInterviewUpdate.do" method="post" >
    <table class="dto" id="list">
           <tr>
				<th>면접결과선택</th>
				<td align="left">
					<select name="inter_result" id="inter_result">
					<option value=""${dto.inter_result == '' ? 'selected="selected"' : ''}>결과선택</option>
	                <option value="불합격"${dto.inter_result == '불합격' ? 'selected="selected"' : ''}>불합격</option>
	                <option value="면접합격"${dto.inter_result == '면접합격' ? 'selected="selected"' : ''}>면접합격</option>
	                <option value="최종합격"${dto.inter_result == '최종합격' ? 'selected="selected"' : ''}>최종합격</option>   	
	                </select>
				</td>
			</tr>
            <tr>
                <th>질문별 점수 선택</th>
                <td>
                	<table>
	                	<c:forEach items="${que}" var="que" >
		                    <tr>
		                        
		                        <td align="center">
		                        <input type="hidden" name="inter_no" value="${dto.inter_no}">
		                        <input type="hidden" name="it_no" value="${que.it_no}">
		                        ${que.it_que}</td>
		                        <td id="hh" align="center">
			                        <select name="inter_score2" id="ss_${que.it_no}">
										<option value="1"${que.inter_score == '1' ? 'selected="selected"' : ''}>1</option>
										<option value="2"${que.inter_score == '2' ? 'selected="selected"' : ''}>2</option>
										<option value="3"${que.inter_score == '3' ? 'selected="selected"' : ''}>3</option>
										<option value="4"${que.inter_score == '4' ? 'selected="selected"' : ''}>4</option>
										<option value="5"${que.inter_score == '5' ? 'selected="selected"' : ''}>5</option>
										<option value="6"${que.inter_score == '6' ? 'selected="selected"' : ''}>6</option>
										<option value="7"${que.inter_score == '7' ? 'selected="selected"' : ''}>7</option>
										<option value="8"${que.inter_score == '8' ? 'selected="selected"' : ''}>8</option>
										<option value="9"${que.inter_score == '9' ? 'selected="selected"' : ''}>9</option>
										<option value="10"${que.inter_score == '10' ? 'selected="selected"' : ''}>10</option>
					                </select>
		                        </td> 
		                    </tr>
						</c:forEach>
                	</table>
                </td>
            </tr>
         
              <tr>
                <th>코멘트작성</th>
                <td>
               		 <textarea rows="10" cols="40" name="inter_comment"  id="inter_comment">${dto.inter_comment}</textarea>
                </td>
           </tr>
        </table>
        <br>
        <input type= "button" id="button3" value="등록/수정" onclick="getResult()">
        <input type="button" id="button4" value="닫기" onclick="pclose2()"/>
    </form>		
</body>
<script>
var pclose = "${pclose}";
if(pclose){
	   opener.location.reload();
	    window.close();
}

function pclose2(){
	opener.location.reload();
    window.close();
}


var arr1 = [];
var arr2 = [];
var arr3 = [];	

function getResult(){		
	for (var i = 0; i < $('#list').find('input[name="it_no"]').length; i++) {			
		arr1[i] = $('#list').find('input[name="inter_no"]')[i].value
		arr2[i] = $('#list').find('input[name="it_no"]')[i].value
		arr3[i] = $('#list').find('select[name="inter_score2"]')[i].value	
	}
	var inter_comment = $('#inter_comment').val();
	var inter_result = $('#inter_result').val();
	var obj={};
	obj.inter_no=arr1;
	obj.it_no=arr2;
	obj.inter_score2=arr3;
	
	obj.inter_comment=inter_comment;
	obj.inter_result=inter_result;
	
	var param ={"values":obj};		
	console.log(param);
	
	$.ajax({
		type:'post',
		url:'rest/comUp',
		data:JSON.stringify(param),
		dataType:'JSON',
		contentType:'application/json; charset=utf-8',
		success:function(data){
			console.log(data);
			alert("수정 되었습니다");
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