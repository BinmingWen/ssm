<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>

<script type="text/javascript">

	function sendJsonRequest() {
		alert("aaaa")
		$.ajax({
			url:"${pageContext.request.contextPath}/user/sendJsonRequest",
			type:"post",
			data:{username:user_name},
			success:function(result){
				$("#checkUser").text(result.msg);
			}
		})
		
	}
	
	function sendNoJsonRequest() {
		alert("aaaa")
		$.ajax({
			url:"${pageContext.request.contextPath}/sendNoJsonRequest",
			type:"post",
			data:{username:"Tom",userpassword:"1111"},
			success:function(result){
				alert(result.username);
				alert(result.userpassword);
			}
		})
		
	}
</script>
</head>
<body>
	<input type="button" onclick="sendJsonRequest()" value="通过json获取数据">
	<input type="button" onclick="sendNoJsonRequest()" value="通过键值对获取数据">
</body>
</html>