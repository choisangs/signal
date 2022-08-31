<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!-- 출력 확인용 푸터입니다. by 상인 -->
	<script>
		var msg = "${msg}";
		if(msg != ""){
			alert(msg);
		}
	</script>
</html>